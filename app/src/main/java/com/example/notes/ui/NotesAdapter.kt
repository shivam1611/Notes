package com.example.notes.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.MyDatabase
import com.example.notes.data.entities.NoteEntity
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.databinding.ItemNoteBinding
import kotlin.concurrent.thread

class NotesAdapter(var notesList: List<NoteEntity>, val context: Context) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val content = binding.content
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NotesAdapter.ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        val note = notesList[position]
        holder.title.text = note.title
        holder.content.text = note.content

        // Long hold of note to show delete option
        holder.itemView.setOnLongClickListener {
            val bottomSheetDialog = BottomSheetDialog(note.id) {
                onDeleteNote(note)
            }
            bottomSheetDialog.show(
                (context as MainActivity).supportFragmentManager, "BottomSheetDialog"
            )
            true
        }

        // click to open note
        holder.itemView.setOnClickListener {
            onOpenNote(note)
        }
    }


    private fun onOpenNote(note: NoteEntity) {
        val intent = Intent(context, ShowNote::class.java)
        intent.putExtra("note", note)
        (context as MainActivity).startActivity(intent)
    }


    private fun onDeleteNote(note: NoteEntity) {
        val builder = AlertDialog.Builder(context as MainActivity)
        builder.setTitle("Delete Note")
        builder.setMessage("Are you sure you want to delete this note?")
        builder.setPositiveButton("Yes") { _, _ ->
            thread {
                val database = MyDatabase.getDatabase(context)
                val noteDao = database.noteDao()
                noteDao.deleteNoteById(id = note.id)
            }
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.show()
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

}