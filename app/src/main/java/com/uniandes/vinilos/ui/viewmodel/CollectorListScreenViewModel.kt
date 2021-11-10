package com.uniandes.vinilos.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniandes.vinilos.model.Collector
import com.uniandes.vinilos.repository.CollectorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CollectorListScreenViewModel @Inject constructor(
    private val repository: CollectorsRepository
) : ViewModel() {

    private val _collectors = MutableLiveData<List<Collector>>()

    fun getCollectors(): LiveData<List<Collector>> {
        viewModelScope.launch(Dispatchers.IO) {
            val collectors = repository.getCollectors()
            _collectors.postValue(collectors)
        }
        return _collectors
    }
}