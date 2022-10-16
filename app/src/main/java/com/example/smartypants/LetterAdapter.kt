package com.example.smartypants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smartypants.database.Definitions
import com.example.smartypants.databinding.ItemViewBinding

class LetterAdapter(private val onItemClick: (Definitions) -> Unit): ListAdapter<Definitions, LetterAdapter.LetterViewHolder>(
    DiffUtilCallback) {

    class LetterViewHolder(private var binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(definition: Definitions){
            binding.letterText.text = definition.letter
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val viewHolder = LetterViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClick(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        private val DiffUtilCallback = object:DiffUtil.ItemCallback<Definitions>(){
            override fun areItemsTheSame(oldItem: Definitions, newItem: Definitions): Boolean {
                return oldItem.letter == newItem.letter
            }

            override fun areContentsTheSame(oldItem: Definitions, newItem: Definitions): Boolean {
               return oldItem.letter == newItem.letter
            }
        }
    }



}