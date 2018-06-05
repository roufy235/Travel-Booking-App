package com.example.roufy235.travel.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.roufy235.travel.Model.FlightsResultModel
import com.example.roufy235.travel.R
import org.w3c.dom.Text

class FlightsResultRecyclerAdapter(val context : Context, val flightsResults : ArrayList<FlightsResultModel>) : RecyclerView.Adapter<FlightsResultRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent : ViewGroup?, viewType : Int) : ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ticket_flight_results, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() : Int {
        return flightsResults.count()
    }

    override fun onBindViewHolder(holder : ViewHolder?, position : Int) {
        holder!!.bindView(context, flightsResults[position])
    }


    inner class ViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
        val carrierName : TextView = itemView!!.findViewById(R.id.carrierName)
        val carrierImage : ImageView = itemView!!.findViewById(R.id.carrierImage)
        val carrierOrigin : TextView = itemView!!.findViewById(R.id.country)
        val amount : TextView = itemView!!.findViewById(R.id.amount)


        fun bindView(context : Context, flights : FlightsResultModel) {
            val resId = context.resources.getIdentifier(flights.carrierImage, "drawable", context.packageName)

            carrierImage.setImageResource(resId)
            carrierName.text = flights.carrierName
            carrierOrigin.text = flights.carrierOrigin
            amount.text = flights.amount
        }
    }
}