package com.demo

import android.app.Application
import android.content.Context
import com.demo.mvvm.factories.HomeViewModelFactory
import com.demo.network.ApiService
import com.demo.network.NetworkConnectionInterceptor
import com.demo.network.repository.ImageRepository


import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ApplicationClass : Application(),KodeinAware {

    override fun onCreate() {
        super.onCreate()
    }


    override val kodein = Kodein.lazy {

        import(androidXModule(this@ApplicationClass))


        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiService(instance()) }

        //Reposs
        bind() from singleton { ImageRepository(instance()) }


        //Factoryyyy
        bind() from provider { HomeViewModelFactory(instance()) }




    }

}