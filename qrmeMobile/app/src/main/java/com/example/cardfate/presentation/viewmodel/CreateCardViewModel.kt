package com.example.cardfate.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardfate.domain.entity.Card
import com.example.cardfate.domain.usecases.LogInUseCase
import com.example.cardfate.domain.usecases.UploadCardUseCase
import com.example.cardfate.presentation.state.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateCardViewModel @Inject constructor(
    private val uploadCardUseCase: UploadCardUseCase,
) : ViewModel() {

    private var _uploadCardState = MutableLiveData<UploadCardState>()
    val uploadCardState: LiveData<UploadCardState> = _uploadCardState

    fun uploadCard(card: Card) {
        _uploadCardState.value = UploadProgress
        viewModelScope.launch {
            uploadCardUseCase.invoke(card) {
                try {
                    _uploadCardState.value = UploadSuccess
                } catch (e: Exception){
                    _uploadCardState.value = UploadError
                }
            }
        }
    }
}