package com.example.hw_1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputText = findViewById<EditText>(R.id.input_text)
        val showButton = findViewById<Button>(R.id.show_button)
        val resultTextView = findViewById<TextView>(R.id.result_text_view)

        showButton.setOnClickListener {
            val input = inputText.text.toString().toInt()
            val longForm = convertToLongForm(input)
            resultTextView.text = longForm
            Toast.makeText(this, longForm, Toast.LENGTH_SHORT).show()
        }
    }
    private val LESS_THAN_20 = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    private val TENS = arrayOf("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
    fun convertToLongForm(num: Int): String {
        if (num == 0) return "zero"

        val billion = num / 1000000000
        val million = (num - billion * 1000000000) / 1000000
        val thousand = (num - billion * 1000000000 - million * 1000000) / 1000
        val hundred = (num - billion * 1000000000 - million * 1000000 - thousand * 1000) / 100
        val rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000 - hundred * 100

        var result = ""
        if (billion != 0) {
            result = convertToLongForm(billion) + " billion"
        }
        if (million != 0) {
            if (result.isNotEmpty()) {
                result += " "
            }
            result += convertToLongForm(million) + " million"
        }
        if (thousand != 0) {
            if (result.isNotEmpty()) {
                result += " "
            }
            result += convertToLongForm(thousand) + " thousand"
        }
        if (hundred != 0) {
            if (result.isNotEmpty()) {
                result += " "
            }
            result += convertToLongForm(hundred) + " hundred"
        }
        if (rest != 0) {
            if (result.isNotEmpty()) {
                result += " "
            }
            if (rest < 20) {
                result += LESS_THAN_20[rest]
            } else {
                result += TENS[rest / 10]
                if (rest % 10 != 0) {
                    result += " " + LESS_THAN_20[rest % 10]
                }
            }
        }
        return result
    }

}

