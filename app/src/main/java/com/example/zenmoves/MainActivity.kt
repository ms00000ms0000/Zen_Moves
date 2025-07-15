package com.example.zenmoves

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.fitnessListView)
        val addBtn: Button = findViewById(R.id.btnAdd)
        val dashBtn: Button = findViewById(R.id.btnDashboard)

        dbHelper = DBHelper(this)
        loadActivityData()

        addBtn.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        dashBtn.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

    private fun loadActivityData() {
        val data = dbHelper.getAllData()
        val list = data.map {
            "${it.date} - ${it.exerciseType} (${it.duration} min, ${it.calories} cal, ${it.steps} steps)"
        }
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
    }

    override fun onResume() {
        super.onResume()
        loadActivityData()
    }
}
