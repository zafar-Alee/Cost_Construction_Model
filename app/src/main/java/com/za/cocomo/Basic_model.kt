package com.za.cocomo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.annotation.SuppressLint
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Basic_model : AppCompatActivity() {
    // Constants for COCOMO model (example values)
    private val a = 2.4
    private val b = 1.05
    private val c = 2.5
    private val d = 0.38

    private val aa = 3.0
    private val bb = 1.12
    private val cc = 2.5
    private val dd = 0.35

    private val aaa = 3.6
    private val bbb = 1.20
    private val ccc = 2.5
    private val ddd = 0.32

    private lateinit var editText: EditText
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_basic_model)

        editText = findViewById(R.id.editText)
        calculateButton = findViewById(R.id.button3)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        calculateButton.setOnClickListener {
        animateButton(calculateButton)
            calculateButton.postDelayed({
            calculateMetrics()
            },70)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateMetrics() {
        val klocInput = editText.text.toString().toDoubleOrNull()

        if (klocInput != null) {
            val resultMessage: String = when {
                klocInput <= 50 -> {
                    val effort = a * Math.pow(klocInput, b)
                    val developmentTime = c * Math.pow(effort, d)
                    val averageStaffSize = effort / developmentTime
                    val productivity = klocInput / effort

                    """
                    Organic project
                    
                   Effort: ${effort.format(3)} person-months 
                    Development Time: ${developmentTime.format(3)} months 
                    Average Staff Size: ${averageStaffSize.format(3)} persons
                    Productivity: ${productivity.format(3)} KLOC/person-month
                """.trimIndent()
                }

                klocInput > 50 && klocInput < 300 -> {
                    val effort = aa * Math.pow(klocInput, bb)
                    val developmentTime = cc * Math.pow(effort, dd)
                    val averageStaffSize = effort / developmentTime
                    val productivity = klocInput / effort

                    """
                    Semi-detached project
                    
                     Effort: ${effort.format(3)} person-months 
                    Development Time: ${developmentTime.format(3)} months 
                    Average Staff Size: ${averageStaffSize.format(3)} persons
                    Productivity: ${productivity.format(3)} KLOC/person-month
                """.trimIndent()
                }

                klocInput >= 300 -> {
                    val effort = aaa * Math.pow(klocInput, bbb)
                    val developmentTime = ccc * Math.pow(effort, ddd)
                    val averageStaffSize = effort / developmentTime
                    val productivity = klocInput / effort

                    """
                    Embedded project
                    
                    Effort: ${effort.format(3)} person-months 
                    Development Time: ${developmentTime.format(3)} months 
                    Average Staff Size: ${averageStaffSize.format(3)} persons
                    Productivity: ${productivity.format(3)} KLOC/person-month
                """.trimIndent()
                }

                else -> "Invalid input."
            }

            // Show the result in a dialog
            showResultDialog(resultMessage)
        } else {
            showResultDialog("Please enter a valid KLOC value.")
        }
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
            .start()
    }
}
