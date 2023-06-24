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
//        val one: TextView = findViewById(R.id.tv1)
//        val two: TextView = findViewById(R.id.tv2)
//        val three: TextView = findViewById(R.id.tv3)
//        val four: TextView = findViewById(R.id.tv4)
//        val five: TextView = findViewById(R.id.tv5)
//        val six: TextView = findViewById(R.id.tv6)
//        val seven: TextView = findViewById(R.id.tv7)
//        val eight: TextView = findViewById(R.id.tv8)
//        val nine: TextView = findViewById(R.id.tv9)
//        val ac: TextView = findViewById(R.id.tvAC)
//        val equals: TextView = findViewById(R.id.tvEquals)
//        val addition: TextView = findViewById(R.id.tvAddition)
//        val subtraction: TextView = findViewById(R.id.tvSubtract)
//        val multiplication: TextView = findViewById(R.id.tvMultiply)
//        val divide: TextView = findViewById(R.id.tvDivide)
//        val mod: TextView = findViewById(R.id.tvPercent)
//        val dot: TextView = findViewById(R.id.tvDot)
//        val zero: TextView = findViewById(R.id.tv0)
//        val doubleZero: TextView = findViewById(R.id.tv00)
//        val backspace: TextView = findViewById(R.id.tvBackspace)
//
//
//        equals.setOnTouchListener{ view: View, motionEvent: MotionEvent ->
//            click(equals, display,motionEvent)
//            return@setOnTouchListener true
//        }
//
//        ac.setOnTouchListener{ view: View, motionEvent: MotionEvent ->
//            click(ac, display,motionEvent)
//            return@setOnTouchListener true
//        }
//
//        backspace.setOnTouchListener{ view: View, motionEvent: MotionEvent ->
//            click(backspace, display,motionEvent)
//            return@setOnTouchListener true
//        }
//
//        one.setOnTouchListener{ view: View, motionEvent: MotionEvent ->
//            click(one, display,motionEvent)
//            return@setOnTouchListener true
//        }
//
//        one.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(one, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        two.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(two, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        three.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(three, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        four.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(four, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        five.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(five, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        six.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(six, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        seven.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(seven, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        eight.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(eight, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        nine.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(nine, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        addition.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(addition, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        mod.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(mod, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        divide.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(divide, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        multiplication.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(multiplication, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        subtraction.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(subtraction, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        dot.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(dot, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        zero.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(zero, display, motionEvent)
//            return@OnTouchListener true
//        })
//
//        doubleZero.setOnTouchListener (View.OnTouchListener { view, motionEvent ->
//            click(doubleZero, display, motionEvent)
//            return@OnTouchListener true
//        })


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
            MotionEvent.ACTION_DOWN -> {
                id.setBackgroundResource(R.drawable.inverted_oval_shape)

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

            MotionEvent.ACTION_UP -> {
                id.setBackgroundResource(R.drawable.oval_shape)
            }
        }
    }



}
