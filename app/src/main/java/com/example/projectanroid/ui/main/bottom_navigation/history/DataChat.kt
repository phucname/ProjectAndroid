package com.example.projectanroid.ui.main.bottom_navigation.history

data class DataChat(
    val content: String,
    val time: String,
    val main: Boolean
)


val dataTemple = listOf(
    DataChat(
        "Ban ngu chua",
        "19:50",
        false
    ),
    DataChat(
        "Co gi khongpr",
        "19:52",
        true
    ),
    DataChat(
        "I Miss You",
        "20.00",
        false
    ),
    DataChat(
        "zzzzzz",
        "20:10",
        true
    ),
)