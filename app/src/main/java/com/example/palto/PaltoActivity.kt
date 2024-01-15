package com.example.palto

import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.palto.databinding.ActivityPaltoBinding
import com.example.palto.ui.CardViewModel


class PaltoActivity : AppCompatActivity() {

    private var nfcAdapter: NfcAdapter? = null

    private val nfcViewModel: NfcViewModel by viewModels()

    private lateinit var binding: ActivityPaltoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaltoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        // Toolbar
        //

        // Set the toolbar as the app bar for the activity.
        setSupportActionBar(binding.paltoToolbar)

        // Configure the app bar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.palto_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menuFragment, R.id.loginFragment
            )
        )
        binding.paltoToolbar.setupWithNavController(navController, appBarConfiguration)

        //
        // NFC Adapter
        //

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        // Check if NFC is supported (already checked in the app manifest).
        if (nfcAdapter == null) {
            Log.e("NFC", "NFC is not supported")
        }
        if (nfcAdapter?.isEnabled == false) {
            Log.w("NFC", "NFC is not enabled")
        }
    }

    /**
     * Specify the options menu for the Activity.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.palto_menu, menu)
        return true
    }

    /**
     * Just before the application is displayed.
     */
    override fun onResume() {
        super.onResume()

        // Begin to read NFC Cards.
        nfcAdapter?.enableReaderMode(
            this,
            nfcViewModel::setTag,
            NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null
        )
    }

    /**
     * Just after the application has been quit.
     */
    override fun onPause() {
        super.onPause()

        // Disable the NFC discovery.
        nfcAdapter?.disableReaderMode(this)
    }
}
