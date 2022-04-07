package id.ac.ubaya.anmp_uts_160419058.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.anmp_uts_160419058.FavoritesFragmentDirections
import id.ac.ubaya.anmp_uts_160419058.R
import id.ac.ubaya.anmp_uts_160419058.SearchFragmentDirections
import id.ac.ubaya.anmp_uts_160419058.model.Books
import id.ac.ubaya.anmp_uts_160419058.model.News
import id.ac.ubaya.anmp_uts_160419058.util.loadImage
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
        val fav = favList[position]
        with(holder.view) {
            txtTitle.text = fav.title
            txtAuthor.text = fav.author
            txtCode.text = fav.code
            txtGenre.text = fav.genre
            imageBookCard.loadImage(favList[position].image, progressBarBookCardPhoto)

            cardBook.setOnClickListener {
                val action = FavoritesFragmentDirections.actionFavoritesToDetail(fav.id.toString(),"fav")
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

    override fun getItemCount() = favList.size

    fun updateFavoritesList(newFavList: ArrayList<Books>){
        favList.clear()
        favList.addAll(newFavList)
        notifyDataSetChanged()
    }
}