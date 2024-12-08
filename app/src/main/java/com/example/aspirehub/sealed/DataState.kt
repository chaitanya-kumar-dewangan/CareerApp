package com.example.aspirehub.sealed

import com.example.aspirehub.models.MathsPG

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failure(val message: String) : DataState<Nothing>()
    data class Error(val exception: Throwable) : DataState<Nothing>()
    object Empty : DataState<Nothing>()
}

