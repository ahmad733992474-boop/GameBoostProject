package com.example.gameboost

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnBoost: Button
    private lateinit var switchDnd: Switch
    private lateinit var switchAuto: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBoost = findViewById(R.id.btnBoost)
        switchDnd = findViewById(R.id.switchDnd)
        switchAuto = findViewById(R.id.switchAuto)

        btnBoost.setOnClickListener {
            applyBoost()
        }

        switchDnd.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                requestDndPermissionIfNeeded()
            } else {
                disableDndIfNeeded()
            }
        }
    }

    private fun applyBoost() {
        if (switchDnd.isChecked) {
            enableDndIfAllowed()
        }
        if (!hasUsageAccess()) {
            Toast.makeText(this, "Please grant Usage Access (Settings) for better boosting", Toast.LENGTH_LONG).show()
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }

        // Placeholder: Request Game Mode / Frame Rate APIs here if available on device.
        Toast.makeText(this, "Boost applied (placeholders). For full effect, grant permissions & use device-specific SDKs.", Toast.LENGTH_LONG).show()
    }

    private fun hasUsageAccess(): Boolean {
        try {
            val appOps = getSystemService(Context.APP_OPS_SERVICE) as android.app.AppOpsManager
            val mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), packageName)
            return mode == android.app.AppOpsManager.MODE_ALLOWED
        } catch (e: Exception) {
            return false
        }
    }

    private fun requestDndPermissionIfNeeded() {
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (!nm.isNotificationPolicyAccessGranted) {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            startActivity(intent)
        } else {
            Toast.makeText(this, "DND access already granted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun enableDndIfAllowed() {
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (!nm.isNotificationPolicyAccessGranted) {
            Toast.makeText(this, "DND access is not granted. Opening settings...", Toast.LENGTH_LONG).show()
            startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
            return
        }
        nm.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
    }

    private fun disableDndIfNeeded() {
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (nm.isNotificationPolicyAccessGranted) {
            nm.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
        }
    }
}
