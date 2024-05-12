package com.example.spacenewsapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spacenewsapp.databinding.FragmentSignUpBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class SignUpFragment : Fragment() {
    lateinit var signUpBinding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = FragmentSignUpBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        signUpBinding.createAccount.setOnClickListener {
            signUp()
        }
        signUpBinding.SIGoRPTV.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
            findNavController().navigate(action)
        }

        return signUpBinding.root
    }




    fun googleSignIn(){

    }

    fun signUp() {
        val email = signUpBinding.SIEmail.text.toString()
        val password = signUpBinding.SIPass.text.toString()
        if (email.isNotEmpty()){
            if(password.isNotEmpty()){
                MainActivity.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity as MainActivity) {task ->
                        if (task.isSuccessful){
                            Toast.makeText(context, "Account Created!", Toast.LENGTH_SHORT).show()
                            MainActivity.bottomNavigationView?.visibility = View.VISIBLE
                            val action = SignUpFragmentDirections.actionSignUpFragmentToHomeFragment()
                            findNavController().navigate(action)
                        }
                        else{
                            Toast.makeText(context, "Failed to create account!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(context, "Please enter a password", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Please enter an email address", Toast.LENGTH_SHORT).show()
        }


    }
}