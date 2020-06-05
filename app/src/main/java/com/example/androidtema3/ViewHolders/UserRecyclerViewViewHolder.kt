package com.example.androidtema3.ViewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtema3.Adapters.UserListAdapter
import com.example.androidtema3.Models.User
import com.example.androidtema3.R
import kotlinx.android.synthetic.main.users_recycler_layout.view.*

class UserRecyclerViewViewHolder(inflater: LayoutInflater,parent:ViewGroup,onUserListener:UserListAdapter.OnUserListener):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.users_recycler_layout,parent,false))
    ,View.OnClickListener{
    private var name: TextView? =null
    private var email: TextView? =null
    private var onUserListener: UserListAdapter.OnUserListener? = null
    
    init{
          name = itemView.findViewById(R.id.card_view_user_name)
          email = itemView.findViewById(R.id.card_view_user_email)
          this.onUserListener=onUserListener
          itemView.setOnClickListener(this)
    }

    fun bind(user: User) {
        name!!.text =user.name
        email!!.text =user.email
    }

    override fun onClick(v: View?) {
        onUserListener!!.onUserClick(adapterPosition)
    }
}