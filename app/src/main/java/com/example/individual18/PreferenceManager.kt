package com.example.individual18

import android.content.SharedPreferences

class PreferenceManager(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_INT_VALUE = "INT_VALUE"
        const val KEY_TEXT_VALUE = "TEXT_VALUE"
        const val KEY_BOOL_VALUE = "BOOL_VALUE"
        const val KEY_FLOAT_VALUE = "FLOAT_VALUE"
    }

    fun saveIntValue(value: Int) {
        sharedPreferences.edit().putInt(KEY_INT_VALUE, value).apply()
    }

    fun saveTextValue(value: String) {
        sharedPreferences.edit().putString(KEY_TEXT_VALUE, value).apply()
    }

    fun saveBoolValue(value: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_BOOL_VALUE, value).apply()
    }

    fun saveFloatValue(value: Float) {
        sharedPreferences.edit().putFloat(KEY_FLOAT_VALUE, value).apply()
    }

    fun getIntValue(): Int {
        return sharedPreferences.getInt(KEY_INT_VALUE, 0)
    }

    fun getTextValue(): String {
        return sharedPreferences.getString(KEY_TEXT_VALUE, "") ?: ""
    }

    fun getBoolValue(): Boolean {
        return sharedPreferences.getBoolean(KEY_BOOL_VALUE, false)
    }

    fun getFloatValue(): Float {
        return sharedPreferences.getFloat(KEY_FLOAT_VALUE, 0f)
    }

    fun deletePreferences() {
        sharedPreferences.edit().clear().apply()
    }
}