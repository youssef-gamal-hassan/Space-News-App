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
import com.example.spacenewsapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    lateinit var signInBinding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signInBinding = FragmentSignInBinding.inflate(layoutInflater)
        val view = signInBinding.root

        signInBinding.signIn.setOnClickListener {
            signIn()
        }
        signInBinding.SIGoRPTV.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToResetPasswordFragment()
            findNavController().navigate(action)
        }
        signInBinding.SIGoSUTV2.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
        return view
    }
    fun signIn(){
        val email = signInBinding.SIEmail.text.toString()
        val pass = signInBinding.SIPass.text.toString()

        if (email.isNotEmpty()){
            if(pass.isNotEmpty()){
                MainActivity.auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(activity as MainActivity){task->
                        if(task.isSuccessful){
                            Log.d(ContentValues.TAG, "signInWithEmail: successful")
                            val user = MainActivity.auth.currentUser
                            if (user != null){
                                MainActivity.bottomNavigationView?.visibility = View.VISIBLE
                                val action = SignInFragmentDirections.actionSignInFragmentToHomeFragment()
                                findNavController().navigate(action)
                            }
                        }else{
                            Log.w(ContentValues.TAG, "signInWithEmail: failed")
                            Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(context, "Please enter a password.", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Please enter an email.", Toast.LENGTH_SHORT).show()
        }
    }
}