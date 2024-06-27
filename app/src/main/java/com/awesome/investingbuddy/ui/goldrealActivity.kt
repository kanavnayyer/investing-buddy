package com.awesome.investingbuddy.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awesome.investingbuddy.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

class goldrealActivity : AppCompatActivity() {



    //   private lateinit var binding: goldrealActivity
    private var pieval1=0
    private var pieval2=0
    lateinit var pieChart: PieChart
    private  lateinit var lumamttxt: EditText       //findViewById<EditText>(R.id.Lumpsumpamount)
    private lateinit var lumopyear:EditText             //findViewById<EditText>(R.id.Lumpsumpyearedit)
    private lateinit var lumrear:EditText                  //findViewById<EditText>(R.id.expectedreturnedit)
    private lateinit var totalwealth: TextView    //findViewById<TextView>(R.id.lumpsumtotalwealthres)
    private lateinit var profitwealth:TextView //findViewById<TextView>(R.id.lumpsumwealthres)
    private lateinit var calsum: Button    //findViewById<Button>(R.id.calculatesum)
    private lateinit var recomentext:TextView
    private  lateinit var slidr: Slider
    private var checkmath:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goldreal)


        lumamttxt=findViewById<EditText>(R.id.Lumpsumpamount)
        lumopyear=findViewById<EditText>(R.id.Lumpsumpyearedit)
        lumrear=findViewById<EditText>(R.id.expectedreturnedit)
        totalwealth=findViewById<TextView>(R.id.lumpsumtotalwealthres)
        profitwealth=findViewById<TextView>(R.id.lumpsumwealthres)
        calsum=findViewById<Button>(R.id.calculatesum)
        recomentext=findViewById(R.id.reccommendation)
        slidr=findViewById(R.id.sliderate)
        val radiogrp: RadioGroup =findViewById(R.id.Amount)
        //   goldreturn()
       realestate()
        radiogrp.setOnCheckedChangeListener { _, checkedId: Int ->

            // Here if the checkId is METRIC UNITS view then make the view visible else US UNITS view.
            if (checkedId == R.id.Goldbtnt) {
                goldreturn()
                lumamttxt.text.clear()
                lumopyear.text.clear()
                lumrear.text.clear()
                totalwealth.setText("")
                profitwealth.setText("")
                recomentext.setText("we reccommend for 10 years at 6%")
                slidr.value=0f
            }


            else if (checkedId==R.id.Silverbtn){
                Silverbtn()
                lumamttxt.text.clear()
                lumopyear.text.clear()
                lumrear.text.clear()
                totalwealth.setText("")
                profitwealth.setText("")
                recomentext.setText("we reccommend for 10 years at 7%")
                slidr.value=0f
            }
            else{
                realestate()
                lumamttxt.text.clear()
                lumopyear.text.clear()
                lumrear.text.clear()
                totalwealth.setText("")
                profitwealth.setText("")
                recomentext.setText("we reccommend for 20 years at 5%")
                slidr.value=0f
            }
        }

        var c: ConstraintLayout =findViewById(R.id.cc)
        c.setOnClickListener { it.hideKeyboard() };

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
    private  fun  goldreturn(){
        lumamttxt=findViewById<EditText>(R.id.Lumpsumpamount)
        lumopyear=findViewById<EditText>(R.id.Lumpsumpyearedit)
        lumrear=findViewById<EditText>(R.id.expectedreturnedit)
        if(lumamttxt.text.isEmpty()||lumrear.text.isEmpty()||lumrear.text.isEmpty()){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
        }else{
            slidr.addOnChangeListener { slider, value, fromUser ->
                lumrear.setText(value.toInt().toString())
            }
            calsum.setOnClickListener {
                if(lumamttxt.text.isEmpty()||lumopyear.text.isEmpty()|| lumrear.text.isEmpty()){

                    Snackbar.make(it,"Pleasee add all  credentials",Snackbar.LENGTH_SHORT).show()
                }
                else if(lumopyear.text.toString().toLong()>31){
                    Snackbar.make(it,"please enter less than 31 year",Snackbar.LENGTH_SHORT).show()
                }
                else if (lumrear.text.toString().toInt()>15){
                    Snackbar.make(it,"Please enter less than 16%",Snackbar.LENGTH_SHORT).show()
                }
                else{
                    calculator()
                }
            }}
    }
    private  fun Silverbtn(){
        lumamttxt=findViewById<EditText>(R.id.Lumpsumpamount)
        lumopyear=findViewById<EditText>(R.id.Lumpsumpyearedit)
        lumrear=findViewById<EditText>(R.id.expectedreturnedit)
        if(lumamttxt.text.isEmpty()||lumrear.text.isEmpty()||lumrear.text.isEmpty()){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
        }else{
            slidr.addOnChangeListener { slider, value, fromUser ->
                lumrear.setText(value.toInt().toString())
            }
            calsum.setOnClickListener {
                if (lumamttxt.text.isEmpty() || lumopyear.text.isEmpty() || lumrear.text.isEmpty()) {

                    Snackbar.make(it, "Pleasee add all  credentials", Snackbar.LENGTH_SHORT).show()
                } else if (lumopyear.text.toString().toLong() > 31) {
                    Snackbar.make(it, "please enter less than 31 year", Snackbar.LENGTH_SHORT).show()
                } else if (lumrear.text.toString().toInt() > 15) {
                    Snackbar.make(it, "Please enter less than 16%", Snackbar.LENGTH_SHORT).show()
                } else {
                    calculator()
                }
            }}

    }
    private fun realestate(){
        lumamttxt=findViewById<EditText>(R.id.Lumpsumpamount)
        lumopyear=findViewById<EditText>(R.id.Lumpsumpyearedit)
        lumrear=findViewById<EditText>(R.id.expectedreturnedit)
        if(lumamttxt.text.isEmpty()||lumrear.text.isEmpty()||lumrear.text.isEmpty()){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
        }else {
            slidr.addOnChangeListener { slider, value, fromUser ->
                lumrear.setText(value.toInt().toString())
            }
            calsum.setOnClickListener {
                if (lumamttxt.text.isEmpty() || lumopyear.text.isEmpty() || lumrear.text.isEmpty()) {

                    Snackbar.make(it, "Pleasee add all  credentials", Snackbar.LENGTH_SHORT).show()
                } else if (lumopyear.text.toString().toLong() > 31) {
                    Snackbar.make(it, "please enter less than 31 year", Snackbar.LENGTH_SHORT)
                        .show()
                } else if (lumrear.text.toString().toInt() > 15) {
                    Snackbar.make(it, "Please enter less than 16%", Snackbar.LENGTH_SHORT).show()
                } else {
                    calculator()
                }
            }
        }
    }
    private fun calculator() {
        lumamttxt=findViewById<EditText>(R.id.Lumpsumpamount)
        lumopyear=findViewById<EditText>(R.id.Lumpsumpyearedit)
        lumrear=findViewById<EditText>(R.id.expectedreturnedit)
        if(lumamttxt.text.isEmpty()||lumrear.text.isEmpty()||lumrear.text.isEmpty()){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
        }else{
            var maxx=0;
            var amntspend = lumamttxt.text.toString().toLong()
            val yearfor = lumopyear.text.toString().toInt()
            val returnfor = lumrear.text.toString().toInt()
            if(amntspend>42000){ checkmath=true
                maxx = (amntspend % 100).toInt()
                amntspend/=100
            }
            else{
                checkmath=false
            }

            var a: Double = Math.pow(1 + (returnfor.toDouble() / 100), yearfor.toDouble())
            var generatedamnt = amntspend.toFloat() * a.toFloat()

            generatedamnt = ((generatedamnt * 100.0).roundToInt() / 100.0).toFloat()
            if(checkmath==true){generatedamnt*=100+maxx
                amntspend= amntspend*100+maxx}
            var left = generatedamnt - amntspend.toFloat()

            profitwealth.setText(left.toLong().toString())
            totalwealth.setText(generatedamnt.toLong().toString())
            pieval1= ((left/generatedamnt)*100).toInt()

            pieval2=100-pieval1
            piegen(left.toLong(),amntspend.toLong())
//amntspend/=100}
//    else{
//        checkmath=false
//    }

            //   if(checkmath==true){generatedamnt*=100 }

        }}
    fun piegen(ganed: Long, initial:Long){
        pieChart = findViewById(R.id.pieChart)
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

        pieChart.centerText="weathgained ${ganed.toInt()}\nInitial wealth${initial.toInt()}"
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

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}
