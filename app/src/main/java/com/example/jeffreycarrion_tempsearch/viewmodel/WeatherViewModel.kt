package com.example.jeffreycarrion_tempsearch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jeffreycarrion_tempsearch.api.WeatherRepo
import com.example.jeffreycarrion_tempsearch.model.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

const val TAG = "WeatherViewModel"

class WeatherViewModel(
    private val repository: WeatherRepo,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _cityResultData = MutableLiveData<UIState>()
    val cityResultData: LiveData<UIState> get() = _cityResultData

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                TAG,
                "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",
                throwable
            )

        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    fun getDemWeather(city: String, units: String) {
        viewModelSafeScope.launch(dispatcher) {
            //collect from our flow
            repository.getWeatherByCity(city, units).collect { state ->
                //postValue update LiveData asynchronously
                _cityResultData.postValue(state)
            }
        }
    }


    //set value is not asynchronous
    fun setLoading() {
        _cityResultData.value = UIState.Loading
    }
}