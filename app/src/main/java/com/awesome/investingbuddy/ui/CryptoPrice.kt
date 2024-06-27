package com.awesome.investingbuddy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager

import com.awesome.investingbuddy.adapters.cAdapter
import com.awesome.investingbuddy.databinding.FragmentCryptoPriceBinding
import com.awesome.investingbuddy.util.AllApi.Companion.apikCrypto


import com.awesome.rev.cryptoModel

import okhttp3.*
import okhttp3.Request.Builder
import org.chromium.base.ThreadUtils.runOnUiThread
import org.json.JSONObject
import java.io.IOException
import java.util.Locale

class CryptoPrice : Fragment() {
    private val dataa = ArrayList<cryptoModel>()
private  val adapter = cAdapter(dataa)
    private lateinit var binding: FragmentCryptoPriceBinding


// add own key

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentCryptoPriceBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchprice()

        binding.idEdtCurrency.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filter(newText)
                }
                return true
            }
        })


    }




    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist = ArrayList<cryptoModel>()


        for (item in dataa) {

            if(item.symbol.toLowerCase().contains(text.toLowerCase(Locale.getDefault()))||
                item.name.toLowerCase().contains(text.toLowerCase(
                    Locale.getDefault()))){
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }


                adapter.setFilteredList(filteredlist)

        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(activity, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {

            // list to our adapter class.


            adapter.filterList(filteredlist)
        }
    }
    private fun fetchprice() {

        val client = OkHttpClient()

        val request = Builder()
            .url("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest")
            .addHeader("X-CMC_PRO_API_KEY", apikCrypto)
            .addHeader("Accept", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(
                    activity,
                    "Please check Internet Connection",
                    Toast.LENGTH_SHORT
                ).show()
            }



            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonData = response.body?.string()
                    val jsonObject = JSONObject(jsonData)

                    val data = jsonObject.getJSONArray("data")





                    for (i in 0 until data.length()) {
                        val currency = data.getJSONObject(i)
                        val symbol = currency.getString("symbol")
                        val name = currency.getString("name")
                        val Price = currency.getJSONObject("quote").getJSONObject("USD")
                            .getString("price")

                        dataa.add(cryptoModel(symbol, name, Price))


                    }

                    runOnUiThread {
                        val recyclerView = binding.recy

                        recyclerView.apply {
                            // st a LinearLayoutManager to handle Android
                            // RecyclerView behavior
                            layoutManager = LinearLayoutManager(activity)


                        }



                        recyclerView.adapter = adapter



                    }


                }
            }


        })



    }

}