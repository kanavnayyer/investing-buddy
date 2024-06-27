package com.awesome.investingbuddy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awesome.investingbuddy.R
import com.awesome.rev.cryptoModel


class cAdapter(private var mlist:List<cryptoModel>):RecyclerView.Adapter<cAdapter.ViewHolder>() {



    fun filterList(filterList: ArrayList<cryptoModel>) {
        // below line is to add our filtered
        // list in our course array list.
        mlist = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }



    fun setFilteredList(mlist: List<cryptoModel>){
        this.mlist=mlist
    }

    class ViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView) {

        val symbolTV:TextView = itemView.findViewById(R.id.idTVSymbol)
        val rateTV :TextView= itemView.findViewById(R.id.idTVRate);
        val  nameTV:TextView = itemView.findViewById(R.id.idTVName)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cryptoitem,parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return mlist.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val items=mlist[position]
        holder.symbolTV.text=mlist[position].symbol
        holder.rateTV.text=items.price
        holder.nameTV.text=items.name



    }


}