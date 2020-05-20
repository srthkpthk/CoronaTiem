package com.srthk.coronatiem.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.srthk.coronatiem.data.db.AppDatabase
import com.srthk.coronatiem.data.db.entries.Statewise
import com.srthk.coronatiem.data.network.API
import com.srthk.coronatiem.data.network.SafeApiRequest
import com.srthk.coronatiem.data.preference.PreferenceProvider
import com.srthk.coronatiem.util.MIN_INTERVAL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class NationalDataRepository(
    private val api: API,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {
    private val nationalData = MutableLiveData<List<Statewise>>()

    init {
        nationalData.observeForever {
            saveNationalData(it)
        }
    }

    suspend fun getNationalData(): LiveData<List<Statewise>> =
        withContext(Dispatchers.IO) {
            fetchNationalData()
            db.getNationalDataDao().getNationalData()
        }

    private suspend fun fetchNationalData() {
        val lastSaved = prefs.getLastSavedAt()
        if (lastSaved == null || fetchNeeded(LocalDateTime.parse(lastSaved))) {
            try {
                val response = apiRequest { api.getNationalData() }
                nationalData.postValue(response.statewise)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun fetchNeeded(dataSavedAt: LocalDateTime): Boolean =
        ChronoUnit.HOURS.between(dataSavedAt, LocalDateTime.now()) > MIN_INTERVAL


    private fun saveNationalData(it: List<Statewise>) {
        CoroutineScope(Dispatchers.IO).launch {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getNationalDataDao().saveNationalData(it)
        }

    }

}