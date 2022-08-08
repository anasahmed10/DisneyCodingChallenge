package com.example.disneycodechallenge_anasahmed.presenter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.disneycodechallenge_anasahmed.R
import com.example.disneycodechallenge_anasahmed.SelectActivity
import com.example.disneycodechallenge_anasahmed.model.Guest
import com.google.android.material.button.MaterialButton

class RecyclerAdapter(private var guests: List<Guest>?) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    private lateinit var clickListener: ItemClickListener

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var checkbox: CheckBox = itemView.findViewById(R.id.checkBox)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
        }
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.clickListener = itemClickListener
    }

    fun getClickListener(): ItemClickListener {
        return clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyler_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPresenter = guests?.get(position)

        holder.checkbox.text = (itemPresenter?.firstName ?: "") + " " + (itemPresenter?.lastName ?: "")
        holder.checkbox.setOnClickListener {
            itemPresenter?.isChecked = holder.checkbox.isChecked
        }

    }

    override fun getItemCount(): Int {
        return guests?.size ?: 0
    }

    companion object {
    }

}