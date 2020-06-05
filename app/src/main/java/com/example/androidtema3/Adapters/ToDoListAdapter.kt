package com.example.androidtema3.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtema3.Models.ToDo

import com.example.androidtema3.ViewHolders.ToDoRecyclerViewViewHolder


class ToDoListAdapter(private var list: MutableList<ToDo>,private var onToDoListener: OnToDoListener)
    : RecyclerView.Adapter<ToDoRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoRecyclerViewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ToDoRecyclerViewViewHolder(inflater, parent,this.onToDoListener)
    }

    override fun onBindViewHolder(holder: ToDoRecyclerViewViewHolder, position: Int) {
        val toDo: ToDo = list[position]
        holder.bind(toDo)
    }

    override fun getItemCount(): Int = list.size

    public interface OnToDoListener{
        fun onToDoClick(position:Int);
    }

}