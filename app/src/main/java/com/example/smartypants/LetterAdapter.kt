package com.example.smartypants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class LetterAdapter: RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    class LetterViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val letterText: TextView = view.findViewById(R.id.letterText)
        val letterImage: ImageView = view.findViewById(R.id.imageView)
    }

    private val list = ('A').rangeTo('Z').toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return LetterViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list[position]
        holder.letterText.text = item.toString()
        holder.letterImage.setOnClickListener {
            val action = LetterListFragmentDirections.actionLetterListFragmentToDefinitionFragment3()
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}