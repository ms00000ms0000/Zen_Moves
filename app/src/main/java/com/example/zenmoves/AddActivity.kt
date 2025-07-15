package com.example.zenmoves

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        dbHelper = DBHelper(this)

        val dateEdit = findViewById<EditText>(R.id.etDate)
        val typeEdit = findViewById<EditText>(R.id.etType)
        val durationEdit = findViewById<EditText>(R.id.etDuration)
        val calEdit = findViewById<EditText>(R.id.etCalories)
        val stepsEdit = findViewById<EditText>(R.id.etSteps)
        val saveBtn = findViewById<Button>(R.id.btnSave)

        saveBtn.setOnClickListener {
            val model = ActivityModel(
                0,
                dateEdit.text.toString(),
                typeEdit.text.toString(),
                durationEdit.text.toString().toIntOrNull() ?: 0,
                calEdit.text.toString().toIntOrNull() ?: 0,
                stepsEdit.text.toString().toIntOrNull() ?: 0
            )
            if (dbHelper.insertData(model)) {
                Toast.makeText(this, "Saved to ZenMoves!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to save!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
