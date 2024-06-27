package com.awesome.investingbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.awesome.investingbuddy.databinding.ActivityMainBinding

import com.awesome.investingbuddy.ui.CryptoPrice
import com.awesome.investingbuddy.ui.HomeActivity
import com.awesome.investingbuddy.ui.RegisterActivity
import com.awesome.investingbuddy.util.AllApi
import com.awesome.investingbuddy.util.AllApi.Companion.auth


import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()


        if(auth.currentUser != null){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.registerTV.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        binding.loginBtn.setOnClickListener{
            val email=binding.emailLogin.text.toString()
            val passWord=binding.passwordLogin.text.toString()
            if(email.isNotEmpty()&&passWord.isNotEmpty()){
                auth.signInWithEmailAndPassword(email,passWord).addOnCompleteListener{
                    if(it.isSuccessful){
                        startActivity(Intent(this,HomeActivity::class.java))
                        finish()
                    }

                }.addOnFailureListener {
                    Toast.makeText(this,it.localizedMessage.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}