package id.ac.ubaya.anmp_uts_160419058

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.anmp_uts_160419058.model.Books
import id.ac.ubaya.anmp_uts_160419058.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListAdapter(val bookList:ArrayList<Books>): RecyclerView
.Adapter<BookListAdapter.BookViewHolder>() {
    class BookViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        with(holder.view){
            txtTitle.text = book.title
            txtAuthor.text = book.author
            txtCode.text = book.code
            txtGenre.text = book.genre
            imageBookCard.loadImage(bookList[position].image, progressBarBookCardPhoto)

            cardBook.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchToDetail(book.id.toString(),"search")
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

    override fun getItemCount() = bookList.size

    fun updateBooksList(newBookList: ArrayList<Books>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }
}