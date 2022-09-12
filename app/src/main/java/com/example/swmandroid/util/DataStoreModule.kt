package com.example.swmandroid.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreModule(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "data_store")

    private val emailKey = stringPreferencesKey("email")
    private val expKey = intPreferencesKey("exp")
    private val nickNameKey = stringPreferencesKey("nickName")
    private val techStackKey = stringPreferencesKey("techStack")
    private val tierKey = stringPreferencesKey("tier")
    private val userRoleKey = stringPreferencesKey("userRole")
    private val userTokenKey = stringPreferencesKey("userToken")

    val email: Flow<String> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[emailKey] ?: ""
            }

    val exp: Flow<Int> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[expKey] ?: 0
            }

    val nickName: Flow<String> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[nickNameKey] ?: ""
            }

    val techStack: Flow<String> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[techStackKey] ?: ""
            }

    val tier: Flow<String> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[tierKey] ?: ""
            }

    val userRole: Flow<String> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[userRoleKey] ?: ""
            }

    val userToken: Flow<String> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[userTokenKey] ?: ""
            }

    suspend fun setEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[emailKey] = email
        }
    }

    suspend fun setExp(exp: Int) {
        context.dataStore.edit { preferences ->
            preferences[expKey] = exp
        }
    }

    suspend fun setNickName(nickName: String) {
        context.dataStore.edit { preferences ->
            preferences[nickNameKey] = nickName
        }
    }

    suspend fun setTechStack(techStack: String) {
        context.dataStore.edit { preferences ->
            preferences[techStackKey] = techStack
        }
    }

    suspend fun setTier(tier: String) {
        context.dataStore.edit { preferences ->
            preferences[tierKey] = tier
        }
    }

    suspend fun setUserRole(userRole: String) {
        context.dataStore.edit { preferences ->
            preferences[userRoleKey] = userRole
        }
    }

    suspend fun setUserToken(userToken: String) {
        context.dataStore.edit { preferences ->
            preferences[userTokenKey] = userToken
        }
    }

}