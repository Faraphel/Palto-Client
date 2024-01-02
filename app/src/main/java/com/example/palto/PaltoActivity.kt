package com.example.palto

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels


class PaltoActivity : AppCompatActivity() {

    private var nfcAdapter: NfcAdapter? = null

    private val paltoViewModel: PaltoViewModel by viewModels()

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
        /*
        val url = URL("https://www.faraphel.fr/palto/api/auth/token/")
        val connection = url.openConnection()
        val auth_data = Json.decodeFromString(connection.content)
        */
    }

    override fun onResume() {
        super.onResume()

        nfcAdapter?.enableReaderMode(
            this,
            paltoViewModel.tagLiveData::postValue,
            NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null
        )
    }

    override fun onPause() {
        super.onPause()

        // disable the NFC discovery
        nfcAdapter?.disableReaderMode(this)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun processTag(tag: Tag) {
        Log.d("NFC", "Tag ID : " + tag.id.toHexString())
    }
}
