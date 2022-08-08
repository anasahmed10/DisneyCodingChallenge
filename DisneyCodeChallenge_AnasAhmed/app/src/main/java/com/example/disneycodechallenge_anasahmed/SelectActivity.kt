package com.example.disneycodechallenge_anasahmed

import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disneycodechallenge_anasahmed.model.Guest
import com.example.disneycodechallenge_anasahmed.model.Guest.Companion.isChecked
import com.example.disneycodechallenge_anasahmed.presenter.RecyclerAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.button.MaterialButton

class SelectActivity : AppCompatActivity() {
    private lateinit var haveRecyclerView: RecyclerView
    private lateinit var needRecyclerView: RecyclerView
    private lateinit var scrollView: ScrollView
    private var toolbar: androidx.appcompat.widget.Toolbar? = null
    private lateinit var mAppBarLayout: AppBarLayout
    private lateinit var continueButton: MaterialButton
    private var haveList: MutableList<Guest> = mutableListOf<Guest>()
    private var needList: MutableList<Guest> = mutableListOf<Guest>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        setUpToolbar()
        setUpRecyclerView()
        setUpButton()
        populateData()
    }

    override fun onStart() {
        super.onStart()
        updateButtonState()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.toolbar)
        mAppBarLayout = findViewById(R.id.appbar_layout)
        scrollView = findViewById(R.id.scrollView)
        setSupportActionBar(toolbar)
        title = "Select Guests"
        // show back button on toolbar
        // on back button press, it will navigate to parent activity
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)

        toolbar?.setNavigationOnClickListener {
            finish()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
                title = "These Guests Have Reservations"
                if (i2 < 50) {
                    title = "Select Guests"
                }
                updateButtonState()
            }
        }

    }

    private fun setUpRecyclerView() {
        haveRecyclerView = findViewById(R.id.haveRecyclerView)
        needRecyclerView = findViewById(R.id.needRecyclerView)
        haveRecyclerView.adapter = RecyclerAdapter(haveList)
        haveRecyclerView.layoutManager = LinearLayoutManager(this)
        needRecyclerView.adapter = RecyclerAdapter(needList)
        needRecyclerView.layoutManager = LinearLayoutManager(this)

        haveRecyclerView.setOnClickListener {
            continueButton.isEnabled = true
        }

        needRecyclerView.setOnClickListener() {
            updateButtonState()
        }
    }

    private fun setUpButton() {
        continueButton = findViewById(R.id.continue_button)
        updateButtonState()

        continueButton.setOnClickListener {
            if (isChecked(haveList)) {
                Toast.makeText(this, "Reservation Made!", Toast.LENGTH_LONG).show()
            }
            if (isChecked(needList) && !isChecked(haveList)) {
                Toast.makeText(this, "Unable to make a reservation!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateButtonState() {
        continueButton.isEnabled = isChecked(haveList) || isChecked(needList)

        if (!continueButton.isEnabled) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // continueButton.setBackgroundColor(resources.getColor(R.color.blue, theme))
                continueButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blue_fade))
            }
        } else {
            continueButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blue))
        }
    }


    private fun populateData() {
        haveList.add(Guest("Dale", "Gibson"))
        haveList.add(Guest("Jeremy", "Gibson"))
        haveList.add(Guest("Aaron", "Gibson"))
        haveList.add(Guest("Claudia", "Gibson"))
        haveList.add(Guest("Dale", "Gibson"))
        haveList.add(Guest("Frank", "Gibson"))
        haveList.add(Guest("Gideon", "Gibson"))
        haveList.add(Guest("Herbert", "Gibson"))
        haveList.add(Guest("Isabelle", "Gibson"))
        haveList.add(Guest("Jeremy", "Gibson"))
        haveList.add(Guest("Ken", "Gibson"))
        haveList.add(Guest("Lisbeth", "Gibson"))

        needList.add(Guest("Linda", "Gibson"))
        needList.add(Guest("Margaret", "Gibson"))
        updateButtonState()
    }
}
