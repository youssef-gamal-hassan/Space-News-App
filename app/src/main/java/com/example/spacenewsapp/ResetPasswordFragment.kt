package com.example.spacenewsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spacenewsapp.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {
    lateinit var resetPasswordBinding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resetPasswordBinding = FragmentResetPasswordBinding.inflate(layoutInflater)
        val view =  resetPasswordBinding.root

        resetPasswordBinding.ResetEmailButton.setOnClickListener {
            resetPass()
        }
        return view
    }

    fun resetPass(){
        val email = resetPasswordBinding.ResetEmailTE.text.toString()

        if(email.isNotEmpty()){
            MainActivity.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener{ task->
                    if(task.isSuccessful){
                        Toast.makeText(context, "Email Sent!", Toast.LENGTH_SHORT).show()
                        val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToSignInFragment()
                        findNavController().navigate(action)
                    }
                    else{
                        Toast.makeText(context, "Email is not valid!", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(context, "Enter an email address", Toast.LENGTH_SHORT).show()
        }
    }
}