package com.awesome.investingbuddy.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awesome.investingbuddy.MainActivity
import com.awesome.investingbuddy.R
class SplashScreenActivity : AppCompatActivity() {
    private var i = 0
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val img=findViewById<ImageView>(R.id.chartt)
        val rotate = RotateAnimation(
            0f,
            180f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 1000
        rotate.interpolator = LinearInterpolator()


        img.startAnimation(rotate)
        Handler(Looper.getMainLooper()).postDelayed({


                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
             }, 1000) // 3000 is the delaye


    }}