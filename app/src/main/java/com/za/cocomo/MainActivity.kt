package com.za.cocomo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var basicButton: Button
    private lateinit var intermediateButton: Button
    private lateinit var detailedButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize buttons
        basicButton = findViewById(R.id.basic)
        intermediateButton = findViewById(R.id.intermediate)
        detailedButton = findViewById(R.id.detailed)

        // Set click listeners with animations and delayed actions
        basicButton.setOnClickListener {
            animateButton(basicButton)
            basicButton.postDelayed({
                val intent = Intent(this, Basic_model::class.java)
                startActivity(intent)
            }, 70)
        }

        intermediateButton.setOnClickListener {
            animateButton(intermediateButton)
            intermediateButton.postDelayed({
                val intent = Intent(this, Drivers::class.java)
                startActivity(intent)
            }, 70)
        }

        detailedButton.setOnClickListener {
            animateButton(detailedButton)
            detailedButton.postDelayed({
                val intent = Intent(this, Detailed_model::class.java)
                startActivity(intent)
            }, 70)
        }
    }

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


//        button.animate()
//            .translationY(-10f) // Move up slightly
//            .setDuration(100)
//            .withEndAction {
//                button.animate()
//                    .translationY(0f) // Return to original position
//                    .setDuration(100)
//                    .start()
//            }
//            .start()


//        button.animate()
//            .rotation(15f) // Slight rotation
//            .setDuration(100)
//            .withEndAction {
//                button.animate()
//                    .rotation(0f) // Return to original position
//                    .setDuration(100)
//                    .start()
//            }
//            .start()
    }


}
