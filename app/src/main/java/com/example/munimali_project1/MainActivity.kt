package com.example.munimali_project1

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var scoreView: TextView

    private lateinit var strikeView: TextView
    private lateinit var topButton: Button
    private lateinit var bottomButton: Button
    private lateinit var layout: LinearLayout
    private lateinit var directionText: TextView
    private lateinit var hardModeCheckBox: CheckBox
    private var upperBound = 100
    private var score = 0
    private var strike = 0
    private var topIsGreater = false
    private var hardMode = false
    private val twoDigitsSize = 64f
    private val threeDigitsSize = 55f
    private val fourDigitsSize = 44f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById(R.id.main)
        scoreView = findViewById(R.id.score)
        strikeView = findViewById(R.id.strikes)
        val startRestartButton: Button = findViewById(R.id.start_restart_button)
        hardModeCheckBox = findViewById(R.id.hard_mode)
        directionText = findViewById(R.id.directions_text)
        topButton = findViewById(R.id.top_button)
        bottomButton = findViewById(R.id.bottom_button)

        scoreView.text = getString(R.string.score, score)
        strikeView.text = getString(R.string.strikes, strike)

        hardModeCheckBox.setOnClickListener {
            hardMode = !hardMode
            upperBound = if (hardMode) {
                1000
            } else {
                100
            }
            changeNum()
        }
        startRestartButton.setOnClickListener {
            startRestartButton.text = getString(R.string.restart_button)
            restart()
        }

        topButton.setOnClickListener {
            clickScoreButton(topButton)
        }
        bottomButton.setOnClickListener {
            clickScoreButton(bottomButton)
        }
        topButton.setTextColor(ContextCompat.getColor(this,R.color.default_text))
        bottomButton.setTextColor(ContextCompat.getColor(this,R.color.default_text))

    }

    private fun changeNum() {
        val top: Int = (1..upperBound).random()
        var bottom :Int = (1..upperBound).random()
        while (bottom == top) {
            bottom = (1..upperBound).random()
        }
        topIsGreater = top > bottom

        topButton.textSize = if (top > 999) {
            fourDigitsSize
        } else if (top > 99) {
            threeDigitsSize
        } else {
            twoDigitsSize
        }

        bottomButton.textSize = if (top > 999) {
            fourDigitsSize
        } else if (top > 99) {
            threeDigitsSize
        } else {
            twoDigitsSize
        }

        topButton.text = top.toString()
        bottomButton.text = bottom.toString()
    }

    private fun clickScoreButton(btn: Button) {
        if ((topIsGreater && topButton == btn) || (!topIsGreater && bottomButton == btn)) {
            score++
            scoreView.text = getString(R.string.score, score)
            scoreView.setTextColor(ContextCompat.getColor(this, R.color.click_yellow))
            strikeView.setTextColor(ContextCompat.getColor(this, R.color.default_text))
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
        } else {
            strike++
            strikeView.text = getString(R.string.strikes, strike)
            strikeView.setTextColor(ContextCompat.getColor(this, R.color.click_yellow))
            scoreView.setTextColor(ContextCompat.getColor(this, R.color.default_text))
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
        }
        changeNum()
        endingCheck()
    }

    private fun restart() {
        score = 0
        strike = 0
        layout.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
        directionText.text = getString(R.string.playing_text)
        topButton.isEnabled = true
        bottomButton.isEnabled = true
        hardModeCheckBox.isEnabled = true
        scoreView.text = getString(R.string.score, 0)
        scoreView.setTextColor(ContextCompat.getColor(this, R.color.default_text))
        strikeView.text = getString(R.string.strikes, 0)
        strikeView.setTextColor(ContextCompat.getColor(this, R.color.default_text))
        changeNum()
    }

    private fun endingCheck() {
        if (score >= 10) {
            ending()
            scoreView.setTextColor(ContextCompat.getColor(this, R.color.win_green))
            val winToast: Toast = Toast.makeText(this, "Congrats, you won!", Toast.LENGTH_SHORT)
            winToast.show()
        } else if (strike >= 3) {
            ending()
            strikeView.setTextColor(ContextCompat.getColor(this, R.color.lose_red))
            val loseToast: Toast = Toast.makeText(this, "Sorry, you lost", Toast.LENGTH_SHORT)
            loseToast.show()
        }
    }

    private fun ending() {
        topButton.isEnabled = false
        topButton.text = ""
        bottomButton.isEnabled = false
        hardModeCheckBox.isEnabled = false
        bottomButton.text = ""
        directionText.text = getString(R.string.restart_text)
        layout.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))

    }
}