package com.example.spacenewsapp

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.spacenewsapp.databinding.FragmentImageBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : Fragment() {

    lateinit var retrofitAPI: ImageAPI
    lateinit var imageBinding: FragmentImageBinding
    var NASABaseUrl = "https://api.nasa.gov/"
    var Images = ArrayList<Image>()
    var index: Int = 0
    companion object{
        var databaseRef = MainActivity.database.getReference(MainActivity.auth.currentUser!!.uid).child("favorites")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imageBinding = FragmentImageBinding.inflate(layoutInflater)
        val view = imageBinding.root

        imageBinding.ImageTitle.visibility = View.INVISIBLE

        val retrofit = Retrofit.Builder()
            .baseUrl(NASABaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitAPI = retrofit.create(ImageAPI::class.java)

        getImage()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageBinding.nextButtonFav.setOnClickListener {
            imageBinding.nextButtonFav.isClickable = false
            imageBinding.previousButtonFav.isClickable = false
            if(Images.isNotEmpty()) {
                if (Images.lastIndex != -1 && Images.lastIndex >= index + 1) {
                    index += 1
                    displayImage()
                } else {
                    getImage()
                }
            }
            imageBinding.previousButtonFav.isClickable = true
            imageBinding.nextButtonFav.isClickable = true
        }
        imageBinding.previousButtonFav.setOnClickListener {
            if(index - 1 >= 0){
                index -= 1
                displayImage()
            }
        }
        imageBinding.favoriteButtonFav.setOnClickListener {
            Images[index].title?.let { it1 -> databaseRef.child(it1).setValue(Images[index].url).addOnCompleteListener{
                Toast.makeText(context, "Image Added to Favorites", Toast.LENGTH_SHORT).show()
            }}

        }

    }

    fun displayImage(){
        imageBinding.ImageTitle.text = Images[index].title
        imageBinding.ImageTitle.visibility = View.VISIBLE
        Picasso.get()
            .load(Images[index].url)
            .resize(300, 280)
            .centerInside()
            .placeholder(R.drawable.spaceimage)
            .into(imageBinding.ImageIV)
    }

    fun getImage(){
        val call: Call<List<ImageResults>> = retrofitAPI.getImage(apiKey = resources.getString(R.string.api_key), count = 1)
        call.enqueue(object : Callback<List<ImageResults>>{
            override fun onResponse(call: Call<List<ImageResults>>, response: Response<List<ImageResults>>) {
                if (response.isSuccessful){
                    if(index != 0){
                        Images.add(Image(response.body()?.get(0)?.title, response.body()?.get(0)?.url, Images[index]))
                    }else{
                        Images.add(Image(response.body()?.get(0)?.title, response.body()?.get(0)?.url, null))
                    }
                    displayImage()
                }
                else{
                    imageBinding.ImageTitle.text = response.toString()
                    imageBinding.ImageTitle.visibility = View.VISIBLE
                }

            }

            override fun onFailure(call: Call<List<ImageResults>>, t: Throwable) {
                Toast.makeText(context, "Failed to get image", Toast.LENGTH_LONG).show()
                t.localizedMessage?.let { Log.e(ContentValues.TAG, it) }
            }
        })
    }

}