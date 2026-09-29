package com.roesch.jwsalgaffar.screens

import android.bluetooth.BluetoothSocket
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.roesch.jwsalgaffar.components.DynamicSelectTextField
import com.roesch.jwsalgaffar.components.DynamicSelectTextFieldSufix2
import com.roesch.jwsalgaffar.components.JudulInfo
import com.roesch.jwsalgaffar.components.TombolHome
import com.roesch.jwsalgaffar.components.TombolIcon
import com.roesch.jwsalgaffar.components.TombolKirim
import com.roesch.jwsalgaffar.components.listBilanganBulat
import com.roesch.jwsalgaffar.components.listZona
import java.io.OutputStream

@Composable
fun Screen02(
    navController: NavHostController,
    bluetoothSocket: BluetoothSocket,
    currentLocation: LatLng
) {
    val bluetoothOutputStream: OutputStream = bluetoothSocket.outputStream

    val options1 = listBilanganBulat()

    val options2 = listZona()
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var input3 by remember { mutableStateOf("") }
    var input4 by remember { mutableStateOf("") }
    var input5 by remember { mutableStateOf("") }

    val lat = currentLocation.latitude.toString()
    val long = currentLocation.longitude.toString()

    val zona = when(input5){
        "Waktu Indonesia Barat" ->  7
        "Waktu Indonesia Tengah" ->  8
        "Waktu Indonesia Timur" ->  9
        else -> ""
    }

    val datas = listOf(
        "NIH$input1",
        "NAL$input2",
        "NLA$lat",
        "NLO$long",
        "NTZ$zona"
    )
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ){
                JudulInfo(
                    judul = "IHTIYATI & KOORDINAT TEMPAT SHOLAT",
                    info = "Input Ihtiyati (Waktu Kehati-hatian/Toleransi Waktu), dan MDPL (Meter Di atas Permukaan Laut) serta Time Zone di Lokasi Tempat Ibadah dimaksud.\n"+
                            "Untuk Koordinat(Latitude/Longitude) akan diisi otomatis jika Layanan Lokasi pada HP diaktifkan"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    DynamicSelectTextFieldSufix2(
                        selectedValue = input1,
                        options = options1,
                        label = "IHTIYATI",
                        suffix = "MENIT",
                        onValueChangedEvent ={input1 = it}
                    )
                    OutlinedTextField(
                        value = input2,
                        onValueChange = { input2 = it },
                        label = { Text("MDPL")},
                        modifier = Modifier
                            .width(176.dp)
                    )


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {


                    OutlinedTextField(
                        value = lat,
                        onValueChange = { input3 = it },
                        label = { Text("LATITUDE") },
                        modifier = Modifier
                            .width(176.dp)
                    )

                    OutlinedTextField(
                        value = long,
                        onValueChange = { input4 = it },
                        label = { Text("LONGITUDE") },
                        modifier = Modifier
                            .width(176.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DynamicSelectTextField(
                        selectedValue = input5,
                        options = options2,
                        label = "ZONA WAKTU",
                        onValueChangedEvent ={input5 = it}
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    for(data in datas) {
                                        val dataQ =
                                            buildString {
                                                append(data)
                                                append("\n")
                                            }
                                        bluetoothOutputStream.write(dataQ.toByteArray())
                                    }
                                }
                            )
                    ) {
                        TombolKirim()
                    }
                    Spacer(Modifier.height(20.dp))
                    TombolHome(
                        "MENU UTAMA",
                        navController
                    )
                }

                Spacer(Modifier.height(190.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Created by @Roesch ",
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(20.dp))

            }
        }
    }
}