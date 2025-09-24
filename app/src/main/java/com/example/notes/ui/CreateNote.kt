package com.example.notes.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notes.R
import com.example.notes.data.MyDatabase
import com.example.notes.data.dao.NoteDao
import com.example.notes.data.entities.NoteEntity
import com.example.notes.databinding.ActivityCreateNoteBinding
import kotlin.concurrent.thread

class CreateNote : AppCompatActivity() {
    lateinit var database: MyDatabase
    lateinit var noteDao: NoteDao
    lateinit var binding: ActivityCreateNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = MyDatabase.getDatabase(this)
        noteDao = database.noteDao()
        binding.createNoteButton.setOnClickListener {
            val title = binding.etTtitle.text.toString()
            val content = binding.etContent.text.toString()
            if (title.isEmpty() || content.isEmpty()) {
                    binding.etTtitle.error = "Title is required"
                    binding.etContent.error = "Content is required"
            } else {
                thread {
                    noteDao.insertNote(NoteEntity(title = title, content = content))
                    finish()
                }
            }
        }
    }
}
