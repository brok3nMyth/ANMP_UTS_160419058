package id.ac.ubaya.anmp_uts_160419058

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.anmp_uts_160419058.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val BookListAdapter  = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh_books()

        recSearchView.layoutManager = LinearLayoutManager(context)
        recSearchView.adapter = BookListAdapter

        refreshLayoutSearch.setOnRefreshListener {
            recSearchView.visibility = View.GONE

            progressLoadSearch.visibility = View.VISIBLE
            viewModel.refresh_books()
            refreshLayoutSearch.isRefreshing = false
            txtErrorSearch.visibility= View.GONE
        }

        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner, Observer {
            BookListAdapter.updateBooksList(it)
        })
        viewModel.booksLoadErrorLiveData.observe(viewLifecycleOwner, Observer {
            txtErrorSearch.visibility = if(it) View.VISIBLE else View.GONE

        })
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recSearchView.visibility = View.GONE
                progressLoadSearch.visibility = View.VISIBLE
            } else {
                recSearchView.visibility = View.VISIBLE
                progressLoadSearch.visibility = View.GONE
            }
        })

    }
}