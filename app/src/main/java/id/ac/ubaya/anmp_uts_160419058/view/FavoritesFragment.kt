package id.ac.ubaya.anmp_uts_160419058

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.anmp_uts_160419058.view.FavoritesListAdapter
import id.ac.ubaya.anmp_uts_160419058.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_main_menu.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.recSearchView


class FavoritesFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val BookListAdapter  = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh_fav()

        recFavView.layoutManager = LinearLayoutManager(context)
        recFavView.adapter = BookListAdapter

        refreshLayoutFav.setOnRefreshListener {
            recFavView.visibility = View.GONE

            progressLoadFav.visibility = View.VISIBLE
            viewModel.refresh_fav()
            refreshLayoutFav.isRefreshing = false
            txtErrorFav.visibility= View.GONE
        }

        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner, Observer {
            BookListAdapter.updateBooksList(it)
        })
        viewModel.booksLoadErrorLiveData.observe(viewLifecycleOwner, Observer {
            txtErrorFav.visibility = if(it) View.VISIBLE else View.GONE

        })
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recFavView.visibility = View.GONE
                progressLoadFav.visibility = View.VISIBLE
            } else {
                recFavView.visibility = View.VISIBLE
                progressLoadFav.visibility = View.GONE
            }
        })

    }
}