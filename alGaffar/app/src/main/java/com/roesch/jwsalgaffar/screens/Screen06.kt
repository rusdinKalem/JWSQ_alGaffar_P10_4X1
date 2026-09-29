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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.roesch.jwsalgaffar.components.DynamicSelectTextFieldSufix2
import com.roesch.jwsalgaffar.components.JudulInfo
import com.roesch.jwsalgaffar.components.TombolHome
import com.roesch.jwsalgaffar.components.TombolIcon
import com.roesch.jwsalgaffar.components.TombolKirim
import com.roesch.jwsalgaffar.components.listBilanganBulat
import com.roesch.jwsalgaffar.components.listBilanganBulatPositif
import java.io.OutputStream

@Composable
fun Screen06(
    navController: NavHostController,
    bluetoothSocket: BluetoothSocket
) {
    val bluetoothOutputStream: OutputStream = bluetoothSocket.outputStream
    val options1 : List<String> = listBilanganBulatPositif()

    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var input3 by remember { mutableStateOf("") }
    var input4 by remember { mutableStateOf("") }
    var input5 by remember { mutableStateOf("") }
    var input6 by remember { mutableStateOf("") }

    val datas = listOf(
        "NTT$input1",
        "NTH$input2",
        "NAD$input3",
        "NSO$input4",
        "NIN$input5",
        "NJM$input6",
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
                    judul = "DURASI WAKTU",
                    info = "Pilih Durasi waktu dari TARTIL/TARHIM/ADZAN/SHOLAT/INFO JUMAT/KHUTBAH Yang Diinginkan"
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
                        label = "TARTIL",
                        suffix = "MENIT",
                        onValueChangedEvent ={input1 = it}
                    )
                    DynamicSelectTextFieldSufix2(
                        selectedValue = input2,
                        options = options1,
                        label = "TARHIM",
                        suffix = "MENIT",
                        onValueChangedEvent ={input2 = it}
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    DynamicSelectTextFieldSufix2(
                        selectedValue = input3,
                        options = options1,
                        label = "ADZAN",
                        suffix = "MENIT",
                        onValueChangedEvent ={input3 = it}
                    )
                    DynamicSelectTextFieldSufix2(
                        selectedValue = input4,
                        options = options1,
                        label = "SHOLAT",
                        suffix = "MENIT",
                        onValueChangedEvent ={input4 = it}
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    DynamicSelectTextFieldSufix2(
                        selectedValue = input5,
                        options = options1,
                        label = "INFO",
                        suffix = "MENIT",
                        onValueChangedEvent ={input5 = it}
                    )
                    DynamicSelectTextFieldSufix2(
                        selectedValue = input6,
                        options = options1,
                        label = "KHUTBAH",
                        suffix = "MENIT",
                        onValueChangedEvent ={input6 = it}
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
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
            }
        }
    }
}