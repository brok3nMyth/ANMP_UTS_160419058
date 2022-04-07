package id.ac.ubaya.anmp_uts_160419058.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.anmp_uts_160419058.R
import id.ac.ubaya.anmp_uts_160419058.SearchFragmentDirections
import id.ac.ubaya.anmp_uts_160419058.model.Books
import kotlinx.android.synthetic.main.book_list_item.view.*

class FavoritesListAdapter (val favList:ArrayList<Books>): RecyclerView
.Adapter<FavoritesListAdapter.FavViewHolder>() {
    class FavViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val book = favList[position]
        with(holder.view) {
            txtTitle.text = book.title
            txtAuthor.text = book.author
            txtCode.text = book.code
            txtGenre.text = book.genre
            val uri = book.image
            imageBookCard.setImageURI(uri.toUri())

            cardBook.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchToDetail(book.id.toString(),"fav")
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

    override fun getItemCount() = favList.size
}