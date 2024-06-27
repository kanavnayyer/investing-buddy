package com.awesome.investingbuddy.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.awesome.investingbuddy.databinding.FragmentNewTaskSheetBinding
import com.awesome.investingbuddy.models.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTaskSheet : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.lumpsumpcalculate.setOnClickListener {
            lumpsumcalculate()

        }

        binding.layoutsipcalculate.setOnClickListener {
            sipcalculate()

        }
        binding.WholeInvestmentPiechart.setOnClickListener {
            pieformer()

        }
        binding.cryptoscreenfrag.setOnClickListener {
            crippie()
        }
        binding.housegoldlayout.setOnClickListener {
            commoditesreal()
        }
        binding.EmiCalculator.setOnClickListener {
            emiCal()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun lumpsumcalculate()
    {

        activity?.let{
            val intent = Intent (it,LumpsumCalActivity::class.java)
            it.startActivity(intent)
        }

        dismiss()


    }
    private fun pieformer() {
        activity?.let{
            val intent = Intent (it,StockPiechartActivity::class.java)
            it.startActivity(intent)
        }

        dismiss()


    }



    private fun sipcalculate() {
        activity?.let {
            val intent = Intent (it,sipCalActivity::class.java)
            it.startActivity(intent)
            //showw()
        }

        dismiss()
    }


    private  fun crippie(){
        activity?.let {
            val intent= Intent(it,cripreccomendationActivity::class.java)
            it.startActivity(intent)
        }
        dismiss()
    }
    private  fun commoditesreal(){
        activity?.let {
            val intent= Intent(it,goldrealActivity::class.java)
            it.startActivity(intent)
        }
        dismiss()
    }
    private  fun emiCal(){
        activity?.let {
            val intent = Intent(it,EmiCalculatorActivity::class.java)
            it.startActivity(intent)
        }
        dismiss()
    }


}