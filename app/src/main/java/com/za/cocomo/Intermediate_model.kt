package com.za.cocomo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class Intermediate_model : AppCompatActivity() {
    // Constants for COCOMO model (example values)
    private val a = 3.2
    private val b = 1.05
    private val c = 2.5
    private val d = 0.38

    private val aa = 3.0
    private val bb = 1.12
    private val cc = 2.5
    private val dd = 0.35

    private val aaa = 2.8
    private val bbb = 1.20
    private val ccc = 2.5
    private val ddd = 0.32

    private lateinit var editText: EditText
    private lateinit var calculateButton: Button

    private var eaf: Double? = null // Effort Adjustment Factor from previous activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intemediate_model)

        // Get the EAF value passed from the previous activity (Drivers)
        eaf = intent.getDoubleExtra("EAF", -1.0).takeIf { it != -1.0 }

        // Initialize UI components
        editText = findViewById(R.id.editText)
        calculateButton = findViewById(R.id.button3)

        // Adjust for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up button click listener
        calculateButton.setOnClickListener {
            animateButton(calculateButton)
            calculateButton.postDelayed({
            calculateMetrics()
            },70)
        }
    }

    private fun calculateMetrics() {
        val klocInput = editText.text.toString().toDoubleOrNull()

        if (eaf == null) {
            Toast.makeText(this, "EAF not provided!", Toast.LENGTH_SHORT).show()
            return
        }

        if (klocInput == null || klocInput <= 0) {
            showResultDialog("Please enter a valid positive KLOC value.")
            return
        }

        val resultMessage: String = when {
            klocInput <= 50 -> calculateCOCOMO(a, b, c, d, klocInput, "Organic project")
            klocInput <= 300 -> calculateCOCOMO(aa, bb, cc, dd, klocInput, "Semi-detached project")
            else -> calculateCOCOMO(aaa, bbb, ccc, ddd, klocInput, "Embedded project")
        }

        showResultDialog(resultMessage)
    }

    private fun calculateCOCOMO(a: Double, b: Double, c: Double, d: Double, kloc: Double, projectType: String): String {
        val effort = a * kloc.pow(b) * eaf!!
        val developmentTime = c * effort.pow(d)
        val averageStaffSize = effort / developmentTime
        val productivity = kloc / effort

        return """
            $projectType

            Effort: ${effort.format(3)} person-months 
            Development Time: ${developmentTime.format(3)} months 
            Average Staff Size: ${averageStaffSize.format(3)} persons
            Productivity: ${productivity.format(3)} KLOC/person-month
        """.trimIndent()
    }

    private fun showResultDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Calculation Result")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun Double.format(digits: Int): String = "%.${digits}f".format(this)


    private fun animateButton(button: Button) {
        button.animate()
            .scaleX(1.1f) // Scale up slightly
            .scaleY(1.1f) // Scale up slightly
            .setDuration(30) // Quick effect duration
            .withEndAction {
                button.animate()
                    .scaleX(1f) // Reset scale
                    .scaleY(1f)
                    .setDuration(30)
                    .start()
            }
            .start()}
}
