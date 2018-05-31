package com.example.roufy235.travel.Controllers

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.example.roufy235.travel.R
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.activity_plane.*

class PlaneActivity : AppCompatActivity() {

    private var lastChecked : Int = 1

    lateinit var spinner : MaterialSpinner
    lateinit var spinner2 : MaterialSpinner
    lateinit var classLevel : MaterialSpinner

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plane)

        spinner = findViewById(R.id.spinner)
        spinner.setItems("Lagos", "Abuja")
        spinner.setOnItemSelectedListener { view, position, id, item ->

            Snackbar.make(view!!, "Clicked$item", Snackbar.LENGTH_SHORT).show()

        }



        spinner2 = findViewById(R.id.spinner2)
        spinner2.setItems("Lagos", "Abuja", "Akure", "Kaduna", "Ibadan")
        spinner2.setOnItemSelectedListener { view, position, id, item ->

            Snackbar.make(view!!, "Clicked$item", Snackbar.LENGTH_SHORT).show()

        }


        classLevel = findViewById(R.id.class_level)

        classLevel.setItems("First Class", "Economy")
        classLevel.setOnItemSelectedListener{view, position, id, item ->
            Snackbar.make(view!!, "Clicked$item", Snackbar.LENGTH_SHORT).show()
        }

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
        Toast.makeText(this, "This event will be implemented soon", Toast.LENGTH_LONG).show()
    }
}
