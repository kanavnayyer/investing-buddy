package com.awesome.investingbuddy.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awesome.investingbuddy.R

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent

import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.awesome.investingbuddy.adapters.DBContract2
import com.awesome.investingbuddy.adapters.DBCrypto

class cryptodatbasecreator : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cryptodatabassecreator)

        val spinner: Spinner = findViewById(R.id.alloptions)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.Cryptos,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        var selamnt:EditText=findViewById(R.id.addAmountforsec)
        var left:TextView=findViewById(R.id.leftAmntText);
        val radiogrp: RadioGroup =findViewById(R.id.Amount)

        radiogrp.setOnCheckedChangeListener { _, checkedId: Int ->

            // Here if the checkId is METRIC UNITS view then make the view visible else US UNITS view.
            if (checkedId == R.id.sectoramount) {
                left.visibility= View.INVISIBLE
                selamnt.visibility= View.VISIBLE

            } else {
                left.visibility= View.VISIBLE
                selamnt.visibility= View.INVISIBLE
                showleft();
                //txtsec.text.clear()
            }
        }
        var totalamnr:EditText=findViewById(R.id.totalamnt);
        var db= DBCrypto(this);
        var cursor=db.readData();
        if(cursor!!.moveToFirst()){
            totalamnr.setText(cursor.getString(cursor.getColumnIndex(DBContract2.DBEntry.COLUMN_Total)))
        }
        totalamnr.setOnClickListener {
            Toast.makeText(this,"By changing this your whole data will be deleted",Toast.LENGTH_SHORT).show();
            showDialog("hh");
        }

        var addbut: Button =findViewById(R.id.addbutton2);
        var show:Button=findViewById(R.id.addbutton)
        addbut.setOnClickListener {

            add1();
        }

        show.setOnClickListener {
            var intent= Intent(this,cryptoLast::class.java)
            startActivity(intent);
        }
        var deleteAll:Button=findViewById(R.id.addbutton3)
        deleteAll.setOnClickListener {
            deleteAll1();
        }


        val actionBar: ActionBar? = supportActionBar

        findViewById<ConstraintLayout>(R.id.cc).setOnClickListener { it.hideKeyboard() };


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
    fun add1(){
        var totalamnt:EditText=findViewById(R.id.totalamnt);
        var selamnt:EditText=findViewById(R.id.addAmountforsec)
        val spinner: Spinner = findViewById(R.id.alloptions)
        var other:EditText=findViewById(R.id.NAMEBYUSER)

        var db=DBCrypto(this);
        if(totalamnt.text.isEmpty()|| selamnt.text.isEmpty()){
            Toast.makeText(this,"Fill collumns to proceed",Toast.LENGTH_SHORT).show();
        }else{


            if(spinner.selectedItem.toString()=="Other"){
                db.insertData(totalamnt.text.toString(),selamnt.text.toString(),other.text.toString());
                selamnt.text.clear();
                other.text.clear()
            }else {

                db.insertData(
                    totalamnt.text.toString(),
                    selamnt.text.toString(),
                    spinner.selectedItem.toString()
                );
                selamnt.text.clear();
            }
        }
    }

    fun deleteAll1(){
        showDialog("hello");
    }
    @SuppressLint("Range")
    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_layout)

        val yesBtn = dialog.findViewById(R.id.btn_yes) as Button
        val noBtn = dialog.findViewById(R.id.btn_no) as Button
        yesBtn.setOnClickListener {
            var db=DBCrypto(this);
            var cur=db.readData();
            if(cur.moveToFirst()){
                db.del();
                var totalamnr:EditText=findViewById(R.id.totalamnt);
                totalamnr.text.clear()
                Toast.makeText(this,"Your Investment data is deleted",Toast.LENGTH_SHORT).show();
                dialog.dismiss()}else{
                Toast.makeText(this,"There is no data to Delete",Toast.LENGTH_SHORT).show();
            }
        }
        noBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }
    @SuppressLint("Range")
    fun showleft(){
        var left:TextView=findViewById(R.id.leftAmntText);
        var db=  DBCrypto(this)
        var cursor=db.readData()
        if(cursor!!.moveToFirst()){
            cursor.moveToFirst();
            var seltotal=0f;
            var total=cursor.getString((cursor!!.getColumnIndex(DBContract2.DBEntry.COLUMN_Total))).toFloat()
            seltotal+=cursor.getString((cursor!!.getColumnIndex(DBContract2.DBEntry.COLUMN_Invested))).toFloat()
            while(cursor!!.moveToNext()){
                seltotal+=cursor.getString((cursor!!.getColumnIndex(DBContract2.DBEntry.COLUMN_Invested))).toFloat()
            }

            left.setText((total-seltotal).toString())
        }else{
            left.setText("0");
        }

    }
    @SuppressLint("Range")
    fun leftint():Float{
        var db=  DBCrypto(this)
        var cursor=db.readData()
        var seltotal=0f;
        var total=0f;
        if(cursor!!.moveToFirst()){
            cursor.moveToFirst();

            total=cursor.getString((cursor!!.getColumnIndex(DBContract2.DBEntry.COLUMN_Total))).toFloat()
            seltotal+=cursor.getString((cursor!!.getColumnIndex(DBContract2.DBEntry.COLUMN_Invested))).toFloat()
            while(cursor!!.moveToNext()){
                seltotal+=cursor.getString((cursor!!.getColumnIndex(DBContract2.DBEntry.COLUMN_Invested))).toFloat()
            }
        }
        return total-seltotal;
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}