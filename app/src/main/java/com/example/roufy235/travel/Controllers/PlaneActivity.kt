package com.example.roufy235.travel.Controllers

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.example.roufy235.travel.R
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.activity_plane.*
import spencerstudios.com.bungeelib.Bungee

class PlaneActivity : AppCompatActivity() {

    private var lastChecked : Int = 1

    lateinit var spinner : MaterialSpinner
    lateinit var spinner2 : MaterialSpinner
    lateinit var classLevel : MaterialSpinner
    lateinit var alertDialog : AlertDialog.Builder


    var destination : String = "Lagos"
    var flightFrom : String = "Lagos"
    var passengerClass : String? = null
    var realArrivalDate : String? = null
    var realDepartureDate : String? = null

    var departureDate : DatePicker? = null
    var arrivalDate : DatePicker? = null




    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plane)

        spinner = findViewById(R.id.spinner)
        spinner.setItems("Lagos", "Abuja")
        spinner.setOnItemSelectedListener { view, position, id, item ->

            flightFrom = item.toString()
            //Snackbar.make(view!!, "Clicked$item", Snackbar.LENGTH_SHORT).show()

        }



        spinner2 = findViewById(R.id.spinner2)
        spinner2.setItems("Lagos", "Abuja", "Akure", "Kaduna", "Ibadan")
        spinner2.setOnItemSelectedListener { view, position, id, item ->

            destination = item.toString()
            //Snackbar.make(view!!, "Clicked$item", Snackbar.LENGTH_SHORT).show()

        }


        classLevel = findViewById(R.id.class_level)

        classLevel.setItems("First Class", "Economy")
        classLevel.setOnItemSelectedListener{view, position, id, item ->

            passengerClass = item.toString()
            //Snackbar.make(view!!, "Clicked$item", Snackbar.LENGTH_SHORT).show()
        }

    }


    fun arrivalDate(view : View) {
        alertDialog = if (Build.VERSION.SDK_INT >= 23) {
            AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            AlertDialog.Builder(this)
        }

        val newView = LayoutInflater.from(this).inflate(R.layout.date_dialog, null)

        alertDialog.setView(newView)
                .setTitle("Arrival Date")
                .setCancelable(true)
                .setPositiveButton("Done") {dialog, which ->

                    arrivalDate = newView.findViewById<DatePicker>(R.id.datePicker)

                    realArrivalDate = arrivalDate!!.dayOfMonth.toString() + "/" + arrivalDate!!.month + "/" + arrivalDate!!.year

                    arrivalBtn.text = realArrivalDate

                    //Toast.makeText(this, "dat =>" + arrivalDate!!.dayOfMonth + "/" + arrivalDate!!.month + "/" + arrivalDate!!.year, Toast.LENGTH_LONG).show()

                }
                .setNegativeButton("Cancel") {dialog, which ->

                }
                .show()
    }


    fun departureDate(view : View) {
        alertDialog = if (Build.VERSION.SDK_INT >= 23) {
            AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            AlertDialog.Builder(this)
        }

        val newView = LayoutInflater.from(this).inflate(R.layout.date_dialog, null)

        alertDialog.setView(newView)
                .setTitle("Departure Date")
                .setCancelable(true)
                .setPositiveButton("Done") {dialog, which ->

                    departureDate = newView.findViewById<DatePicker>(R.id.datePicker)

                    realDepartureDate = departureDate!!.dayOfMonth.toString() + "/" + departureDate!!.month + "/" + departureDate!!.year

                    departureBtn.text = realDepartureDate

                    //Toast.makeText(this, "dat =>" + departureDate!!.dayOfMonth + "/" + departureDate!!.month + "/" + departureDate!!.year, Toast.LENGTH_LONG).show()

                }
                .setNegativeButton("Cancel") {dialog, which ->

                }
                .show()
    }

    fun planeRoundClicked(view : View) {
        lastChecked = 1
        btnRound.setBackgroundResource(R.drawable.btn_gradient)
        btnRound.setTextColor(application.resources.getColor(R.color.white))


        btnOneWay.setBackgroundColor(Color.TRANSPARENT)
        btnMulti.setBackgroundColor(Color.TRANSPARENT)
        btnMulti.setTextColor(application.resources.getColor(R.color.gray))
        btnOneWay.setTextColor(application.resources.getColor(R.color.gray))

    }

    fun planeOneWayClicked(view : View) {
        lastChecked = 2
        btnOneWay.setBackgroundResource(R.drawable.btn_gradient)
        btnOneWay.setTextColor(application.resources.getColor(R.color.white))


        btnRound.setBackgroundColor(Color.TRANSPARENT)
        btnMulti.setBackgroundColor(Color.TRANSPARENT)
        btnMulti.setTextColor(application.resources.getColor(R.color.gray))
        btnRound.setTextColor(application.resources.getColor(R.color.gray))
    }

    fun planeMultiClicked(view : View) {
        lastChecked = 3
        btnMulti.setBackgroundResource(R.drawable.btn_gradient)
        btnMulti.setTextColor(application.resources.getColor(R.color.white))


        btnRound.setBackgroundColor(Color.TRANSPARENT)
        btnOneWay.setBackgroundColor(Color.TRANSPARENT)
        btnOneWay.setTextColor(application.resources.getColor(R.color.gray))
        btnRound.setTextColor(application.resources.getColor(R.color.gray))
    }

    fun searchFlights(view : View) {
        val numberOfPassenger = bookNumberOfPassenger.text.toString()

        if (numberOfPassenger.isNotEmpty() && realArrivalDate != null && realDepartureDate != null) {


            val intent = Intent(this, FlightsResultActivity::class.java)

            intent.putExtra("passengerNumber", numberOfPassenger)
            intent.putExtra("departure", realDepartureDate)
            intent.putExtra("arrival", realArrivalDate)
            intent.putExtra("trip", lastChecked)
            intent.putExtra("from", flightFrom)
            intent.putExtra("location", destination)
            intent.putExtra("passengerClass", passengerClass)

            startActivity(intent)
            Bungee.fade(this)
        } else {
            Toast.makeText(this, "all fields are required", Toast.LENGTH_SHORT).show()
        }
    }
}
