package com.example.sisvitafrontend.navigation

import android.app.Application

class ContextAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicialización global si es necesario
        instance = this
    }

    companion object {
        private var instance: ContextAplication? = null

        fun applicationContext() : ContextAplication {
            return instance as ContextAplication
        }
    }
}