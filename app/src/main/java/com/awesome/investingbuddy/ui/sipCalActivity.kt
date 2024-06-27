package com.awesome.investingbuddy.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.awesome.investingbuddy.R
import com.awesome.investingbuddy.databinding.ActivitySipCalBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import kotlin.math.pow

class sipCalActivity : AppCompatActivity(){
    lateinit var pieChart: PieChart

    private lateinit var binding: ActivitySipCalBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySipCalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sliderate.addOnChangeListener{slider, value, fromUser ->
            binding.expectedreturnedit.setText(value.toInt().toString())}
        binding.calsipbtn.setOnClickListener {

            checkdatafill()
        }
        binding.cc.setOnClickListener { it.hideKeyboard() }


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
    fun checkdatafill(){
        var amtsip=binding.sipsumpamount
        var siptime=binding.sipyearedit
        var interes=binding.expectedreturnedit

        if(amtsip.text.isEmpty()||siptime.text.isEmpty()||interes.text.isEmpty()){
            Toast.makeText(this@sipCalActivity,"Please Enter all Credentials",Toast.LENGTH_SHORT).show()
        }
        else if(amtsip.text.toString().toFloat()>=1000001){
            Toast.makeText(this@sipCalActivity,"Please enter Amount less than 1 million(10lakh)",Toast.LENGTH_SHORT).show()
        }
        else if(interes.text.toString().toFloat()>31){
            Toast.makeText(this@sipCalActivity,"Please enter expected return less than 31%",Toast.LENGTH_SHORT).show()

        }
        else if(siptime.text.toString().toInt()>31){
            Toast.makeText(this@sipCalActivity,"Please enter Less than 31 years",Toast.LENGTH_SHORT).show()
        }
        else{
            sipamntgenerated()

        }


    }






    fun piegen(totalwealth:Long,spendwealth:Long,pieval1:Float,pieval2:Float){
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


        entries.add(PieEntry(pieval1))
        entries.add(PieEntry(pieval2))



        // on below line we are setting pie data set
        val dataSet = PieDataSet(entries,"")


        // on below line we are setting icons.
        dataSet.setDrawIcons(true)

        pieChart.centerText="Total wealth$spendwealth\nSpend wealth$totalwealth"
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



    fun sipamntgenerated() {
        var interest= binding.expectedreturnedit.text.toString()//  binding.expectedreturnedit.text.toString()
        var amt=binding.sipsumpamount.text.toString()
        var timep=binding.sipyearedit.text.toString()


//

        var sipAmount = calculateSIP(amt.toDouble(), interest.toDouble(), 12, timep.toInt())


        var aa=(sipAmount).toLong()
        binding.siptotalwealthres.setText(aa.toString())
        var amtspnd=amt.toLong()*12*timep.toInt()
        //binding.
        binding.sipwealthres.setText(amtspnd.toString())

        var pieval1= aa-amtspnd.toFloat()
        var  pieval2=amtspnd.toFloat()
        piegen(amtspnd,aa,pieval1,pieval2)



    }

    private fun calculateSIP(P : Double, r :Double , n : Int, t : Int) : Double {
        val rate = r / (100 * n)
        val nt = n * t
        return P * ((1 + rate).pow(nt) - 1) / rate
    }
}
