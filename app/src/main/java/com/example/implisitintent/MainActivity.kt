package com.example.implisitintent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val _btnKirimPesan = findViewById<Button>(R.id.btnKirimPesan)

        _btnKirimPesan.setOnClickListener {
            val _sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra("address", "081112")
                putExtra("sms_body", "isi SMS")
                type = "text/plain"
            }

            if (_sendIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(_sendIntent, "Pilih Aplikasi"))
            }
        }

        val _btnSetAlarm = findViewById<Button>(R.id.btnSetAlarm)

        _btnSetAlarm.setOnClickListener {
            val _alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Coba Alarm")
                putExtra(AlarmClock.EXTRA_HOUR, 20)
                putExtra(AlarmClock.EXTRA_MINUTES, 15)
                putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            }
            startActivity(_alarmIntent)
        }

        val _btnSetTimer = findViewById<Button>(R.id.btnSetTimer)

        _btnSetTimer.setOnClickListener {
            val _timerIntent = Intent(AlarmClock.ACTION_SET_TIMER).apply{
                putExtra(AlarmClock.EXTRA_MESSAGE, "Coba TImer")
                putExtra(AlarmClock.EXTRA_LENGTH, 20)
                putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            }

            startActivity(_timerIntent)
        }

        val _etURL = findViewById<EditText>(R.id.etURL)
        val _btnOpenURL = findViewById<Button>(R.id.btnOpenURL)

        _btnOpenURL.setOnClickListener {
            var _webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://" + _etURL.text.toString())
            )

            if(intent.resolveActivity(packageManager) != null) {
                startActivity(_webIntent)
            } else {
                Toast.makeText(
                    this,
                    "Tidak ada aplikasi Browser ditemukan",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}