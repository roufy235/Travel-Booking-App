package com.example.roufy235.travel.Controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.example.roufy235.travel.Adapters.FlightsResultRecyclerAdapter
import com.example.roufy235.travel.Model.FlightsResultModel
import com.example.roufy235.travel.R
import com.example.roufy235.travel.Services.DataServices
import com.example.roufy235.travel.Services.VolleyDataServices
import kotlinx.android.synthetic.main.activity_flights_result.*
import spencerstudios.com.bungeelib.Bungee

class FlightsResultActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    lateinit var adapter : FlightsResultRecyclerAdapter

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flights_result)


        val bundle = intent.extras

        val location = bundle.getString("location", "")
        var destinationLocal : String? = null

        flightsResultLocation.text = location
        departureDate.text = bundle.getString("departure", "")
        numPassenger.text = bundle.getString("passengerNumber", "1")

        val explore = bundle.getBoolean("explore", false)

        if (explore) {

            watingProgressBar.visibility = View.VISIBLE

            when(location) {
                "London" -> {
                    destinationLocal = "LON"
                }
                "Seattle, United States (US)" -> {
                    destinationLocal = "SEA"
                }
                "Ottawa, Canada" -> {
                    destinationLocal = "YOW"
                }
                "Montreal, Canada" -> {
                    destinationLocal = "YMQ"
                }
                "Paris, France" -> {
                    destinationLocal = "PAR"
                }
                "explore" -> {
                    destinationLocal = "DXB"
                }
            }


            VolleyDataServices.searchFlights(this, "LOS", destinationLocal!!){ result ->
                if (result){
                    watingProgressBar.visibility = View.INVISIBLE

                    if (DataServices.flightsResult.count() == 0) {

                        noFlight.visibility = View.VISIBLE

                    } else {

                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                        adapter.notifyDataSetChanged()

                    }
                } else {
                    watingProgressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            if (DataServices.flightsResult.count() == 0) {

                noFlight.visibility = View.VISIBLE

            }
            watingProgressBar.visibility = View.INVISIBLE
        }


        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        supportActionBar!!.title = "Flight Results"

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp)

        toolbar.setNavigationOnClickListener {

            DataServices.flightsResult.clear()
            finish()
            Bungee.fade(this)
        }

        //dummy()

        adapter = FlightsResultRecyclerAdapter(this, DataServices.flightsResult) {flightsResultModel, position ->
            val intent = Intent(this, FlightDetailsActivity::class.java)

            intent.putExtra("location", "LOS")
            intent.putExtra("position", position)
            intent.putExtra("destination", location)
            startActivity(intent)

            //Toast.makeText(this, "position $position", Toast.LENGTH_SHORT).show()
        }
        recyclerFlightResults.adapter = adapter

        val layoutManager = LinearLayoutManager(this)

        recyclerFlightResults.layoutManager = layoutManager
    }


    fun dummy() {
        DataServices.flightsResult.add(FlightsResultModel("Air Costa", "India", "airplane", "300k"))
    }
}
