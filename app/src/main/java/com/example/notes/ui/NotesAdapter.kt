package com.example.notes.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemNoteBinding

class NotesAdapter(var notesList: List<Note>, val context: Context) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val content = binding.content
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        val note = notesList[position]
        holder.title.text = note.title
        holder.content.text = note.content
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

}