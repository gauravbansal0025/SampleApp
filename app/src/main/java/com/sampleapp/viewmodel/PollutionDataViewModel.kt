package com.sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sampleapp.model.PollutionData
import com.sampleapp.repository.PollutionRepository
import kotlinx.coroutines.launch

class PollutionDataViewModel :ViewModel()
{
    private val pollutionRepository:PollutionRepository by lazy {
        PollutionRepository()
    }
    private var _polluList = MediatorLiveData<PollutionData>()
    val polluList: LiveData<PollutionData>
        get() = _polluList


    fun getpollutionData(dateTime:String,time:String) {

        viewModelScope.launch {
            val apList = pollutionRepository.getPollutionData(dateTime,time)
            _polluList.postValue(apList.body())
        }
    }
}