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
import com.example.androidtema3.Adapters.ToDoListAdapter
import com.example.androidtema3.Models.ToDo
import com.example.androidtema3.Models.User
import com.example.androidtema3.R
import kotlinx.android.synthetic.main.fragment_to_do.*
import org.json.JSONArray
import org.json.JSONObject

class ToDoFragment : Fragment(),ToDoListAdapter.OnToDoListener {

    var user: User?=null
    private var toDoList: MutableList<ToDo> = mutableListOf()
    companion object {
        fun newInstance() = ToDoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //refreshToDoList() //primesc lista de toDo insa nu apare in recycler view, dar daca adaug
        //manual in lista apare... ??????
        toDoList.add(ToDo("delectus aut autem"))
        toDoList.add(ToDo("quis ut nam facilis et officia qui"))
        toDoList.add(ToDo("fugiat veniam minus"))
        recycler_view_to_do.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = ToDoListAdapter(toDoList,this@ToDoFragment)
        }
    }

    private fun refreshToDoList(){
        val queue = Volley.newRequestQueue(this.context)
        val url = "https://jsonplaceholder.typicode.com/todos?userId="+user!!.id

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

    private fun handleResponse(response: String){
        val jsonArray = JSONArray(response)
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray[i] as JSONObject
            addToDo(jsonObject.getString("title"))
        }
    }

    private fun addToDo(title: String){
        toDoList.add(ToDo(title))
    }

    override fun onToDoClick(position: Int) {
        var fragment = AlarmSetFragment.newInstance()
        fragment.toDo=toDoList[position]
        fragmentManager!!.beginTransaction()
            .replace(R.id.fragment_to_do,fragment,"alarm_fragment")
            .addToBackStack(fragment.toString())
            .commit()
    }

}
