package com.example.projectanroid.Firebase.conmon

object contanst {
    val urlStore = "gs://doan-f52ef.appspot.com/"

}

public final data class DataState<T>(
    val isLoading: Boolean = false,
    val data: T? = null ,
    val error: String = ""
)
