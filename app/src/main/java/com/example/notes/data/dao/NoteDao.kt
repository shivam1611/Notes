package com.example.notes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notes.data.entities.NoteEntity
import com.example.notes.ui.Note


@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NoteEntity>

    @Delete
    fun deleteNote(note: NoteEntity)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity

    @Query("UPDATE notes SET title = :title, content = :content WHERE id = :id")
    fun updateNote(id: Int, title: String, content: String)

    @Query("SELECT * FROM notes WHERE title LIKE :title")
    fun searchNotesByTitle(title: String): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE content LIKE :content")
    fun searchNotesByContent(content: String): List<NoteEntity>


}