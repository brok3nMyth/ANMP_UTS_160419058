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
import id.ac.ubaya.anmp_uts_160419058.model.Books
import id.ac.ubaya.anmp_uts_160419058.model.Loans

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val booksLiveData = MutableLiveData<Books>()
    val favoritesLiveData = MutableLiveData<Books>()
    val loansLiveData = MutableLiveData<Loans>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch_book(IdBook : String) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://api.npoint.io/3f4650770dbbd7694dee/book/$IdBook" //perlu di akali gimana caranya dari code jadi id / hapus code ganti jadi id

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val result = Gson().fromJson<Books>(it, Books::class.java)
                booksLiveData.value = result
                Log.d("showvolley", it)
            },
            {
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    fun fetch_fav(IdBook : String) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://api.npoint.io/f999e8046a47c3e8bf9f/favorites/$IdBook" //perlu di akali gimana caranya dari code jadi id (coba pakai index)/ hapus code ganti jadi id

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val result = Gson().fromJson<Books>(it, Books::class.java)
                favoritesLiveData.value = result
                Log.d("showvolley", it)
            },
            {
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    fun fetch_loan(IdLoan : String) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://api.npoint.io/cb5a60da17413fda90b2/loan/$IdLoan" //perlu di akali gimana caranya dari code jadi id (coba pakai index)/ hapus code ganti jadi id/

        val stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                val result = Gson().fromJson<Loans>(it, Loans::class.java)
                loansLiveData.value = result
                Log.d("showvolley", it)
            },
            {
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