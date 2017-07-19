package com.devmasterteam.festafimdeano.util

import android.content.Context

/**
 * Classe usada para encapsular funcionamento do SharedPreferences.
 * */
class SecurityPreferences(context: Context) {

    // Acesso SharedPreferences
    private val mSharedPreferences = context.getSharedPreferences("FimDeAno", Context.MODE_PRIVATE)

    /**
     * Salva um valor a partir de uma chave
     * */
    fun storeString(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    /**
     * Recupera um valor a partir de uma chave
     * */
    fun getString(key: String): String {
        return mSharedPreferences.getString(key, "")
    }

}