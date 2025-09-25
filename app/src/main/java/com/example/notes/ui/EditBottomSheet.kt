package com.example.notes.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notes.data.MyDatabase
import com.example.notes.databinding.BottomEditLayoutBinding
import com.example.notes.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.concurrent.thread

class EditBottomSheet(var noteID: Int) : BottomSheetDialogFragment() {
    lateinit var binding: BottomEditLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomEditLayoutBinding.inflate(inflater, container, false)
        onSetupFragment()
        binding.createNoteButton.setOnClickListener {
            onUpdateNote()
        }
        return binding.root

    }

}

private fun EditBottomSheet.onSetupFragment() {
    thread {
        val database = MyDatabase.getDatabase(requireContext())
        val noteDao = database.noteDao()
        var note = noteDao.getNoteById(noteID)
        requireActivity().runOnUiThread {
            binding.etTtitle.setText(note.title)
            binding.etContent.setText(note.content)
        }
    }
}

private fun EditBottomSheet.onUpdateNote() {
    var newTitle = binding.etTtitle.text.toString()
    var newContent = binding.etContent.text.toString()
    thread {
        val database = MyDatabase.getDatabase(requireContext())
        val noteDao = database.noteDao()
        noteDao.updateNote(title = newTitle, content = newContent, id = noteID)
        requireActivity().runOnUiThread {
            dismiss()
            var builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Note Updated")
            builder.setMessage("Note Updated Successfully")
            builder.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }
    }
}

