package com.example.spacenewsapp

import android.content.ContentValues
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
import com.example.spacenewsapp.databinding.FragmentBlogBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [BlogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlogFragment : Fragment() {
    lateinit var blogBinding: FragmentBlogBinding
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
        blogBinding = FragmentBlogBinding.inflate(layoutInflater)
        val view = blogBinding.root

        getBlogs()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        blogBinding.ArticlesRV.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        adapter = ArticleAdapter(ArticleTitles,ArticleImages, ArticleUrl, ArticleSummary, view.context)

        blogBinding.ArticlesRV.adapter = adapter
    }

    fun getBlogs(){
        val call: Call<Returns> = MainActivity.retrofitAPI.getBlogs()
        call.enqueue(object : Callback<Returns> {
            override fun onResponse(call: Call<Returns>, response: Response<Returns>) {
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

            override fun onFailure(call: Call<Returns>, t: Throwable) {
                Toast.makeText(context, "Failed to get articles", Toast.LENGTH_LONG).show()
                t.localizedMessage?.let { Log.e(ContentValues.TAG, it) }
            }
        })
    }
}