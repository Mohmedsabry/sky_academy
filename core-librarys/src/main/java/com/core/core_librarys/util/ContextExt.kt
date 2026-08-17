package com.core.core_librarys.util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.util.Locale

abstract class ContextExt {
    fun Context.getSecurePrefs(fileName: String): SharedPreferences {
        val masterKey = MasterKey.Builder(this).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

        return EncryptedSharedPreferences.create(
            this,
            fileName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun Context.getLocaleContext(): Context {
        val language = getLanguage() ?: "ar"
        val locale = Locale.Builder().setLanguage(language).build()
        val localContext =
            this.createConfigurationContext(Configuration(this.resources.configuration).also {
                it.setLocale(
                    locale
                )
            })
        return localContext
    }

    abstract fun getString(res: Int): String
    abstract fun getString(res: Int, vararg args: Any): String
    abstract fun getToken(): String?
    abstract fun getRefreshToken(): String?
    abstract fun isLoggedIn(): Boolean
    abstract fun logout()
    abstract fun setToken(token: String)
    abstract fun setRefreshToken(token: String)
    abstract fun getLanguage(): String?
    abstract fun saveLanguage(localLanguage: String)
    abstract fun getUiMode(): String?
    abstract fun saveUiMode(mode: String)
}