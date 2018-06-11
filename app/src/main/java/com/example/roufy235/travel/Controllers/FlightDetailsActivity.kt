package com.example.roufy235.travel.Controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.example.roufy235.travel.Model.FlightDetailsHolder
import com.example.roufy235.travel.R
import com.example.roufy235.travel.Services.DataServices
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_flight_details.*

class FlightDetailsActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    lateinit var destinationLocal : String

    private lateinit var mFirebaseAnalytics : FirebaseAnalytics
    private lateinit var mAuth : FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var mRef : DatabaseReference

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_details)


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mRef = database.reference


        val bundle : Bundle = intent.extras


        val destination = bundle.getString("destination")
        val fromLocation = bundle.getString("location")
        val positionInArray = bundle.getInt("position")

        val flightDetails : FlightDetailsHolder = DataServices.saveAllFlightResult[positionInArray]

        destinationForFlightDetails.text = destination
        depart_date.text = flightDetails.departDate
        return_date.text = flightDetails.returnDate
        distance.text = flightDetails.distance
        value.text = flightDetails.value



        mRef.child("Users_info").child("hello").child(mAuth.uid.toString()).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0 : DatabaseError) {

            }

            override fun onDataChange(p0 : DataSnapshot) {
                try {
                    val databaseValue = p0.value as HashMap<*, *>

                    flightDetailsUsername.text = databaseValue["username"] as String

                } catch (ex : Exception){}
            }

        })









        when(destination) {
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


        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        supportActionBar!!.title = "Flight to $destination"

        toolbar.setNavigationOnClickListener {
            finish()
        }

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp)


    }
}
