package com.example.hw_1
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputText = findViewById<EditText>(R.id.input_text)
        val showButton = findViewById<Button>(R.id.show_button)
        val resultTextView = findViewById<TextView>(R.id.result_text_view)

        showButton.setOnClickListener {
            val input = inputText.text.toString().toLong()
            val longForm = convertToLongForm(input)
            resultTextView.text = longForm
            Toast.makeText(this, longForm, Toast.LENGTH_SHORT).show()
        }
    }
    private val ONES = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    private val TENS = arrayOf("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    fun convertToLongForm(num: Long): String {
        if (num < 0) return "minus " + convertToLongForm(-num)
        if (num < 20) return ONES[num.toInt()]
        if (num < 100) return TENS[num.toInt() / 10] + (if (num.toInt() % 10 == 0) "" else " " + ONES[num.toInt() % 10])
        if (num < 1000) return ONES[num.toInt() / 100] + " hundred " + convertToLongForm(num % 100)
        if (num < 1000000) return convertToLongForm(num / 1000) + " thousand " + convertToLongForm(num % 1000)
        if (num < 1000000000) return convertToLongForm(num / 1000000) + " million " + convertToLongForm(num % 1000000)
        if (num < 1000000000000L) return convertToLongForm(num / 1000000000) + " billion " + convertToLongForm(num % 1000000000)
        return "Too large"
    }
}
