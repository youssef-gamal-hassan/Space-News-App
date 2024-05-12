package com.example.spacenewsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spacenewsapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)

        homeBinding.ImagesButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToImageFragment()
            findNavController().navigate(action)
        }
        homeBinding.ArticleButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToArticleFragment()
            findNavController().navigate(action)
        }
        homeBinding.BlogButton.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToBlogFragment()
            findNavController().navigate(action)
        }
        homeBinding.ReportButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToReportFragment()
            findNavController().navigate(action)
        }
        homeBinding.SearchButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        val view = homeBinding.root

        return view
    }
}