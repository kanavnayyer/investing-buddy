package com.awesome.investingbuddy.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awesome.investingbuddy.R
import com.awesome.investingbuddy.models.Note
import java.util.Random


class NoteAdapter(var list: List<Note>):RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
    fun updateData(newNotes: List<Note>) {
        list = newNotes
        notifyDataSetChanged()
    }
    fun getRandomColor(): Int {
        val random = Random()
        val r = random.nextInt(150) + 100 // Red component in the range 100-250
        val g = random.nextInt(150) + 100 // Green component in the range 100-250
        val b = random.nextInt(150) + 100 // Blue component in the range 100-250
        // Create the color using RGB values
        return Color.rgb(r, g, b)
    }
   inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
       val title=view.findViewById<TextView>(R.id.titleTextView)
       val description=view.findViewById<TextView>(R.id.descriptionTextView)


   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
 val view=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item=list[position]
        holder.title.text=item.title
        holder.description.text=item.description
        holder.itemView.setBackgroundColor(getRandomColor())
    }



}