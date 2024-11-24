package com.example.reelsmate

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reelsmate.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

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

    fun setInProgress(inProgress : Boolean){
        if(inProgress){
            binding.progressBar.visibility = View.VISIBLE
            binding.submitBtn.visibility = View.GONE
        }else{
            binding.progressBar.visibility = View.GONE
            binding.submitBtn.visibility = View.VISIBLE
        }
    }

    fun signup(){
        val email = binding.emailInput.text.toString()
        val passsword = binding.passwordInput.text.toString()
        val confirmPassword = binding.confirmPasswordInput.text.toString()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailInput.setError("Email not valid")
            return
        }
        if(passsword.length<6){
            binding.passwordInput.setError("Minimum 6 character")
            return
        }
        if(passsword != confirmPassword){
            binding.confirmPasswordInput.setError("Password not matched")
            return
        }
        signupwithFirebasse(email,passsword)
    }
    fun signupwithFirebasse(email : String, password : String){
        setInProgress(true)
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email, password
        ).addOnSuccessListener {
            Toast.makeText(applicationContext,"Success", Toast.LENGTH_SHORT).show()
        }
    }
}