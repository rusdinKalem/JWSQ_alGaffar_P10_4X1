package com.roesch.jwsalgaffar.screens

import android.bluetooth.BluetoothSocket
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.roesch.jwsalgaffar.components.JudulInfo
import com.roesch.jwsalgaffar.components.TombolHome
import com.roesch.jwsalgaffar.components.TombolIcon
import com.roesch.jwsalgaffar.components.TombolKirim
import java.io.OutputStream

@Composable
fun Screen07(
    navController: NavHostController,
    bluetoothSocket: BluetoothSocket,
    scrollState: ScrollState
) {
    val bluetoothOutputStream: OutputStream = bluetoothSocket.outputStream
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var input3 by remember { mutableStateOf("") }
    var input4 by remember { mutableStateOf("") }
    var input5 by remember { mutableStateOf("") }

    val datas = listOf(
        "CN1$input1",
        "CN2$input2",
        "CN3$input3",
        "CSM$input4",
        "CJM$input5"
    )
    val maxChar = 150
    val maxPesan = 75
    val jmlh1= input1.count()
    val jmlh2= input2.count()
    val jmlh3= input3.count()
    val jmlh4= input4.count()
    val jmlh5= input5.count()
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ){
            JudulInfo(
                judul = "PENGINPUTAN RUNNING TEXT",
                info = "Input Kalimat Yang akan ditampilkan dalam Running Text"
            )

            Column(
                Modifier
                    .verticalScroll(scrollState)
            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
                )   {

                        OutlinedTextField(
                            value = input1,
                            onValueChange = {
                                if(it.length <= maxChar) {
                                    input1 = it
                                }
                                            },
                            label = { Text("RUNTEXT1") },
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth(0.93f)
                        )
                    }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end=20.dp),
                text = "$jmlh1/$maxChar",
                fontSize = 16.sp,
                textAlign = TextAlign.End
                )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            )   {

                OutlinedTextField(
                    value = input2,
                    onValueChange = {
                        if(it.length <= maxChar) {
                            input2 = it
                        }
                    },
                    label = { Text("RUNTEXT2") },
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(0.93f)
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end=20.dp),
                text = "$jmlh2/$maxChar",
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            )   {

                OutlinedTextField(
                    value = input3,
                    onValueChange = {
                        if(it.length <= maxChar) {
                            input3 = it
                        }
                    },
                    label = { Text("RUNTEXT3") },
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(0.93f)
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end=20.dp),
                text = "$jmlh3/$maxChar",
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            )   {

                OutlinedTextField(
                    value = input4,
                    onValueChange = {
                        if(it.length <= maxChar) {
                            input4 = it
                        }
                    },
                    label = { Text("PRA-SHOLAT") },
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(0.93f)
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end=20.dp),
                text = "$jmlh4/$maxPesan",
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                )   {

                    OutlinedTextField(
                        value = input5,
                        onValueChange = {
                            if(it.length <= maxChar) {
                                input5 = it
                            }
                        },
                        label = { Text("PRA-KHUTBAH") },
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth(0.93f)
                    )
                }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end=20.dp),
                text = "$jmlh5/$maxPesan",
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )

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

            Spacer(Modifier.height(220.dp))

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

