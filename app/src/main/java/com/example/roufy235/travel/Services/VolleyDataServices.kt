package com.example.roufy235.travel.Services

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.roufy235.travel.Model.FlightDetailsHolder
import com.example.roufy235.travel.Model.FlightsResultModel
import com.example.roufy235.travel.Utilities.URL
import org.json.JSONException

object VolleyDataServices {


    fun searchFlights(context : Context, location : String, destination : String, complete : (Boolean) -> Unit) {

        val link = URL + "origin_iata=$location&destination_iata=$destination"

        val searchRequest = object : JsonObjectRequest(Method.GET, link, null, Response.Listener {response ->

            try {
                val jsonArray = response.getJSONArray("best_prices")

                DataServices.flightsResult.clear()
                DataServices.saveAllFlightResult.clear()
                var imageName : String?
                var country : String?

                for (key in 0 until jsonArray.length()) {

                    val availableFlight = jsonArray.getJSONObject(key)

                    val carrierName = availableFlight.getString("gate")

                    val distance = availableFlight.getString("distance")
                    val depart_date = availableFlight.getString("depart_date")
                    val return_date = availableFlight.getString("return_date")

                    val resultDestination = availableFlight.getString("destination")




                    when(carrierName) {
                        "BudgetAir.nl" -> {
                            imageName = "budget"
                        }
                        "SmartFares" -> {
                            imageName = "fares"
                        }
                        "Qatar Airways" -> {
                            imageName = "qatar"
                        }
                        "Kiwi.com" -> {
                            imageName = "kiwi"
                        }
                        "Travelgenio" -> {
                            imageName = "travelgenio"
                        }
                        else -> {
                            imageName = "airplane"
                        }
                    }

                    when(resultDestination) {
                        "LON" -> {
                            country = "London"
                        }
                        "YOW" -> {
                            country = "Ottawa, Canada"
                        }
                        "SEA" -> {
                            country = "Seattle, United States"
                        }
                        "YMQ" -> {
                            country = "Montreal, Canada"
                        }
                        "PAR" -> {
                            country = "Paris, France"
                        }
                        "DXB" -> {
                            country = "Dubai, United Arab Emirates"
                        }
                        else -> {
                            country = ""
                        }
                    }

                    val price = (availableFlight.getDouble("value") * 5.78).toInt().toString()

                    DataServices.flightsResult.add(FlightsResultModel(carrierName, country, imageName, price))

                    DataServices.saveAllFlightResult.add(FlightDetailsHolder(distance, depart_date, return_date, price))

                    //println(availableFlight.getString("value"))
                }

                complete(true)
            } catch (ex : JSONException) {
                complete(false)
                //println(ex.localizedMessage)
            }
        }, Response.ErrorListener {error ->
            //println(error.localizedMessage)
            complete(false)
        }){

            override fun getBodyContentType() : String {
                return "application/json; charset=utf-8"
            }
        }
        Volley.newRequestQueue(context).add(searchRequest)
    }
}