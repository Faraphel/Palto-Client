package com.example.palto

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        // check if the application was started by an intent
        if (this.intent != null) this.processIntent(intent)
    }

    override fun onResume() {
        super.onResume()

        val intent = Intent(
            this,
            this::class.java
        )
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val intentFilter = arrayOf<IntentFilter>()

        // enable the NFC discovery
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, intentFilter, null)
    }

    override fun onPause() {
        super.onPause()

        // disable the NFC discovery
        this.nfcAdapter!!.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        // check the new intent
        if (intent != null) {
            this.processIntent(intent)
        }
    }

    private fun processIntent(intent: Intent) {
        Log.d("NFC", "New : ${intent.action}")

        var text: TextView = this.findViewById(R.id.test_text)
        text.text = intent.action

        if (intent.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
            val tag: Tag? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(NfcAdapter.EXTRA_TAG, Tag::class.java)
            } else {
                intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            }

            Log.d("NFC", "Tag : ${tag?.id.contentToString()}")
        }

        if (intent.action == NfcAdapter.ACTION_TECH_DISCOVERED) {  // TODO(Faraphel): switch ?

            val tag: Tag? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(NfcAdapter.EXTRA_TAG, Tag::class.java)
            } else {
                intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            }

            Log.d("NFC", "Tag : $tag")

        }

    }
}
