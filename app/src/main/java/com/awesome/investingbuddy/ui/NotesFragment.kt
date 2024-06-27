package com.awesome.investingbuddy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.awesome.investingbuddy.R
import com.awesome.investingbuddy.adapters.NoteAdapter
import com.awesome.investingbuddy.databinding.FragmentNotesBinding
import com.google.firebase.firestore.FirebaseFirestore


class NotesFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var binding: FragmentNotesBinding// Replace with your binding class
    // Initialize binding and other variables here
    private  lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    }
