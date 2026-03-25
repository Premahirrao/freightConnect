package com.freightconnect.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LanguageHelper {

    private const val PREF_NAME = "app_prefs"
    private const val KEY_LANGUAGE = "selected_language"

    fun setLocale(context: Context, languageCode: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUAGE, languageCode).apply()
        
        updateResources(context, languageCode)
    }

    fun loadLocale(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val language = prefs.getString(KEY_LANGUAGE, "en") ?: "en"
        updateResources(context, language)
    }

    fun getCurrentLanguage(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, "en") ?: "en"
    }

    private fun updateResources(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        
        context.createConfigurationContext(configuration)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    fun getLanguageDisplayName(code: String): String = when (code) {
        "en" -> "English"
        "hi" -> "हिंदी"
        "mr" -> "मराठी"
        "te" -> "తెలుగు"
        else -> "English"
    }
}
