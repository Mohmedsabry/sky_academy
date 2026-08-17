package com.centerk.secretary.util

import android.content.Context
import androidx.core.content.edit
import com.core.core_librarys.util.ContextExt

class ContextExtImp(
    private val context: Context
) : ContextExt() {
    val credentialFile = "credential"
    val accessToken = "TOKEN"
    val secretaryRefreshToken = "REFRESH_TOKEN"
    val isLoggedIn = "IS_LOGGED_IN"
    val mainLanguage = "LANGUAGE"
    val mainUiMode = "UI_MODE"

    val securePasswordSaver by lazy {
        context.getSecurePrefs(credentialFile)
    }

    override fun getString(res: Int): String {
        return context.getLocaleContext().getString(res)
    }

    override fun getString(res: Int, vararg args: Any): String {
        return context.getLocaleContext().getString(res, *args)
    }

    override fun getToken(): String? = securePasswordSaver.getString(accessToken, null)


    override fun getRefreshToken(): String? =
        securePasswordSaver.getString(secretaryRefreshToken, null)

    override fun isLoggedIn(): Boolean = securePasswordSaver.getBoolean(isLoggedIn, false)

    override fun logout() {
        securePasswordSaver.edit {
            remove(accessToken)
            remove(secretaryRefreshToken)
            remove(isLoggedIn)
        }
    }

    override fun setToken(token: String) {
        securePasswordSaver.edit {
            putString(accessToken, token)
        }
    }

    override fun setRefreshToken(token: String) {
        securePasswordSaver.edit {
            putString(secretaryRefreshToken, token)
        }
    }

    override fun getLanguage(): String? {
        return securePasswordSaver.getString(mainLanguage, "ar")
    }

    override fun saveLanguage(localLanguage: String) {
        securePasswordSaver.edit {
            putString(mainLanguage, localLanguage)
        }
    }

    override fun getUiMode(): String? = securePasswordSaver.getString(mainUiMode, null)

    override fun saveUiMode(mode: String) {
        securePasswordSaver.edit {
            putString(mainUiMode,mode)
        }
    }
}