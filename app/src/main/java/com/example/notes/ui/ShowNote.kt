package com.example.notes.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notes.R
import com.example.notes.data.entities.NoteEntity
import com.example.notes.databinding.ActivityShowNoteBinding

class ShowNote : AppCompatActivity() {
    lateinit var binding: ActivityShowNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityShowNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val note = intent.getSerializableExtra("note") as NoteEntity
        binding.title.text = note.title
        binding.body.text = note.content
        binding.editBtn.setOnClickListener {
            val editBottomSheet = EditBottomSheet(note.id)
            editBottomSheet.show(supportFragmentManager, "editBottomSheet")
        }


    }
}