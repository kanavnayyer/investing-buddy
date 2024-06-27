package com.awesome.investingbuddy.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

import com.awesome.investingbuddy.R
import com.awesome.investingbuddy.databinding.ActivityLumpsumCalBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import kotlin.math.roundToInt

class LumpsumCalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLumpsumCalBinding

    lateinit var pieChart: PieChart
    private var pieval1=0
    private var pieval2=0
    private var ganed=0
    private var initial=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLumpsumCalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sliderate.addOnChangeListener { slider, value, fromUser ->
            binding.expectedreturnedit.setText(value.toInt().toString())
        }
        binding.calculatesum.setOnClickListener {
            checkinng()
        }
        binding.cc.setOnClickListener { it.hideKeyboard() }
        val actionBar: ActionBar? = supportActionBar


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
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    fun piegen(){
        pieChart = binding.pieChart
        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().text="Based on added Values"
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        // on below line we are setting drag for our pie chart
        pieChart.setDragDecelerationFrictionCoef(0.95f)

        // on below line we are setting hole
        // and hole color for pie chart
        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)

        // on below line we are setting circle color and alpha
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        // on  below line we are setting hole radius
        pieChart.setHoleRadius(58f)
        pieChart.setTransparentCircleRadius(61f)

        // on below line we are setting center text
        pieChart.setDrawCenterText(true)

        // on below line we are setting
        // rotation for our pie chart
        pieChart.setRotationAngle(0f)

        // enable rotation of the pieChart by touch
        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)

        // on below line we are setting animation for our pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // on below line we are creating array list and
        // adding data to it to display in pie chart
        val entries: ArrayList<PieEntry> = ArrayList()
        val returenam=pieval1


        val initialwealth=pieval2

        entries.add(PieEntry(returenam.toFloat(),))
        entries.add(PieEntry(initialwealth.toFloat()))



        // on below line we are setting pie data set
        val dataSet = PieDataSet(entries,"")


        // on below line we are setting icons.
        dataSet.setDrawIcons(true)

        pieChart.centerText="weathgained $ganed\nInitial wealth$initial"
        // on below line we are setting slice for pie
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors to list
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.piegreentouch))
        colors.add(resources.getColor(R.color.blue))


        // on below line we are setting colors.
        dataSet.colors = colors

        // on below line we are setting pie data set
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)

        pieChart.setData(data)

        // undo all highlights
        pieChart.highlightValues(null)

        // loading chart
        pieChart.invalidate()
    }
    fun checkinng() {
        if (binding.Lumpsumpamount.text.isEmpty() || binding.Lumpsumpyearedit.text.isEmpty() || binding.expectedreturnedit.text.isEmpty()) {

            Toast.makeText(this@LumpsumCalActivity, "Please enter credentials", Toast.LENGTH_SHORT)
                .show()
        } else if (binding.Lumpsumpamount.text.toString().toFloat() > 9900000) {
            Toast.makeText(
                this@LumpsumCalActivity,
                "Please enter Amount less than 9900001",
                Toast.LENGTH_SHORT
            ).show()
        } else if (binding.Lumpsumpyearedit.text.toString().toFloat() > 31) {
            Toast.makeText(
                this@LumpsumCalActivity,
                "Please enter less  than 31 year",
                Toast.LENGTH_SHORT
            ).show()
        } else if (binding.expectedreturnedit.text.toString().toFloat() > 41) {
            Toast.makeText(
                this@LumpsumCalActivity,
                "Please enter les than 41 % return",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            totalgenerated()
            piegen()
        }


    }



    fun totalgenerated(){
        var interest= binding.expectedreturnedit//  binding.expectedreturnedit.text.toString()
        var amt=binding.Lumpsumpamount
        var timep=binding.Lumpsumpyearedit


//    interest.to(Long)

//    timep.to(Long)
//


        var a: Double =Math.pow(1+(interest.text.toString().toDouble()/100).toDouble() , timep.text.toString().toDouble())
        var generatedamnt =amt.text.toString().toFloat()*a.toFloat()

        generatedamnt= ((generatedamnt*100.0).roundToInt()/100.0).toFloat().toFloat()

//




        var left=generatedamnt-amt.text.toString().toFloat()
        ganed= left.toInt()
        initial=amt.text.toString().toInt()
        pieval1= ((left/generatedamnt)*100).toInt()

        pieval2=100-pieval1
        binding.lumpsumwealthres.setText(left.toString())
        binding.lumpsumtotalwealthres.setText(generatedamnt.toFloat().toString())


//     FV = (100 (1 + .04/12))^12(5)  4622.88 x (1 + 8%)^6 = 7,335.93

    }

}


