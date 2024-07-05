package com.awesome.investingbuddy.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import android.Manifest

class StockPiechartActivity : AppCompatActivity() {
    lateinit var pieChart: PieChart
    @SuppressLint("SuspiciousIndentation")
    private var cpd:Dialog?=null
    private var load:Dialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_stock_piechart)

        pieChart = findViewById(R.id.pieChart)

        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(false)
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

        entries.add(PieEntry(16f))
        entries.add(PieEntry(5f,))
        entries.add(PieEntry(7f,))
        entries.add(PieEntry(5f))
        entries.add(PieEntry(8f,))
        entries.add(PieEntry(11f,))
        entries.add(PieEntry(15f,))
        entries.add(PieEntry(8f,))
        entries.add(PieEntry(14f))
        entries.add(PieEntry(7f))
        entries.add(PieEntry(4f))

        // on below line we are setting pie data set
        val dataSet = PieDataSet(entries, "Our\nRecommendation")

        // on below line we are setting icons.
        dataSet.setDrawIcons(false)

        // on below line we are setting slice for pie
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)

        dataSet.selectionShift = 5f
        pieChart.centerText=dataSet.label
        // add a lot of colors to l
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.teal_200))
        colors.add(resources.getColor(R.color.purple_200))
        colors.add(resources.getColor(R.color.yellow))
        colors.add(resources.getColor(R.color.orange))
        colors.add(resources.getColor(R.color.oran))
        colors.add(resources.getColor(R.color.ocean_blue))
        colors.add(resources.getColor(R.color.darkblue))
        colors.add(resources.getColor(R.color.blue))
        colors.add(resources.getColor(R.color.lightg))
        colors.add(resources.getColor(R.color.red))
        colors.add(resources.getColor(R.color.pur))
        colors.add(resources.getColor(R.color.black))

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
        // loading char
//


        val user:TextView=findViewById(R.id.userdata)
        user.setOnClickListener { val intent=Intent(this,CredentialActivity::class.java)
            startActivity(intent)}







        //float button start





        val btnstk=findViewById<FloatingActionButton>(R.id.stkreferbtnfloat)




        btnstk.setOnClickListener {
            showonbuttonclicked()

        }

//save function





        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )

        // this is the card view whose screenshot
        // we will take in this article
        // get the view using fin view bt id

        // on click of this button it will capture
        // screenshot and save into gallery
        val captureButton = findViewById<View>(R.id.savefrmrecommendation)
        captureButton.setOnClickListener {
            // get the bitmap of the view using
            // getScreenShotFromView method it is
            // implemented below
            // shd()
            shwload()
            pieChart.centerText="Generated by \nInvestment Buddy"
            val tosave=findViewById<RelativeLayout>(R.id.relativedimen)
            val bitmap = getScreenShotFromView(tosave)

            // if bitmap is not null then
            // save it to gallery
            if (bitmap != null) {
                saveMediaToStorage(bitmap)
            }
        }

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

    private fun getScreenShotFromView(v: View): Bitmap? {
        // create a bitmap object

        var screenshot: Bitmap? = null
        try {
            // inflate screenshot object
            // with Bitmap.createBitmap it
            // requires three parameters
            // width and height of the view and
            // the background color
            screenshot =
                Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshot)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("Investing Buddy", "Failed to capture screenshot because:" + e.message)
        }
        // return the bitmap
        return screenshot
    }


    // this method saves the image to gallery
    private fun saveMediaToStorage(bitmap: Bitmap){

        val filename = "${System.currentTimeMillis()}.jpg"





        // Output stream
        var fos: OutputStream? = null

        // For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // getting the contentResolver
            this.contentResolver?.also { resolver ->

                // Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    // putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename+".jpg")
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                // Inserting the contentValues to
                // contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                // Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it)
                }


            }
        } else {
            // These for devices running on android < Q
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)

        }
        //cpd()
        loadb()//for backing loading
        fos?.use {
            // Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this, "Captured View and saved to Gallery", Toast.LENGTH_SHORT).show()


            pieChart.centerText="Our\n" +
                    "Recommendation"
//            }

        }

    }











    private fun showonbuttonclicked(){
        cpd = Dialog(this@StockPiechartActivity)
        cpd?.setContentView(R.layout.stockenhanceractivitydialog)
        cpd?.show()
//
//        val heatmapstk=cpd?.findViewById<ImageView>(R.id.heatstock)
//        heatmapstk?.setOnClickListener { val intent=Intent(this,rainbow_chart::class.java)
//
//            startActivity(intent)}

//        val feargreedstk=cpd?.findViewById<ImageView>(R.id.cripfeargreedbtn)
//        feargreedstk?.setOnClickListener { val intent=Intent(this,rainbow_chart::class.java)

           // startActivity(intent)}
        val bkk=cpd?.findViewById<View>(R.id.backcripbtn)
        bkk?.setOnClickListener { back() }
    }


    private fun back(){
        if (cpd != null) {
            cpd?.dismiss()
            cpd = null
        }
    }
    private fun shwload() {
        load = Dialog(this@StockPiechartActivity)
        load?.setContentView(R.layout.load_dialogue)
        load?.show()

    }

    private fun loadb() {
        if (load != null) {
            load?.dismiss()
            load = null
        }


    }
}