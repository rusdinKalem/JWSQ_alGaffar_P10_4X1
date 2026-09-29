package com.roesch.jwsalgaffar.screens

import android.annotation.SuppressLint
import android.bluetooth.BluetoothSocket
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.roesch.jwsalgaffar.components.JudulInfo
import com.roesch.jwsalgaffar.components.Picker
import com.roesch.jwsalgaffar.components.TombolHome
import com.roesch.jwsalgaffar.components.TombolIcon
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@SuppressLint("SimpleDateFormat")
@Composable
fun Screen03(navController: NavHostController, bluetoothSocket: BluetoothSocket) {
    val bluetoothOutputStream: OutputStream = bluetoothSocket.outputStream

    val calender = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("ddMMyyHHmmss", Locale.getDefault()).format(calender)
    val dayFormat = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column {

                JudulInfo(
                    judul = "WAKTU DAN TANGGAL",
                    info = "Tekan tombol Refresh untuk menginput Tanggal dan Waktu yang sesuai dengan HP/Device \n" +
                            "Dan tekan tombol Edit Tanggal serta Edit Waktu jika pengaturan secara Manual"
                )

                Column (
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                )   {
                    Text(
                        text = "PENGATURAN OTOMATIS", fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp, modifier = Modifier.padding(horizontal = 15.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .clickable(
                                onClick = {bluetoothOutputStream.write("SDT$dateFormat$dayFormat".toByteArray())}
                            )
                            .padding(horizontal = 10.dp)
                            .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xff1b1b1b)
                            )
                            .padding(horizontal = 5.dp)
                            .height(150.dp)
                            .width(150.dp)
                        ,
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.Refresh,
                            contentDescription = "Date",
                            Modifier.size(100.dp)
                        )
                        Text(
                            text = "REFRESH", fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp, modifier = Modifier.padding(horizontal = 15.dp))
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "PENGATURAN MANUAL", fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp, modifier = Modifier.padding(horizontal = 15.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Picker(bluetoothSocket)

                    Spacer(modifier = Modifier.height(50.dp))

                    TombolHome(
                        "MENU UTAMA", navController)
                }
            }

        }
    }
}