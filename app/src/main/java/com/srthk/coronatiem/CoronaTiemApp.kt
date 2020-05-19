package com.srthk.coronatiem

import android.app.Application
import com.srthk.coronatiem.data.db.AppDatabase
import com.srthk.coronatiem.data.network.API
import com.srthk.coronatiem.data.network.NetworkConnectionInterceptor
import com.srthk.coronatiem.data.preference.PreferenceProvider
import com.srthk.coronatiem.data.repository.NotionalDataRepository
import com.srthk.coronatiem.ui.Home.NationalViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CoronaTiemApp : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@CoronaTiemApp))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { API(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { NotionalDataRepository(instance(), instance(), instance()) }
        bind() from provider { NationalViewModelFactory(instance()) }
    }
}