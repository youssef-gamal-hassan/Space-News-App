package com.example.spacenewsapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacenewsapp.databinding.FragmentArticleBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.contracts.Returns

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : Fragment() {
    lateinit var articleBinding: FragmentArticleBinding
    var responses = ArrayList<Results>()
    lateinit var adapter: ArticleAdapter
    var ArticleTitles = ArrayList<String>()
    var ArticleImages= ArrayList<String>()
    var ArticleUrl= ArrayList<String>()
    var ArticleSummary= ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleBinding = FragmentArticleBinding.inflate(layoutInflater)
        val view = articleBinding.root

        getArticles()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        articleBinding.ArticlesRV.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        adapter = ArticleAdapter(ArticleTitles,ArticleImages, ArticleUrl, ArticleSummary, view.context)

        articleBinding.ArticlesRV.adapter = adapter
    }

    fun getArticles(){
        val call: Call<com.example.spacenewsapp.Returns> = MainActivity.retrofitAPI.getArticles()
        call.enqueue(object : Callback<com.example.spacenewsapp.Returns>{
            override fun onResponse(call: Call<com.example.spacenewsapp.Returns>, response: Response<com.example.spacenewsapp.Returns>) {
                if (response.isSuccessful){

                    responses = response.body()?.results as ArrayList<Results>

                    for(i in responses){
                        ArticleTitles.add(i.title)
                        ArticleImages.add(i.image_url)
                        ArticleSummary.add(i.summary)
                        ArticleUrl.add(i.url)
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<com.example.spacenewsapp.Returns>, t: Throwable) {
                Toast.makeText(context, "Failed to get articles", Toast.LENGTH_LONG).show()
                t.localizedMessage?.let { Log.e(TAG, it) }
            }
        })
    }
}