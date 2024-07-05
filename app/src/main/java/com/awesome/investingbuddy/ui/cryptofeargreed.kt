package com.awesome.investingbuddy.ui

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awesome.investingbuddy.R
import com.bumptech.glide.Glide

class cryptofeargreed : AppCompatActivity() {
    private var cpd: Dialog?=null
    private var load: Dialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cryptofeargreed)


        val myUrl = "https://alternative.me/crypto/fear-and-greed-index.png"

        val img=findViewById<ImageView>(R.id.imagefeargreed)
        //  shwload()

        Glide.with(getApplicationContext())

            .load(myUrl)
            .skipMemoryCache(true).error(R.drawable.loaad)
            .into(img)
        //    loadb()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}



