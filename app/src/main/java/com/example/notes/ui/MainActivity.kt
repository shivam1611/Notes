package com.example.notes.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var notesList: List<Note>
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myRecyclerView = binding.recyclerView
        notesList = listOf(
            Note("Grocery List", "Buy milk, eggs, and bread and also having foog tu niyam kanoon baataye tera paar teri maaa tuh arkh rakh rakha sale"),
            Note("Workout Plan", "Morning run at 6 AM, yoga at 7 AM"),
            Note("Project Ideas", "Build a notes app using Room DB"),
            Note("Exam Schedule", "Math on Monday, Physics on Wednesday"),
            Note("Book to Read", "Atomic Habits by James Clear"),
            Note("Shopping List", "New shoes, headphones, and a backpack"),
            Note("Meeting Notes", "Discuss app features with the team"),
            Note("Birthday Reminder", "Mom’s birthday on 10th October"),
            Note("Travel Plan", "Visit Manali next month"),
            Note("Learning Goals", "Master Kotlin Coroutines this week")
        )

        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.adapter = NotesAdapter(notesList, this)

    }
}