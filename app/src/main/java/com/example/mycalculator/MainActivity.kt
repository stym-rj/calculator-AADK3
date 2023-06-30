package com.example.mycalculator

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var calcCounter: Int = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val display: TextView = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            binding.tvAC,
            binding.tvOpenBracket,
            binding.tvCloseBracket,
            binding.tvBackspace,
            binding.tv7,
            binding.tv8,
            binding.tv9,
            binding.tvDivide,
            binding.tv4,
            binding.tv5,
            binding.tv6,
            binding.tvMultiply,
            binding.tv1,
            binding.tv2,
            binding.tv3,
            binding.tvSubtract,
            binding.tv0,
            binding.tvDot,
            binding.tvEquals,
            binding.tvAddition,
        )

        buttons.forEach {
            it.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
                click(it, display, motionEvent)
                return@OnTouchListener true
            })
        }
    }

    private fun click(id: TextView, display: TextView, motionEvent: MotionEvent) {
        when (motionEvent.action) {
            MotionEvent.ACTION_UP -> {
                id.setBackgroundResource(R.drawable.oval_shape)

                when(id.id) {
                    R.id.tvAC ->            display.text = ""

                    R.id.tvBackspace ->     if (display.text == "ERROR") {
                                                display.text = ""
                                            }
                                            else if (display.text.length <= 1) {
                                                display.text = ""
                                            }
                                            else {
                                                display.text = display.text.toString().dropLast(1)
                                            }

                    R.id.tvEquals ->        try {
                                                val text = display.text.toString()
                                                val expression = ExpressionBuilder(text).build()

                                                val result = expression.evaluate()
                                                val longResult = result.toLong()
                                                if (result == longResult.toDouble()) {
                                                    display.text = longResult.toString()
                                                } else {
                                                    display.text = result.toString()
                                                }
                                            }
                                            catch (e: Exception) {
                                                display.text = "ERROR"
                                            }
                                            finally {
                                                calcCounter = 1
                                            }

                    else ->                 {
                                                if (    (display.text == "ERROR" ||
                                                        calcCounter == 1) &&
                                                        id.id != R.id.tvAddition &&
                                                        id.id != R.id.tvSubtract &&
                                                        id.id != R.id.tvMultiply &&
                                                        id.id != R.id.tvDivide ) {
                                                    display.text = id.text.toString()
                                                } else if ( (display.text == "ERROR" ||
                                                            display.text.toString() == "") &&
                                                            (id.id == R.id.tvAddition ||
                                                            id.id == R.id.tvSubtract ||
                                                            id.id == R.id.tvMultiply ||
                                                            id.id == R.id.tvDivide )) {
                                                    display.text = ""
                                                } else if (display.text.toString() == "") {
                                                     display.text = id.text.toString()
                                                }else {
                                                    display.text = display.text.toString() + id.text.toString()
                                                }
                                                calcCounter = 0
                                            }
                }
            }

            MotionEvent.ACTION_DOWN -> {
                id.setBackgroundResource(R.drawable.inverted_oval_shape)
            }
        }
    }



}
