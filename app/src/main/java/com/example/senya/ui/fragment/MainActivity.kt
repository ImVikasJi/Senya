package com.example.senya.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.senya.R
import com.example.senya.viewModel.AttractionsViewModel

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration



    val viewModel: AttractionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        viewModel.init(this)
        viewModel.factSelectedLiveData.observe(this){ attraction ->
            val stringBuilder = StringBuilder("")
            attraction.facts.forEach {
                stringBuilder.append("\u2022 $it")
                stringBuilder.append("\n\n")
            }
            // To fetch the single string from our facts
            val message =
                stringBuilder.toString()
                    .substring(0, stringBuilder.toString().lastIndexOf("\n\n"))
            AlertDialog.Builder(this)
                .setTitle("${attraction.title} Facts")
                .setMessage(message)
                .setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss()
                }.show()
        }
        viewModel.locationSelectedLiveData.observe(this) { attraction ->
            val uri =
                Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=9&q=${attraction.title}")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}