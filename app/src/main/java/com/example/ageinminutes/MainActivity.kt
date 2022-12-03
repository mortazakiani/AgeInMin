package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var date :TextView?=null
    private var txtAgeInMin :TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        date =findViewById(R.id.txtDate)
        txtAgeInMin =findViewById(R.id.txtAgeInMin)

        val btnCalc :Button =findViewById(R.id.btn_calculate)
        btnCalc.setOnClickListener {
            datePicker()
        }
    }
     private fun datePicker(){
         val myCalendar =Calendar.getInstance()
        val year =myCalendar.get(Calendar.YEAR)
        val month =myCalendar.get(Calendar.MONTH)
        val dayOfMonth =myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd =DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay
        ->
            val selectedDate= "$selectedYear ,${selectedMonth+1} , $selectedDay"
            date?.text= selectedDate
            val sdf =SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val theDate =sdf.parse(selectedDate)
            theDate?.let {    val selectedDateInMin=theDate.time /60000
                val currentDate =sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMin =currentDate.time /60000
                    val differenceInMin =currentDateInMin -selectedDateInMin
                    txtAgeInMin?.text =differenceInMin.toString()}
                }


        },
        year,month,dayOfMonth
        )
        dpd.datePicker.maxDate= System.currentTimeMillis()- 86400000
        dpd.show()

    }
}