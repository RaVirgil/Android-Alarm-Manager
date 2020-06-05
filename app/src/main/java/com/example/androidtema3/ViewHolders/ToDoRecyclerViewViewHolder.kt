package com.example.androidtema3.ViewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtema3.Adapters.ToDoListAdapter
import com.example.androidtema3.Adapters.UserListAdapter
import com.example.androidtema3.Fragments.ToDoFragment
import com.example.androidtema3.Models.ToDo
import com.example.androidtema3.R
import kotlinx.android.synthetic.main.users_recycler_layout.*

class ToDoRecyclerViewViewHolder(inflater: LayoutInflater, parent: ViewGroup,onToDoListener: ToDoListAdapter.OnToDoListener):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.to_do_recycler_layout,parent,false))
    ,View.OnClickListener{
    private var title: TextView?=null
    private var onToDoListener: ToDoListAdapter.OnToDoListener? = null

    init {
        title=itemView.findViewById(R.id.card_view_to_do_title)
        this.onToDoListener=onToDoListener
        itemView.setOnClickListener(this)
    }

    fun bind(toDo: ToDo) {
        title!!.text=toDo.title
    }

    override fun onClick(v: View?) {
        onToDoListener!!.onToDoClick(adapterPosition)
    }

}