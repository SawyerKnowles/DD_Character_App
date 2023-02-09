package com.example.ddcharacterapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils

class InputCharacterInfo: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0,0)
        setContentView(R.layout.add_list_character)

        val cancelButton = findViewById<Button>(R.id.cancel_button)
        val addButton = findViewById<Button>(R.id.add_button)
        val addListCharacterBackground = findViewById<LinearLayout>(R.id.add_list_character_background)
        val charInfoWindow = findViewById<LinearLayout>(R.id.charInfo)

        // Fade animation for the background of Popup Window
        val alpha = 100 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            addListCharacterBackground.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        charInfoWindow.alpha = 0f
        charInfoWindow.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        cancelButton.setOnClickListener {
            onCancelPressed()
        }

        addButton.setOnClickListener {
            onAddPressed()
        }
    }

    private fun onAddPressed() {
        val charName = findViewById<TextView>(R.id.charName)
        val className = findViewById<TextView>(R.id.className)
        val levelNo = findViewById<TextView>(R.id.levelNo)

        val addListCharacterBackground = findViewById<LinearLayout>(R.id.add_list_character_background)
        val charInfoWindow = findViewById<LinearLayout>(R.id.charInfo)
        // Fade animation for the background of Popup Window when you press the back button
        val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            addListCharacterBackground.setBackgroundColor(
                animator.animatedValue as Int
            )
        }

        // Fade animation for the Popup Window when you press the back button
        charInfoWindow.animate().alpha(0f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // After animation finish, close the Activity
        colorAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val intent = Intent()
                intent.putExtra("name", charName.text.toString())
                intent.putExtra("class", className.text.toString())
                intent.putExtra("level", levelNo.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
                overridePendingTransition(0, 0)
            }
        })
        colorAnimation.start()
    }

    private fun onCancelPressed() {
        val addListCharacterBackground = findViewById<LinearLayout>(R.id.add_list_character_background)
        val charInfoWindow = findViewById<LinearLayout>(R.id.charInfo)
        // Fade animation for the background of Popup Window when you press the back button
        val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            addListCharacterBackground.setBackgroundColor(
                animator.animatedValue as Int
            )
        }

        // Fade animation for the Popup Window when you press the back button
        charInfoWindow.animate().alpha(0f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // After animation finish, close the Activity
        colorAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                //val intent = Intent()
                //setResult(Activity.RESULT_OK, intent)
                finish()
                overridePendingTransition(0, 0)
            }
        })
        colorAnimation.start()
    }
}