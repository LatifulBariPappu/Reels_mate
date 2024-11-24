package com.example.reelsmate

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.reelsmate.databinding.ActivitySignupBinding
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    lateinit var binding:ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            signup()
        }
    }
    fun signup(){
        val email = binding.emailInput.text.toString()
        val passsword = binding.passwordInput.text.toString()
        val confirmPassword = binding.confirmPasswordInput.text.toString()

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailInput.setError("Email not valid")
            return
        }
        if(passsword.length<6){
            binding.passwordInput.setError("Minimum 6 character")
            return
        }
        if(confirmPassword.length<6){
            binding.confirmPasswordInput.setError("Password not matched")
            return
        }
        signupwithFirebasse()
    }
    fun signupwithFirebasse(){

    }
}