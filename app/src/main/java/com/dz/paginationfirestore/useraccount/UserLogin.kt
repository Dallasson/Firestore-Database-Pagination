package com.dz.paginationfirestore.useraccount

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dz.paginationfirestore.ui.MainActivity
import com.dz.paginationfirestore.databinding.ActivityUserLoginBinding
import com.google.firebase.auth.FirebaseAuth

class UserLogin : AppCompatActivity() {
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private lateinit var firebaseAuth: FirebaseAuth
    private var _binding : ActivityUserLoginBinding? = null
    private val binding  get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding  = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth  = FirebaseAuth.getInstance()

        binding.loginUser.setOnClickListener {
            if(validateEmail() &&  validatePassword()){
                val userEmail = binding.emailInput.editText!!.text.toString()
                val userPassword = binding.passwordInput.editText!!.text.toString()

                firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword)
                    .addOnCompleteListener {
                     Toast.makeText(this,"Logged In Successfully",Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Error Signing In",Toast.LENGTH_SHORT).show()
                    }
            }
        }
        binding.register.setOnClickListener {
            Intent(this,UserRegistration::class.java).apply {
                startActivity(this)
            }
        }

        authStateListener = FirebaseAuth.AuthStateListener {
            if(it.currentUser != null){
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authStateListener)
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