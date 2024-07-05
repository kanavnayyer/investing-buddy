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
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
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
import kotlin.math.pow

class EmiCalculatorActivity : AppCompatActivity() {
    private var pieval1:Int=0
    private var pieval2:Int=0
    lateinit var pieChart: PieChart
    private  lateinit var lumamttxt: EditText       //findViewById<EditText>(R.id.Lumpsumpamount)
    private lateinit var lumopyear: EditText             //findViewById<EditText>(R.id.Lumpsumpyearedit)
    private lateinit var lumrear: EditText                  //findViewById<EditText>(R.id.expectedreturnedit)
    private lateinit var totalwealth: TextView    //findViewById<TextView>(R.id.lumpsumtotalwealthres)
    private lateinit var profitwealth: TextView //findViewById<TextView>(R.id.lumpsumwealthres)
    private lateinit var calsum: Button    //findViewById<Button>(R.id.calculatesum)
    private lateinit var recomentext: TextView
    private  lateinit var slidr: Slider




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_emi_calculator)
        findViewById<ConstraintLayout>(R.id.cc).setOnClickListener { it.hideKeyboard() }
        lumamttxt=findViewById<EditText>(R.id.Lumpsumpamount)
        lumopyear=findViewById<EditText>(R.id.Lumpsumpyearedit)
        lumrear=findViewById<EditText>(R.id.expectedreturnedit)
        totalwealth=findViewById<TextView>(R.id.lumpsumtotalwealthres)
        profitwealth=findViewById<TextView>(R.id.lumpsumwealthres)
        calsum=findViewById<Button>(R.id.calculatesum)
        recomentext=findViewById(R.id.reccommendation)
        slidr=findViewById(R.id.sliderate)


        slidr.addOnChangeListener { slider, value, fromUser ->
            lumrear.setText(value.toInt().toString())
        }
        calsum.setOnClickListener {
            if(lumamttxt.text.isEmpty()||lumopyear.text.isEmpty()|| lumrear.text.isEmpty()){

                Snackbar.make(it,"Pleasee add all  credentials", Snackbar.LENGTH_SHORT).show()
            }
            else if(lumopyear.text.toString().toLong()>31){
                Snackbar.make(it,"please enter less than 31 year", Snackbar.LENGTH_SHORT).show()
            }
            else if (lumrear.text.toString().toInt()>15){
                Snackbar.make(it,"Please enter less than 16%", Snackbar.LENGTH_SHORT).show()
            }
            else{
                calculator()
            }
        }}
    private fun calculator() {


        var principal = lumamttxt.text.toString().toDouble()
        val timefor = lumopyear.text.toString().toDouble()
        val rateatWhich = lumrear.text.toString().toDouble()



        val emp = String.format("%.2f",  emiCalculator(principal, rateatWhich, timefor)).toFloat()

        profitwealth.setText(emp.toString())
        totalwealth.setText((emp *timefor*12).toFloat().toString())
        val togive=emp*12*timefor

        pieval1= (((principal/togive))*100).toInt()
        pieval2=100-pieval1

        piegen(togive.toLong(),principal.toLong())

    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    private  fun piegen(ganed: Long, initial:Long){
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

        pieChart.centerText="Total  ${ganed.toInt()}\nTook ${initial.toInt()}"
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
    private  fun emiCalculator(p: Double, r: Double, t: Double): Double {
        var emi: Double
        val r = r / (12 * 100)
        val t = t * 12
        emi = (p * r * (1 + r).pow(t)) / ((1 + r).pow(t) - 1)
        return emi
    }}