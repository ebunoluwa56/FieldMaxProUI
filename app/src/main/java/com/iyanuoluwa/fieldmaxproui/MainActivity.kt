package com.iyanuoluwa.fieldmaxproui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.login_button)
        val emailText = findViewById<EditText>(R.id.email)
        val passwordText = findViewById<EditText>(R.id.password)

        loginButton.setOnClickListener {
            val email = emailText.text.toString().trim()
            val password = passwordText.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val intent = Intent(this, ScheduleActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Empty fields not allowed!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}