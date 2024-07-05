package com.awesome.investingbuddy.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awesome.investingbuddy.R
import com.awesome.investingbuddy.adapters.DBContract
import com.awesome.investingbuddy.adapters.DBHelper

class CredentialActivity : AppCompatActivity() {
    //
//private  val txtsec:EditText=findViewById(R.id.addAmountforsec)
//    private  val txtvalleft:TextView=findViewById(R.id.leftAmntText)
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credential)


//leftshow();
        setTotal();

        val spinner: Spinner = findViewById(R.id.alloptions)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.Sectors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        var addbut:Button=findViewById(R.id.addbutton2);


        val txtsec:EditText=findViewById(R.id.addAmountforsec)
        val txtvalleft:TextView=findViewById(R.id.leftAmntText)
        val radiogrp:RadioGroup=findViewById(R.id.Amount)
//    txtvalleft.visibility=View.VISIBLE
        // txtsec.visibility=View.INVISIBLE
        radiogrp.setOnCheckedChangeListener { _, checkedId: Int ->

            // Here if the checkId is METRIC UNITS view then make the view visible else US UNITS view.
            if (checkedId == R.id.sectoramount) {
                txtvalleft.visibility=View.INVISIBLE
                txtsec.visibility=View.VISIBLE

            } else {
                txtvalleft.visibility=View.VISIBLE
                txtsec.visibility=View.INVISIBLE
                leftshow();
                //txtsec.text.clear()
            }
        }
        var tota:EditText=findViewById(R.id.totalamnt)
        tota.setOnClickListener {    Toast.makeText(this,"By changing this your whole data will be deleted",Toast.LENGTH_SHORT).show();
            showDialog("hh"); }
        addbut.setOnClickListener {

            var tota:EditText=findViewById(R.id.totalamnt)
            if(tota.text.isEmpty()||txtsec.text.isEmpty()){
                Toast.makeText(this,"Please fill all the Investment collumns",Toast.LENGTH_SHORT).show()
            }else{


                if(tota.text.toString().toFloat()==0f){
                    Toast.makeText(this,"Sum of your selected Investments is equal to your Total investment",Toast.LENGTH_SHORT).show()
                }else {
                    if(txtsec.text.toString().toFloat()>tota.text.toString().toFloat()){
                        Toast.makeText(this,"Entered Amount is greater then left invested Amount",Toast.LENGTH_SHORT).show()
                    }else{
                        add(
                            tota.text.toString(),
                            spinner.selectedItem.toString(),
                            "hello",
                            txtsec.text.toString()
                        )

                        //leftshow();
                        //tota.setText((tota.text.toString().toFloat()-txtsec.text.toString().toFloat()).toString())
                        txtsec.text.clear()
                    }}}
        }

        var deletebut:Button=findViewById(R.id.addbutton3)
        var openPie:Button=findViewById(R.id.addbutton)

        deletebut.setOnClickListener {
            showDialog("hello")
            Toast.makeText(this,"Your data is deleted Please enter new Investment Information",Toast.LENGTH_SHORT).show();
        }

        openPie.setOnClickListener {
            var db= DBHelper(this)
            var c=db.readData()
            if(c!!.moveToFirst()){
                val intent=Intent(this,Dbcheck::class.java);
                startActivity(intent);}
            else{
                Toast.makeText(this,"You don't have any investment to show" ,
                    Toast.LENGTH_SHORT).show()
            }
        }


        var c:ConstraintLayout=findViewById(R.id.cc)
        c.setOnClickListener { it.hideKeyboard() }



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

    fun add(total:String,sel:String,color:String,left:String){
        var db= DBHelper(this);

        db.insertData(total,sel,color,left)



    }
    @SuppressLint("Range")
    fun leftshow(){
        var lf:TextView=findViewById(R.id.leftAmntText)
        var db= DBHelper(this);
        var cursor=db.readData();
        var total=ArrayList<String>()
        var selamount=ArrayList<String>()
        var totalinves=0;
        if(cursor!!.moveToFirst()){
            cursor!!.moveToFirst();
            total.add(cursor.getString((cursor!!.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_NAME))))
            selamount.add(cursor.getString((cursor!!.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_ADDRESS))))
            totalinves+=Integer.parseInt(selamount.get(selamount.size-1))
            while(cursor!!.moveToNext()){
                total.add(cursor.getString((cursor!!.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_NAME))))
                selamount.add(cursor.getString((cursor!!.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_ADDRESS))))
                totalinves+=Integer.parseInt(selamount.get(selamount.size-1))
            }
            if(total.get(0).toFloat()-totalinves<0){
                total.set(0,totalinves.toString());
                lf.setText("0.0");
            }else {
                lf.setText((total.get(0).toInt()-totalinves).toString());
            } // on below line we are setting pie data set


//var m=cursor.getString(cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_AGE))
            //text.setText(m)
        }

    }
    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_layout)
        var text=dialog.findViewById<TextView>(R.id.tvBody)
        text.setText("You want to Delete your whole data ?")
        val yesBtn = dialog.findViewById(R.id.btn_yes) as Button
        val noBtn = dialog.findViewById(R.id.btn_no) as Button
        yesBtn.setOnClickListener {
            var db= DBHelper(this)
            db.del();
            setTotal();
            Toast.makeText(this,"Your Investment data is deleted",Toast.LENGTH_SHORT).show();
            dialog.dismiss()
        }
        noBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }


    @SuppressLint("Range")
    fun setTotal() {
        var db = DBHelper(this);
        var cursor = db.readData();
        var total = ArrayList<String>()

        if (cursor!!.moveToFirst()) {
            cursor!!.moveToFirst();
            total.add(cursor.getString((cursor!!.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_NAME))))
            var total2:EditText=findViewById(R.id.totalamnt)
            total2.setText(total.get(0));
        }else{
            var total2:EditText=findViewById(R.id.totalamnt)
            total2.text.clear();
        }



    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
@SuppressLint("Range")

class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        val spinner: Spinner = findViewById(R.id.alloptions)
        spinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}