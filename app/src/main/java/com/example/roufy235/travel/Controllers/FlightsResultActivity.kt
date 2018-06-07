package com.example.roufy235.travel.Controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.example.roufy235.travel.Adapters.FlightsResultRecyclerAdapter
import com.example.roufy235.travel.Model.FlightsResultModel
import com.example.roufy235.travel.R
import com.example.roufy235.travel.Services.DataServices
import kotlinx.android.synthetic.main.activity_flights_result.*
import spencerstudios.com.bungeelib.Bungee

class FlightsResultActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    lateinit var adapter : FlightsResultRecyclerAdapter

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flights_result)


        val bundle = intent.extras

        flightsResultLocation.text = bundle.getString("location", "")
        departureDate.text = bundle.getString("departure", "")
        numPassenger.text = bundle.getString("passengerNumber", "")


        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        supportActionBar!!.title = "Flight Results"

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp)

        toolbar.setNavigationOnClickListener {
            finish()
            Bungee.fade(this)
        }

        dummy()

        adapter = FlightsResultRecyclerAdapter(this, DataServices.flightsResult)
        recyclerFlightResults.adapter = adapter

        val layoutManager = LinearLayoutManager(this)

        recyclerFlightResults.layoutManager = layoutManager




    }


    fun dummy() {
        DataServices.flightsResult.add(FlightsResultModel("Air Costa", "India", "airplane", "300k"))
        DataServices.flightsResult.add(FlightsResultModel("Alpha Jet", "Slovakia", "airplane", "400k"))
        DataServices.flightsResult.add(FlightsResultModel("Avior Regional", "Venezuela", "airplane", "300k"))
        DataServices.flightsResult.add(FlightsResultModel("InterCaribbean Airways", "Turks and Caicos Islands", "airplane", "100k"))
        DataServices.flightsResult.add(FlightsResultModel("InterCaribbean Airways", "Turks and Caicos Islands", "plane2", "150k"))
        DataServices.flightsResult.add(FlightsResultModel("InterCaribbean Airways", "Turks and Caicos Islands", "airplane", "300k"))
        DataServices.flightsResult.add(FlightsResultModel("InterCaribbean Airways", "Turks and Caicos Islands", "planeair", "300k"))
        DataServices.flightsResult.add(FlightsResultModel("InterCaribbean Airways", "Turks and Caicos Islands", "program", "300k"))
        DataServices.flightsResult.add(FlightsResultModel("InterCaribbean Airways", "Turks and Caicos Islands", "airplane", "300k"))
    }
}
