package com.roesch.jwsalgaffar.utils

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.util.UUID

object BluetoothConnectionHolder {
    var socket: BluetoothSocket? = null
    var connectedDeviceAddress: String? = null

    val SPP_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    @SuppressLint("MissingPermission")
    fun createSocket(device: BluetoothDevice): BluetoothSocket {
        // Method 1: Reflection on Channel 1 (paling ampuh untuk HC-06 & clone)
        try {
            val method = device.javaClass.getMethod("createRfcommSocket", Int::class.javaPrimitiveType)
            return method.invoke(device, 1) as BluetoothSocket
        } catch (_: Exception) {}

        // Method 2: Insecure RFCOMM
        try {
            return device.createInsecureRfcommSocketToServiceRecord(SPP_UUID)
        } catch (_: Exception) {}

        // Method 3: Secure RFCOMM
        return device.createRfcommSocketToServiceRecord(SPP_UUID)
    }

    @SuppressLint("MissingPermission")
    fun connect(bluetoothAdapter: BluetoothAdapter, address: String): Boolean {
        try {
            bluetoothAdapter.cancelDiscovery()
        } catch (_: Exception) {}

        close()

        val device = bluetoothAdapter.getRemoteDevice(address)

        // Urutan 1: Insecure RFCOMM (Sangat stabil untuk HC-06 dan Android 11-14)
        try {
            val s = device.createInsecureRfcommSocketToServiceRecord(SPP_UUID)
            s.connect()
            socket = s
            connectedDeviceAddress = address
            Log.d("BT_HOLDER", "Berhasil terhubung via Insecure RFCOMM ke ${device.name ?: address}")
            return true
        } catch (e1: Exception) {
            Log.w("BT_HOLDER", "Insecure RFCOMM gagal (${e1.message}), mencoba Channel 1 Reflection...")
        }

        // Urutan 2: Reflection Direct Channel 1 (Bypass SDP query untuk modul HC-06)
        try {
            val method = device.javaClass.getMethod("createRfcommSocket", Int::class.javaPrimitiveType)
            val s = method.invoke(device, 1) as BluetoothSocket
            s.connect()
            socket = s
            connectedDeviceAddress = address
            Log.d("BT_HOLDER", "Berhasil terhubung via Channel 1 Reflection ke ${device.name ?: address}")
            return true
        } catch (e2: Exception) {
            Log.w("BT_HOLDER", "Reflection gagal (${e2.message}), mencoba Secure RFCOMM...")
        }

        // Urutan 3: Secure RFCOMM (Metode standar)
        try {
            val s = device.createRfcommSocketToServiceRecord(SPP_UUID)
            s.connect()
            socket = s
            connectedDeviceAddress = address
            Log.d("BT_HOLDER", "Berhasil terhubung via Secure RFCOMM ke ${device.name ?: address}")
            return true
        } catch (e3: Exception) {
            Log.e("BT_HOLDER", "Semua metode koneksi gagal: ${e3.message}")
            close()
            return false
        }
    }

    fun close() {
        try {
            socket?.close()
        } catch (_: Exception) {}
        socket = null
        connectedDeviceAddress = null
    }

    val isConnected: Boolean
        get() = socket?.isConnected == true
}
