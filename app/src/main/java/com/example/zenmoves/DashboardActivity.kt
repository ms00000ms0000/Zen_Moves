package com.example.zenmoves

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val txtSummary = findViewById<TextView>(R.id.txtSummary)
        dbHelper = DBHelper(this)

        val data = dbHelper.getAllData()
        val totalCalories = data.sumOf { it.calories }
        val totalSteps = data.sumOf { it.steps }
        val totalDuration = data.sumOf { it.duration }

        txtSummary.text = "ZenMoves Summary:\nCalories: $totalCalories\nSteps: $totalSteps\nWorkout Duration: $totalDuration mins"
    }
}
