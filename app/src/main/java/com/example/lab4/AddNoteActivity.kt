package com.example.lab4

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddNoteActivity : AppCompatActivity() {

    private lateinit var noteTitleInput: EditText
    private lateinit var noteContentInput: EditText
    private lateinit var saveNoteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        noteTitleInput = findViewById(R.id.noteTitleInput)
        noteContentInput = findViewById(R.id.noteContentInput)
        saveNoteButton = findViewById(R.id.saveNoteButton)

        saveNoteButton.setOnClickListener {
            val title = noteTitleInput.text.toString().trim()
            val content = noteContentInput.text.toString().trim()

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_note_warning), Toast.LENGTH_SHORT).show()
            } else {
                val sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE)
                sharedPreferences.edit().putString(title, content).apply()
                finish()
            }
        }
    }
}
