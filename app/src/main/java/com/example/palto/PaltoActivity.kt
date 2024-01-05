package com.example.palto

import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.palto.ui.NfcViewModel
import com.example.palto.ui.UserViewModel


class PaltoActivity : AppCompatActivity() {

    private var nfcAdapter: NfcAdapter? = null

    private val nfcViewModel: NfcViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get the NFC Adapter
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        // check if NFC is supported
        if (nfcAdapter == null) {
            Log.e("NFC", "NFC is not supported")
            return
        }

        // check if NFC is disabled
        if (nfcAdapter?.isEnabled == false) {
            Log.w("NFC", "NFC is not enabled")
        }

        setContentView(R.layout.activity_palto)
    }

    override fun onResume() {
        super.onResume()

        nfcAdapter?.enableReaderMode(
            this,
            nfcViewModel.tag::postValue,
            NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null
        )
    }

    override fun onPause() {
        super.onPause()

        // disable the NFC discovery
        nfcAdapter?.disableReaderMode(this)
    }

    /*
    @OptIn(ExperimentalStdlibApi::class)
    fun processTag(tag: Tag) {
        Log.d("NFC", "Tag ID : " + tag.id.toHexString())
    }
     */
}
