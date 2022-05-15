package com.tsvilla.optimus.presentation

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.tsvilla.optimus.R
import com.google.android.gms.wearable.*

class MainActivity : AppCompatActivity(),
    DataClient.OnDataChangedListener,
    MessageClient.OnMessageReceivedListener,
    CapabilityClient.OnCapabilityChangedListener {

    private var activityContext: Context? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityContext = this
        setContentView(R.layout.activity_main)
        setupNavigation()
        Toast.makeText(applicationContext, "Hello World", Toast.LENGTH_SHORT).show()
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
}