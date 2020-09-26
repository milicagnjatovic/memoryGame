package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images: MutableList<Int> = mutableListOf(img1, img2, img3, img4, img5, img6, img1, img2, img3, img4, img5, img6)
        val btns = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12)

        val cardBack = turn
        var clicked = 0
        var turnOver = false
        var lastClicked = -1

        fun setupGame() {
            images.shuffle()

            for (i in 0..11) {
                btns[i].setBackgroundResource(cardBack)
                btns[i].text = "cardBack"
                btns[i].textSize = 0.0f
                btns[i].setOnClickListener {
                    if (btns[i].text == "cardBack" && !turnOver) {
                        btns[i].setBackgroundResource(images[i])
                        btns[i].setText(images[i])
                        if (clicked == 0) {
                            lastClicked = i
                        }
                        clicked++
                    } else if (btns[i].text !in "cardBack") {
                        btns[i].setBackgroundResource(cardBack)
                        btns[i].text = "cardBack"
                        clicked--
                    }

                    if (clicked == 2) {
                        turnOver = true
                        if (btns[i].text == btns[lastClicked].text) {
                            btns[i].isClickable = false
                            btns[lastClicked].isClickable = false
                            turnOver = false
                            clicked = 0
                        }
                    } else if (clicked == 0) {
                        turnOver = false
                    }
                }
            }
        }

        setupGame()

        newGameBtn.setOnClickListener {
            clicked = 0
            turnOver = false
            lastClicked = -1
            setupGame()
        }
    }
}