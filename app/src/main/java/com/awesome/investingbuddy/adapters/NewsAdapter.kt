package com.awesome.investingbuddy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.awesome.investingbuddy.R
import com.awesome.model.Article


class NewsAdapter(var list: List<Article>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val titletextview = itemView.findViewById<TextView>(R.id.articleTitle)
        val descriptiontextview = itemView.findViewById<TextView>(R.id.articleDescription)
        val datetextview = itemView.findViewById<TextView>(R.id.articleDateTime)
        val imageview = itemView.findViewById<ImageView>(R.id.articleImage)




    }
    fun updateData(newArticles: List<Article>) {
        list = newArticles
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.newsitem,parent,false)
    return NewsViewHolder(view)
    }




    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    val news = list[position]
        holder.titletextview.text = news.title
        holder.descriptiontextview.text = news.description
        holder.datetextview.text = news.publishedAt
        holder.imageview.load(news.urlToImage)
    }

}