package com.example.individual18

class Utils {
    companion object {
        fun isValidInteger(value: String): Boolean {
            return try {
                value.toInt()
                true
            } catch (e: NumberFormatException) {
                false
            }
        }

        fun isValidText(value: String): Boolean {
            val regex = Regex("^[a-zA-Z]+$")
            return value.matches(regex)
        }

        fun isValidFloat(value: String): Boolean {
            return try {
                value.toFloat()
                true
            } catch (e: NumberFormatException) {
                false
            }
        }
    }
}