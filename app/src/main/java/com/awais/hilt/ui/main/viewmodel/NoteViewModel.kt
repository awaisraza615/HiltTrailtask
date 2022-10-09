package com.awais.hilt.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awais.hilt.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val notesLiveData get() = mainRepository.notesLiveData

    fun getAllNotes() {
        viewModelScope.launch {
            mainRepository.getNotes()
        }
    }
}