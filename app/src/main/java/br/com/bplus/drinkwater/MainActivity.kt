package br.com.bplus.drinkwater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val prefs = GlassPreferences(this)
    private var today: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_small).setOnClickListener {
            saveCup(GlassType.SMALL)
        }
        findViewById<Button>(R.id.btn_medium).setOnClickListener {
            saveCup(GlassType.MEDIUM)
        }
        findViewById<Button>(R.id.btn_large).setOnClickListener {
            saveCup(GlassType.LARGE)
        }

        refresh()
    }

    private fun saveCup(glassType: GlassType) {
        prefs.save(today + glassType.value)

        Snackbar.make(findViewById(android.R.id.content), R.string.undo, Snackbar.LENGTH_LONG)
            .setAction(android.R.string.ok) {
                prefs.save(today - glassType.value)
                refresh()
            }
            .show()

        refresh()
    }

    private fun refresh() {
        today = prefs.fetch()
        findViewById<TextView>(R.id.txt_result).text = getString(R.string.result,today)

    }
}