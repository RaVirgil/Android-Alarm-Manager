package com.example.androidtema3.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtema3.Models.User
import com.example.androidtema3.ViewHolder.UserRecyclerViewViewHolder


class UserListAdapter(private var list: MutableList<User>,private var onUserListener: OnUserListener)
    : RecyclerView.Adapter<UserRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyclerViewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserRecyclerViewViewHolder(inflater, parent,this.onUserListener)
    }

    override fun onBindViewHolder(holder: UserRecyclerViewViewHolder, position: Int) {
        val user: User = list[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = list.size

    public interface OnUserListener{
        fun onUserClick(position:Int);
    }
}