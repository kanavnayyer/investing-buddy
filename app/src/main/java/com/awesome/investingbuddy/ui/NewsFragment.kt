package com.awesome.investingbuddy.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awesome.api.ApiClient
import com.awesome.investingbuddy.adapters.NewsAdapter
import com.awesome.investingbuddy.databinding.FragmentNewsBinding
import com.awesome.model.Article
import com.awesome.model.newsmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : Fragment() {
private lateinit var binding: FragmentNewsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var page = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerHeadlines
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        newsAdapter = NewsAdapter(emptyList())
        recyclerView.adapter = newsAdapter

        fetchNews()
        startPeriodicLoading()


            }




        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentNewsBinding.inflate(inflater,container,false)
            return binding.root
    }
    private fun fetchNews() {
        val apiClient = ApiClient()
        val newsApiService = apiClient.newsApiService

        newsApiService.getHeadLines("us", page).enqueue(object : Callback<newsmodel> {
            override fun onResponse(call: Call<newsmodel>, response: Response<newsmodel>) {
                if (response.isSuccessful) {
                    val newsModel: newsmodel? = response.body()
                    newsModel?.let {
                        val articles = it.articles.map { article ->
                            Article(
                                article.description,

                                article.title,
                                article.publishedAt,
                                article.name,
                                article.urlToImage
                            )
                        }
                        Log.d("MainActivity", "Articles fetched: ${articles.size}")
                        newsAdapter.updateData(newsAdapter.list+ articles)
                    }
                } else {
                    Log.e("MainActivity", "Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<newsmodel>, t: Throwable) {
                Log.e("MainActivity", "Network error: ${t.message}")
            }
        })
    }

    private fun startPeriodicLoading() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                page++
                fetchNews()
                handler.postDelayed(this, 50000)
            }
        }, 50000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

}