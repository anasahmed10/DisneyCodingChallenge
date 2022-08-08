package com.example.disneycodechallenge_anasahmed.model

data class Guest(var firstName: String, var lastName: String, var isChecked: Boolean = false) {
    companion object {
        fun isChecked(list: MutableList<Guest>) : Boolean {
            for (guest in list) {
                if(guest.isChecked) return true
            }
            return false
        }
    }
}