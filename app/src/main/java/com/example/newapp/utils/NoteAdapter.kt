package com.example.newapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.data.entities.Note
import com.example.newapp.databinding.NoteItemsBinding

class NoteAdapter (
    val context:Context,
    var dataSEt: MutableList<Note>,
    val listenner:ClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(val binding: NoteItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    {
        fun onDeleteclicklistenner(){
            binding.trash.setOnClickListener {

                listenner.onItemDeleted(adapterPosition)
            }
        }



    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {


        return NoteViewHolder(
            NoteItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val noteContent = dataSEt[position].content

        holder.binding.text.text=noteContent
        holder.onDeleteclicklistenner()



    }


    override fun getItemCount(): Int {
        return dataSEt.size
    }

    interface ClickListener {
        fun onItemDeleted(position: Int)

        fun onAddItemToFavorites(position: Int)
    }


}
