package com.example.reelsmate

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.reelsmate.databinding.ActivityLoginBinding
import com.example.reelsmate.util.UiUtil
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseAuth.getInstance().currentUser?.let {
            //user is there logged in
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        binding.submitBtn.setOnClickListener {
            login()
        }

        binding.goToSignupBtn.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }

    }
    fun setInProgress(inProgress : Boolean){
        if(inProgress){
            binding.progressBar.visibility = View.VISIBLE
            binding.submitBtn.visibility = View.GONE
        }else{
            binding.progressBar.visibility = View.GONE
            binding.submitBtn.visibility = View.VISIBLE
        }
    }

    fun login() {
        val email = binding.emailInput.text.toString()
        val passsword = binding.passwordInput.text.toString()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailInput.setError("Email not valid")
            return
        }
        if(passsword.length<6) {
            binding.passwordInput.setError("Minimum 6 character")
            return
        }
        loginwithFirebasse(email,passsword)
    }

    fun loginwithFirebasse(email: String, passsword: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email,passsword
        ).addOnSuccessListener {
            UiUtil.showToast(this,"Login Successfully")
            setInProgress(false)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }.addOnFailureListener {
            UiUtil.showToast(applicationContext,it.localizedMessage?:"Something went wrong")
            setInProgress(false)
        }
    }
}