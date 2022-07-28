package com.example.jeffreycarrion_tempsearch.model

sealed class UIState {
    object Loading: UIState()
    class Error(val errorMessage: Exception): UIState()
    class Success(val response: TempResponse):UIState()

}