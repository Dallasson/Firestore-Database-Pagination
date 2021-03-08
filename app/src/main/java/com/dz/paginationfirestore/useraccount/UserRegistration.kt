package com.dz.paginationfirestore.useraccount

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dz.paginationfirestore.R
import com.dz.paginationfirestore.databinding.ActivityUserLoginBinding
import com.dz.paginationfirestore.databinding.ActivityUserRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class UserRegistration : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private var _binding : ActivityUserRegistrationBinding? = null
    private val binding  get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding  = ActivityUserRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerUser.setOnClickListener {
            if(validateEmail()  and validatePassword()){
                val userEmail = binding.emailInput.editText!!.text.toString()
                val userPassword = binding.passwordInput.editText!!.text.toString()

                firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                    .addOnCompleteListener {
                        Toast.makeText(this,"Registered Successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Error registering User",Toast.LENGTH_SHORT).show()
                    }
            }
        }
        binding.login.setOnClickListener {
           Intent(this,UserLogin::class.java).apply {
               startActivity(this)
           }
        }

    }


    private fun validateEmail() : Boolean {
        val userEmail = binding.emailInput.editText!!.text.toString()
        return if(userEmail.isEmpty()){
            binding.emailInput.error = "Email Missing"
            false
        } else {
            binding.emailInput.error = null
            true
        }
    }
    private fun validatePassword() : Boolean{
        val userPassword = binding.passwordInput.editText!!.text.toString()
        return if(userPassword.isEmpty()){
            binding.passwordInput.error = "Password Missing"
            false
        } else {
            binding.passwordInput.error = null
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}