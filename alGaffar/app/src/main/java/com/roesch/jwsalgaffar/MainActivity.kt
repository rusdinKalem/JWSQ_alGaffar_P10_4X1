@file:Suppress("DEPRECATION")

package com.roesch.jwsalgaffar

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roesch.jwsalgaffar.utils.BluetoothHandler
import com.roesch.jwsalgaffar.utils.DeviceConnectionReceiver
import com.roesch.jwsalgaffar.utils.PermissionManager
import java.io.IOException

@Suppress("UNUSED_EXPRESSION")
class MainActivity : ComponentActivity() {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private val bondedDevices = mutableListOf<BluetoothDevice>()
    private lateinit var bluetoothHandler: BluetoothHandler
    private lateinit var bluetoothSocket: BluetoothSocket
    private lateinit var device: BluetoothDevice
    private lateinit var progressDialog: ProgressDialog

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PermissionManager(this).requestPermissions()
        bluetoothAdapter = getSystemService(BluetoothManager::class.java).adapter
        progressDialog = ProgressDialog(this)

        val filter = IntentFilter()
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
        registerReceiver(DeviceConnectionReceiver(), filter)

        if (!bluetoothAdapter.isEnabled) {
            val requestBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(requestBtIntent, 0)
            finish()
            startActivity(intent)
        }

        // Add bonded bluetooth devices to list
        for (device in bluetoothAdapter.bondedDevices) {
            bondedDevices.add(device)
        }
        setContent {
            com.roesch.jwsalgaffar.ui.theme.JWSAlGaffarTheme {
                MainUI(bondedDevices = bondedDevices)
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Composable
    fun MainUI(
        bondedDevices: MutableList<BluetoothDevice>
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg01),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.35f
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 45.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "KONEKSI BLUETOOTH",
                    fontSize = 24.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color(0xFF1B1B1B)
                )
                Text(
                    text = "Pilih Modul JWS yang Terpasang (HC-05)",
                    fontSize = 15.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                    color = Color(0xFF00796B)
                )

                Spacer(modifier = Modifier.height(25.dp))

                if (bondedDevices.isEmpty()) {
                    androidx.compose.material3.Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = androidx.compose.material3.CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.90f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Tidak Ada Perangkat Bluetooth",
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFFD32F2F)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Silakan pasangkan (pair) modul Bluetooth JWS (HC-05 / HC-06) di menu Pengaturan Bluetooth smartphone Anda terlebih dahulu.",
                                fontSize = 14.sp,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                color = Color(0xFF424242)
                            )
                        }
                    }
                } else {
                    for (device in bondedDevices) {
                        PairedDevices(name = device.name ?: "Unknown Device", address = device.address)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }

    @Composable
    private fun PairedDevices(name: String?, address: String?) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White.copy(alpha = 0.92f),
                    shape = RoundedCornerShape(14.dp)
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0xFF00796B)
                )
                .padding(horizontal = 14.dp)
                .height(68.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name ?: "Unknown",
                    fontSize = 16.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color(0xFF1B1B1B)
                )
                Text(
                    text = address ?: "",
                    fontSize = 12.sp,
                    color = Color(0xFF757575)
                )
            }

            Button(
                modifier = Modifier
                    .height(40.dp)
                    .width(115.dp),
                onClick = {
                    device = bluetoothAdapter.getRemoteDevice(address)
                    showDialog(device)
                    Thread { connectToDevice(address.toString()) }.start()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "CONNECT",
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }


    private var count = 2


    @SuppressLint("MissingPermission")
    private fun connectToDevice(deviceAddress: String) {
        val success = com.roesch.jwsalgaffar.utils.BluetoothConnectionHolder.connect(bluetoothAdapter, deviceAddress)
        runOnUiThread {
            progressDialog.dismiss()
            if (success) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("Address", deviceAddress)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Koneksi ke HC-05 gagal. Pastikan modul Bluetooth menyala dan berada dalam jangkauan.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun showDialog(bluetoothDevice: BluetoothDevice) {
        progressDialog.setTitle("Menghubungkan...")
        progressDialog.setMessage("Menyambungkan ke ${bluetoothDevice.name ?: bluetoothDevice.address}")
        progressDialog.setCancelable(true)
        progressDialog.setOnCancelListener {
            com.roesch.jwsalgaffar.utils.BluetoothConnectionHolder.close()
            Toast.makeText(this, "Koneksi dibatalkan", Toast.LENGTH_SHORT).show()
        }
        progressDialog.show()
    }
}
