package com.za.cocomo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.za.cocomo.databinding.ActivityDriversBinding

class Drivers : AppCompatActivity() {

    private lateinit var binding: ActivityDriversBinding
    private lateinit var rvadapter: adapter // Correct adapter type
    private val selectedValues = mutableListOf<Double>() // Tracks selected values for EAF calculation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityDriversBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter with data
        val data = constant.getData() // Ensure this returns a valid ArrayList<RvModel>
        rvadapter = adapter(data, this) { isSelected, value ->
            updateSelectedValues(isSelected, value)
        }

        // Set up RecyclerView
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = rvadapter

        // Button to calculate EAF
        binding.Addbutton.setOnClickListener {

            calculateEAF()
        }
    }

    private fun updateSelectedValues(isSelected: Boolean, value: Double) {
        if (isSelected) {
            selectedValues.add(value)
        } else {
            selectedValues.remove(value)
        }
    }

    private fun calculateEAF() {
        // Ensure there are values to calculate
        if (selectedValues.isEmpty()) {
            Toast.makeText(this, "Please! enter the value", Toast.LENGTH_SHORT).show()
            return
        }

        // Calculate EAF as the product of all selected values
        val eaf = selectedValues.reduce { acc, value -> acc * value }

        // Display EAF using a Toast or pass it to the next activity
        Toast.makeText(this, "EAF: $eaf", Toast.LENGTH_LONG).show()

        // Optionally pass EAF to the next activity
        val intent = Intent(this, Intermediate_model::class.java)
        intent.putExtra("EAF", eaf)
        startActivity(intent)
    }

    private fun animateButton(button: Button) {
        button.animate()
            .scaleX(1.1f) // Scale up slightly
            .scaleY(1.1f) // Scale up slightly
            .setDuration(150) // Quick effect duration
            .withEndAction {
                button.animate()
                    .scaleX(1f) // Reset scale
                    .scaleY(1f)
                    .setDuration(150)
                    .start()
            }
            .start()}
}
