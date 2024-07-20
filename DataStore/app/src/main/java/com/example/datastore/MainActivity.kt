package com.example.datastore

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

val Context.dataStore by preferencesDataStore(name = "MyPreferences_001")

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnSimplePref: Button
    private lateinit var btnFancyPref: Button
    private lateinit var txtCaption1: TextView
    private lateinit var myLayoutlVertical: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myLayoutlVertical = findViewById(R.id.linLayout1Vertical)
        txtCaption1 = findViewById(R.id.textView)

        btnSimplePref = findViewById(R.id.simpleUIButton)
        btnSimplePref.setOnClickListener(this)
        btnFancyPref = findViewById(R.id.fancyUIButton)
        btnFancyPref.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Sử dụng DataStore
        runBlocking {
            applySavedPreference()
        }
    }

    override fun onClick(v: View) {
        runBlocking {
            if (v.id == btnSimplePref.id) {
                dataStore.edit { settings ->
                    settings[intPreferencesKey("backColor")] = Color.BLACK
                    settings[intPreferencesKey("textsize")] = 12
                    settings[intPreferencesKey("layoutColor")] = Color.DKGRAY
                }
            } else {
                dataStore.edit { settings ->
                    settings[intPreferencesKey("backColor")] = Color.BLUE
                    settings[intPreferencesKey("textsize")] = 20
                    settings[stringPreferencesKey("textStyle")] = "bold"
                    settings[intPreferencesKey("layoutColor")] = Color.GREEN
                }
            }
            applySavedPreference()
        }
    }

    suspend fun applySavedPreference() {
        val backColor = dataStore.data.first()[intPreferencesKey("backColor")] ?: Color.BLACK
        val textSize = dataStore.data.first()[intPreferencesKey("textSize")] ?: 12
        val textStyle = dataStore.data.first()[stringPreferencesKey("textstyle")] ?: "normal"
        val layoutColor = dataStore.data.first()[intPreferencesKey("layoutColor")] ?: Color.DKGRAY

        val msg = "color $backColor\n" +
                "size $textSize\n" +
                "style $textStyle"
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

        txtCaption1.setBackgroundColor(backColor)
        txtCaption1.setTextSize(textSize.toFloat())

        if (textStyle == "normal") {
            txtCaption1.setTypeface(Typeface.SERIF, Typeface.NORMAL)
        } else {
            txtCaption1.setTypeface(Typeface.SERIF, Typeface.BOLD)
        }

        myLayoutlVertical.setBackgroundColor(layoutColor)
    }
}
