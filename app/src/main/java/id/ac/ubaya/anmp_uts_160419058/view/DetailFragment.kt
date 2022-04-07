package id.ac.ubaya.anmp_uts_160419058

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.anmp_uts_160419058.util.loadImage
import id.ac.ubaya.anmp_uts_160419058.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private val BookListAdapter  = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments != null) {
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            val id = DetailFragmentArgs.fromBundle(requireArguments()).bookId
            val type = DetailFragmentArgs.fromBundle(requireArguments()).type
            if (type == "fav"){
                viewModel.fetch_fav(id)
            }else{
                viewModel.fetch_book(id)
            }

        }
        observeViewModel()
    }
    fun observeViewModel() {
        txtTitle2.text = viewModel.booksLiveData.value?.title
        txtAuthor2.text= viewModel.booksLiveData.value?.author
        txtCode2.text = viewModel.booksLiveData.value?.code
        txtGenre2.text = viewModel.booksLiveData.value?.genre
        imageBookDetail.loadImage(viewModel.booksLiveData.value?.image, progressBarBookDetailPhoto)
//        val uri = viewModel.booksLiveData.value!!.image
//        imageBookDetail.setImageURI(uri.toUri())


    }

}