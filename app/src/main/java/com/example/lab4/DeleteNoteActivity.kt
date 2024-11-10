package com.example.lab4

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DeleteNoteActivity : AppCompatActivity() {

    private lateinit var deleteNoteListView: ListView
    private val notes = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_note)

        deleteNoteListView = findViewById(R.id.deleteNoteListView)
        loadNotes()

        deleteNoteListView.setOnItemClickListener { _, _, position, _ ->
            val noteToDelete = notes[position]
            val sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove(noteToDelete).apply()
            loadNotes()
        }
    }

    private fun loadNotes() {
        val sharedPreferences = getSharedPreferences("notes", MODE_PRIVATE)
        notes.clear()
        sharedPreferences.all.keys.forEach { notes.add(it) }
        deleteNoteListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notes)
    }
}
