package com.JuanSalvadorGarcia.upaxtest.data.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NetworkHandler @Inject constructor( context: Context) {

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _isConnected = MutableStateFlow(false)
    val isConnected: Flow<Boolean> = _isConnected

    init {
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _isConnected.value = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                _isConnected.value = false
            }

        })
    }
}