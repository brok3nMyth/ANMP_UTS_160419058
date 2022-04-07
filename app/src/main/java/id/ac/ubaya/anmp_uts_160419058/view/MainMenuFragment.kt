package id.ac.ubaya.anmp_uts_160419058

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.anmp_uts_160419058.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_main_menu.*


class MainMenuFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val newsListAdapter  = NewsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
        txtErrorMain.visibility= View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh_news()
        txtErrorMain.visibility= View.GONE
        recNewsView.layoutManager = LinearLayoutManager(context)
        recNewsView.adapter = newsListAdapter

        refreshLayoutMain.setOnRefreshListener {
            recNewsView.visibility = View.GONE

            progressLoadMain.visibility = View.VISIBLE
            viewModel.refresh_news()
            refreshLayoutMain.isRefreshing = false
            txtErrorMain.visibility= View.GONE
        }

        observeViewModel()
        /**tambahkan navigation coding**/


    }

    fun observeViewModel() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            newsListAdapter.updateNewsList(it)
        })
        viewModel.newsLoadErrorLiveData.observe(viewLifecycleOwner, Observer {
            txtErrorMain.visibility = if(it) View.VISIBLE else View.GONE

        })
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recNewsView.visibility = View.GONE
                progressLoadMain.visibility = View.VISIBLE
            } else {
                recNewsView.visibility = View.VISIBLE
                progressLoadMain.visibility = View.GONE
            }
        })

    }
}