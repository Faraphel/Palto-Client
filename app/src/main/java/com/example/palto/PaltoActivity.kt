package com.example.palto

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class PaltoActivity : AppCompatActivity() {
    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // get the NFC Adapter
        this.nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        // check if NFC is supported
        if (this.nfcAdapter == null) {
            Log.e("NFC", "NFC is not supported")
            return
        }

        // check if NFC is disabled
        if (!(this.nfcAdapter!!.isEnabled)) {
            Log.w("NFC", "NFC is not enabled")
        }
    }

    override fun onResume() {
        super.onResume()

        nfcAdapter!!.enableReaderMode(
            this,
            this::processTag,
            NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null
        )
    }

    override fun onPause() {
        super.onPause()

        // disable the NFC discovery
        this.nfcAdapter!!.disableReaderMode(this)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun processTag(tag: Tag) {
        Log.d("NFC", "Tag ID : ${tag.id.toHexString()}")
    }
}
