package com.tsvilla.optimus.presentation


import android.content.Context
import android.net.Uri
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.wear.ambient.AmbientModeSupport
import com.google.android.gms.wearable.*
import com.tsvilla.optimus.R
import com.tsvilla.optimus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    DataClient.OnDataChangedListener,
    MessageClient.OnMessageReceivedListener,
    CapabilityClient.OnCapabilityChangedListener,
    AmbientModeSupport.AmbientCallbackProvider {


    private var activityContext: Context? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var ambientController: AmbientModeSupport.AmbientController


    private class MyAmbientCallback : AmbientModeSupport.AmbientCallback() {

        override fun onEnterAmbient(ambientDetails: Bundle?) {
            // Handle entering ambient mode
        }

        override fun onExitAmbient() {
            // Handle exiting ambient mode
        }

        override fun onUpdateAmbient() {
            // Update the content
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityContext = this
        ambientController = AmbientModeSupport.attach(this)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        setupNavigation()
    }


    private fun initView() {

    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }


    override fun onPause() {
        super.onPause()
        try {
            Wearable.getDataClient(activityContext!!).removeListener(this)
            Wearable.getMessageClient(activityContext!!).removeListener(this)
            Wearable.getCapabilityClient(activityContext!!).removeListener(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onDataChanged(p0: DataEventBuffer) {
    }

    override fun onResume() {
        super.onResume()
        try {
            Wearable.getDataClient(activityContext!!).addListener(this)
            Wearable.getMessageClient(activityContext!!).addListener(this)
            Wearable.getCapabilityClient(activityContext!!)
                .addListener(this, Uri.parse("wear://"), CapabilityClient.FILTER_REACHABLE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCapabilityChanged(p0: CapabilityInfo) {
    }

    override fun onMessageReceived(p0: MessageEvent) {
        TODO("Not yet implemented")
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = MyAmbientCallback()

}