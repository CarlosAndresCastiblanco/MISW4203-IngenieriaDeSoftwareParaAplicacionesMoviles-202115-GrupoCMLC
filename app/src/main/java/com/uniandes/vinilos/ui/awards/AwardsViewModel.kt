package com.uniandes.vinilos.ui.awards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AwardsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is awards Fragment"
    }
    val text: LiveData<String> = _text
}