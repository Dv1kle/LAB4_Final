package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var noteListView: ListView
    private lateinit var addNoteButton: Button
    private lateinit var deleteNoteButton: Button
    private val notes = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteListView = findViewById(R.id.noteListView)
        addNoteButton = findViewById(R.id.addNoteButton)
        deleteNoteButton = findViewById(R.id.deleteNoteButton)

        addNoteButton.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

        deleteNoteButton.setOnClickListener {
            startActivity(Intent(this, DeleteNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes() {
        val sharedPreferences = getSharedPreferences("notes", MODE_PRIVATE)
        notes.clear()

        sharedPreferences.all.forEach { (title, content) ->
            val displayText = "$title\n$content"
            notes.add(displayText)
        }

        noteListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notes)
    }
}
