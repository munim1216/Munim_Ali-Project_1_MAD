package com.example.munimali_project1

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var scoreView: TextView
    private lateinit var strikeView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout: LinearLayout = findViewById(R.id.main)
        scoreView = findViewById(R.id.score)
        strikeView = findViewById(R.id.strikes)
        val startRestartButton: Button = findViewById(R.id.start_restart_button)
        val directionText: TextView = findViewById(R.id.directions_text)
        val topButton: Button = findViewById(R.id.top_button)
        val bottomButton: Button = findViewById(R.id.bottom_button)

        topButton.setOnClickListener {
            scoreView.text = getString(R.string.score, 8888)
        }

    }

    fun startGame() {
        scoreView
    }
}