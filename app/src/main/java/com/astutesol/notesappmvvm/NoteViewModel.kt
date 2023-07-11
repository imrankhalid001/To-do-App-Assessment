package com.astutesol.notesappmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astutesol.notesappmvvm.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {


    val callLiveData get() = noteRepository.callListLiveData
    val buyLiveData get() = noteRepository.buyListLiveData


    fun getCallListVm() {

        viewModelScope.launch {
            noteRepository.getCallList()
        }

    }

    fun getBuyListVm() {

        viewModelScope.launch {
            noteRepository.getBuyList()
        }

    }



}