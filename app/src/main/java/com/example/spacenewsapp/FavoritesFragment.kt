package com.example.spacenewsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.spacenewsapp.databinding.FragmentFavoritesBinding
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    lateinit var favoritesBinding: FragmentFavoritesBinding
    var favoritesTitles = ArrayList<String>()
    var favoritesImages = ArrayList<String>()
    var index = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favoritesBinding = FragmentFavoritesBinding.inflate(layoutInflater)
        val view = favoritesBinding.root
        favoritesBinding.ImageTitle.visibility = View.INVISIBLE

        getFavorites()
//        displayImage()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesBinding.nextButtonFav.setOnClickListener {
            if(favoritesImages.isNotEmpty()) {
                if (favoritesImages.lastIndex != -1 && favoritesImages.lastIndex >= index + 1) {
                    index += 1
                    displayImage()
                } else {
                    displayImage()
                }
            }
        }
        favoritesBinding.previousButtonFav.setOnClickListener {
            if(index - 1 >= 0){
                index -= 1
                displayImage()
            }
        }
    }

    fun displayImage(){
        favoritesBinding.ImageTitle.text = favoritesTitles[index]
        favoritesBinding.ImageTitle.visibility = View.VISIBLE
        Picasso.get()
            .load(favoritesImages[index])
            .resize(300, 280)
            .centerInside()
            .placeholder(R.drawable.spaceimage)
            .into(favoritesBinding.ImageIV)
    }

    fun getFavorites(){
        ImageFragment.databaseRef.get().addOnSuccessListener {
            if (it.exists()){
                for (i in it.children){
                    i.key?.let { it1 -> favoritesTitles.add(it1) }
                    favoritesImages.add(i.getValue().toString())
                }
                Toast.makeText(context, "Favorites loaded", Toast.LENGTH_SHORT).show()
                displayImage()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "No favorites found", Toast.LENGTH_SHORT).show()
        }
    }

}