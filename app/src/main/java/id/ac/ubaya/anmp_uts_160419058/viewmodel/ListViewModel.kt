package id.ac.ubaya.anmp_uts_160419058.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.anmp_uts_160419058.model.Books
import id.ac.ubaya.anmp_uts_160419058.model.Loans
import id.ac.ubaya.anmp_uts_160419058.model.News

class ListViewModel(application: Application): AndroidViewModel(application) {
    val booksLiveData = MutableLiveData<ArrayList<Books>>()
    val booksLoadErrorLiveData = MutableLiveData<Boolean>()
    val favoritesLiveData = MutableLiveData<ArrayList<Books>>()
    val favoritesLoadErrorLiveData = MutableLiveData<Boolean>()
    val newsLiveData = MutableLiveData<ArrayList<News>>()
    val newsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loansLiveData = MutableLiveData<ArrayList<Loans>>()
    val loansLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh_books() {
        booksLoadErrorLiveData.value = false
        loadingLiveData.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url= "https://api.npoint.io/3f4650770dbbd7694dee/book/"

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val sType = object : TypeToken<ArrayList<Books>>(){}.type
                val result = Gson().fromJson<ArrayList<Books>>(it, sType)
                booksLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                booksLoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
    fun refresh_loans(){
        loansLoadErrorLiveData.value = false
        loadingLiveData.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url= "https://api.npoint.io/cb5a60da17413fda90b2/loan/"

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val sType = object : TypeToken<ArrayList<Loans>>(){}.type
                val result = Gson().fromJson<ArrayList<Loans>>(it, sType)
                loansLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                booksLoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    fun refresh_news(){
        newsLoadErrorLiveData.value = false
        loadingLiveData.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url= "https://api.npoint.io/ee9b06f3a5ca0a25243a/news/"

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val sType = object : TypeToken<ArrayList<News>>(){}.type
                val result = Gson().fromJson<ArrayList<News>>(it, sType)
                newsLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                booksLoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
    fun refresh_fav(){
        favoritesLoadErrorLiveData.value = false
        loadingLiveData.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url= "https://api.npoint.io/f999e8046a47c3e8bf9f/favorites/"

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val sType = object : TypeToken<ArrayList<Books>>(){}.type
                val result = Gson().fromJson<ArrayList<Books>>(it, sType)
                favoritesLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                favoritesLoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}