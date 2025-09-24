package com.example.notes.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.MyDatabase
import com.example.notes.data.entities.NoteEntity
import com.example.notes.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var notesList: List<NoteEntity>
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myRecyclerView = binding.recyclerView
        thread{
            val database = MyDatabase.getDatabase(this)
            val noteDao = database.noteDao()
            notesList = noteDao.getAllNotes()
            runOnUiThread {
                myRecyclerView.layoutManager = LinearLayoutManager(this)
                myRecyclerView.adapter = NotesAdapter(notesList, this)
                binding.createNoteButton.setOnClickListener {
                    val intent = Intent(this, CreateNote::class.java)
                    startActivity(intent)
                }
            }
        }
    }


}
