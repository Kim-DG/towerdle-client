package com.keunsori.towerdle.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val REFRESH_TOKEN_KEY = stringPreferencesKey("REFRESH_TOKEN_KEY")
    }

    private val refreshToken: Flow<String?> = dataStore.data.map {
        it[REFRESH_TOKEN_KEY]
    }

    private var _accessToken: String = ""
    val accessToken: String
        get() = _accessToken

    suspend fun setRefreshToken(
        test: String,
    ) {
        dataStore.edit {
            it[REFRESH_TOKEN_KEY] = test
        }
    }

    suspend fun getRefreshToken(): String {
        return refreshToken.first()?:""
    }

    fun setAccessToken(accessToken: String){
        _accessToken = accessToken
    }

    suspend fun deleteToken(){
        setRefreshToken("")
        setAccessToken("")
    }
}