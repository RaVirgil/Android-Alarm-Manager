package com.example.androidtema3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.androidtema3.Adapters.UserListAdapter
import com.example.androidtema3.Models.User
import com.example.androidtema3.R
import kotlinx.android.synthetic.main.fragment_users.*
import org.json.JSONArray
import org.json.JSONObject

class UsersFragment : Fragment(),UserListAdapter.OnUserListener {

    private var userList: MutableList<User> = mutableListOf()
    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_users.apply {
            refreshUserList()
            layoutManager = LinearLayoutManager(activity)
            adapter = UserListAdapter(userList,this@UsersFragment)
        }

    }

    private fun refreshUserList(){
        val queue = Volley.newRequestQueue(this.context)
        val url = "https://jsonplaceholder.typicode.com/users"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                handleResponse(response)
            },

            Response.ErrorListener { error ->
                Toast.makeText(
                    this.context,
                    "That didn't work! :($error",
                    Toast.LENGTH_SHORT
                ).show()
            })

        queue.add(stringRequest)
    }


    private fun findUser(name : String, email: String): User? {
       return userList.find { user->user.email.equals(email) && user.name.equals(name) }
    }
    private fun handleResponse(response: String){

        val jsonArray = JSONArray(response)
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray[i] as JSONObject
            addUser(jsonObject.getString("id"),jsonObject.getString("name"), jsonObject.getString("email"))
        }
    }

    private fun addUser(id: String,name: String, email: String){
        userList.add(User(id.toInt(),name,email))
    }

    override fun onUserClick(position: Int) {
        var fragment = ToDoFragment.newInstance()
        fragment.user=userList[position]
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_users,fragment,"to_do_fragment")
            .addToBackStack(fragment.toString())
            .commit()
    }
}
