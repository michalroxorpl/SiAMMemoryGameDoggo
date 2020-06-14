package com.example.siammemorygame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.siammemorygame.R.drawable.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val images: MutableList<Int> = mutableListOf(doggo_1, doggo_2, doggo_3, doggo_4, doggo_5,
            doggo_6,doggo_1, doggo_2, doggo_3, doggo_4, doggo_5, doggo_6)

        val buttons = arrayOf(buttonA1, buttonB1, buttonC1, buttonA2, buttonB2, buttonC2, buttonA3,
            buttonB3, buttonC3, buttonA4, buttonB4, buttonC4)

        val hiddenCardText = "hidden"
        val hiddenCardBackground = zoomin
        var clickCount = 0
        var totalSteps = 0
        var turnedOverStarted = false
        var lastClickedCard = -1

        images.shuffle()
        for (i in 0..11) {
            buttons[i].text = hiddenCardText
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener{
                if (buttons[i].text == hiddenCardText && !turnedOverStarted) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (clickCount == 0) {
                        lastClickedCard = i
                    }
                    clickCount++
                    totalSteps++
                }
                else if (buttons[i].text !in hiddenCardText) {
                    buttons[i].setBackgroundResource(hiddenCardBackground)
                    buttons[i].text = hiddenCardText
                    clickCount--
                    totalSteps++
                }
                if (clickCount == 2) {
                    turnedOverStarted = true
                    if (buttons[lastClickedCard].text == buttons[i].text) {
                        buttons[i].isClickable = false
                        buttons[lastClickedCard].isClickable = false
                        buttons[i].setBackgroundResource(cup)
                        buttons[lastClickedCard].setBackgroundResource(cup)
                        turnedOverStarted = false
                        lastClickedCard = -1
                        clickCount = 0
                    }
                }
                else if ( clickCount == 0 ) {
                    turnedOverStarted = false
                }
                textViewSteps.text = "Steps: $totalSteps"
            }
        }
        buttonNewGame.setOnClickListener {
            images.shuffle()
            for (i in 0..11) {
                buttons[i].setBackgroundResource(zoomin)
                buttons[i].text=hiddenCardText
                buttons[i].isClickable = true
                clickCount = 0
                turnedOverStarted = false
                lastClickedCard = -1
                totalSteps = 0
            }
        }
    }

}