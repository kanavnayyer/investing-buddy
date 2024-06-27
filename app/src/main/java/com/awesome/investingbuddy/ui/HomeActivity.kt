package com.awesome.investingbuddy.ui

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import com.awesome.investingbuddy.MainActivity
import com.awesome.investingbuddy.R

import com.awesome.investingbuddy.databinding.ActivityHomeBinding
import com.awesome.investingbuddy.databinding.ActivityMainBinding
import com.awesome.investingbuddy.models.TaskViewModel
import com.awesome.investingbuddy.util.AllApi

import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide();
        setupInitialVisibility()
        // getSupportActionBar()?.show()
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.newTaskButton.setOnClickListener {
            NewTaskSheet().show(supportFragmentManager, "newTaskTag")

        }







        binding.cryptobtn.setOnClickListener {
            val fragment = CryptoPrice() // Replace 'MyFragment' with your fragment class

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null) // Optional: Adds the transaction to the back stack
                .commit()

hideViews()}









        binding.stkbtn.setOnClickListener {
            val fragment = NotesFragment() // Replace 'MyFragment' with your fragment class

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null) // Optional: Adds the transaction to the back stack
                .commit()

            hideViews()
        }
        binding.share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "https://github.com/kanavnayyer/investing-buddy")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }



    }
    private fun setupInitialVisibility() {
        // Set initial visibility when MainActivity starts
        binding.linearLAyout.visibility = View.VISIBLE
        binding.share.visibility = View.VISIBLE
        binding.startview.visibility = View.VISIBLE
        binding.arrw.visibility = View.VISIBLE
        binding.newTaskButton.visibility = View.VISIBLE
    }

    private fun hideViews() {
        // Hide views when fragment is opened
        binding.linearLAyout.visibility = View.GONE
        binding.share.visibility = View.GONE
        binding.startview.visibility = View.GONE
        binding.arrw.visibility = View.GONE
        binding.newTaskButton.visibility = View.GONE
    }

    // Handle back press
    override fun onBackPressed() {

        // Check if there are fragments in the back stack
        if (supportFragmentManager.backStackEntryCount > 0) {
            // Pop the fragment from the back stack
            supportFragmentManager.popBackStack()
            showViews()
        }
    }

    private fun showViews() {
        // Show views when no fragment is present
        binding.linearLAyout.visibility = View.VISIBLE
        binding.share.visibility = View.VISIBLE
        binding.startview.visibility = View.VISIBLE
        binding.arrw.visibility = View.VISIBLE
        binding.newTaskButton.visibility = View.VISIBLE
    }
}


    // Override this method to recognize touch event
