package com.example.androidtema3.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidtema3.Fragments.UsersFragment
import com.example.androidtema3.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment()
    }

    private fun openFragment(){
        val fragment = UsersFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main,fragment,"usersFragment")
            .commit()
    }
}