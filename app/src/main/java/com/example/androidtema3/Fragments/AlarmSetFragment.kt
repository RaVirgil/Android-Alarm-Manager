package com.example.androidtema3.Fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.androidtema3.Models.ToDo
import com.example.androidtema3.R
import com.example.androidtema3.Receivers.BcReceiver
import kotlinx.android.synthetic.main.date_picker_layout.*
import kotlinx.android.synthetic.main.fragment_alarm_set.*
import kotlinx.android.synthetic.main.time_picker_layout.*


class AlarmSetFragment : Fragment() {
    var toDo : ToDo ?= null
    var day : String ?=null
    var month : String ?=null
    var year : String ?=null
    var hour : Int ?=null
    var minute : Int ?=null
    companion object {
        fun newInstance() = AlarmSetFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_alarm_set, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_time_picker.setOnClickListener{
            if(vf.displayedChild != 0){
                vf.showNext()
                day=date_picker.dayOfMonth.toString()
                month=date_picker.month.toString()
                year=date_picker.year.toString()
                txt_date.text= "$day/$month/$year"
            }
        }

        btn_date_picker.setOnClickListener{
            if(vf.displayedChild !=1){
                vf.showNext()
                hour=time_picker.hour
                minute=time_picker.minute
                txt_time.text="$hour:$minute"
        }}

        btn_create_alarm.setOnClickListener{
               // startAlert() //nu mi iese partea de alarma, idk ce sa fac
        }
    }

    fun startAlert() {
        val intent = Intent(this.context, BcReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this.context, 234324243, intent, 0
        )
        val alarmManager =
                    context!!.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        alarmManager!![AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + minute!! * 60] = pendingIntent
        Toast.makeText(this.context, "Alarm set in $minute seconds", Toast.LENGTH_LONG).show()
    }


}