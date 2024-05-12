package com.example.spacenewsapp

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spacenewsapp.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    lateinit var profileBinding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileBinding = FragmentProfileBinding.inflate(layoutInflater)
        val view  = profileBinding.root

        profileBinding.EmailTV.text = MainActivity.auth.currentUser?.email

        profileBinding.profileFavoritesButton.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToFavoritesFragment()
            findNavController().navigate(action)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileBinding.LogoutButton.setOnClickListener {
            MainActivity.auth.signOut()
            MainActivity.bottomNavigationView?.visibility = View.GONE
            val action = ProfileFragmentDirections.actionProfileFragmentToSignInFragment()
            findNavController().navigate(action)
        }
    }

}