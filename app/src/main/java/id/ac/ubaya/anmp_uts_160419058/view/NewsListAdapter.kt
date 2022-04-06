package id.ac.ubaya.anmp_uts_160419058

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.anmp_uts_160419058.model.Books
import id.ac.ubaya.anmp_uts_160419058.model.Loans
import id.ac.ubaya.anmp_uts_160419058.model.News
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.fragment_main_menu.*
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsListAdapter(val newsList:ArrayList<News>): RecyclerView
.Adapter<NewsListAdapter.NewsViewHolder>() {
    class NewsViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.news_list_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        with(holder.view){
            txtNewsTitle.text = news.title
            txtNewsContent.text = news.content
            txtNewsDate.text = news.date
        }


    }
    override fun getItemCount() = newsList.size

    fun updateNewsList(newNewsList: ArrayList<News>){
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }
}