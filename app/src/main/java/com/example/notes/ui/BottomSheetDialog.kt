package com.example.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notes.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog(private val noteId: Int, private val onDelete:(Int)->Unit): BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        binding.deleteBtn.setOnClickListener {
            onDelete(id)
            dismiss()
        }
        return binding.root
    }
}