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
import com.example.spacenewsapp.databinding.FragmentReportBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [ReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportFragment : Fragment() {

    lateinit var reportBinding: FragmentReportBinding
    var responses = ArrayList<ReportsResults>()
    lateinit var adapter: ReportsAdapter
    var ArticleTitles = ArrayList<String>()
    var ArticleImages= ArrayList<String>()
    var ArticleUrl= ArrayList<String>()
    var ArticleSummary= ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reportBinding = FragmentReportBinding.inflate(layoutInflater)
        val view = reportBinding.root

        getReports()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        reportBinding.ReportRV.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        adapter = ReportsAdapter(ArticleTitles,ArticleImages, ArticleUrl, ArticleSummary, view.context)

        reportBinding.ReportRV.adapter = adapter
    }

    fun getReports(){
        val call: Call<ReportsReturns> = MainActivity.retrofitAPI.getReports()
        call.enqueue(object : Callback<ReportsReturns> {
            override fun onResponse(call: Call<ReportsReturns>, response: Response<ReportsReturns>) {
                if (response.isSuccessful){

                    responses = response.body()?.results as ArrayList<ReportsResults>

                    for(i in responses){
                        ArticleTitles.add(i.title)
                        ArticleImages.add(i.image_url)
                        ArticleSummary.add(i.summary)
                        ArticleUrl.add(i.url)
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ReportsReturns>, t: Throwable) {
                Toast.makeText(context, "Failed to get articles", Toast.LENGTH_LONG).show()
                t.localizedMessage?.let { Log.e(ContentValues.TAG, it) }
            }
        })
    }

}