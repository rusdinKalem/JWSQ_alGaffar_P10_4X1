@file:Suppress("UNUSED_EXPRESSION")

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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.roesch.jwsalgaffar.components.DynamicSelectTextField
import com.roesch.jwsalgaffar.components.DynamicSelectTextFieldSufix2
import com.roesch.jwsalgaffar.components.JudulInfo
import com.roesch.jwsalgaffar.components.TextSwitch
import com.roesch.jwsalgaffar.components.TombolHome
import com.roesch.jwsalgaffar.components.TombolIcon
import com.roesch.jwsalgaffar.components.TombolKirim
import com.roesch.jwsalgaffar.components.listMode
import com.roesch.jwsalgaffar.components.listWaktuMalam
import com.roesch.jwsalgaffar.components.listWaktuPagi
import java.io.OutputStream

@Composable
fun Screen08(navController: NavHostController, bluetoothSocket: BluetoothSocket) {
    val bluetoothOutputStream: OutputStream = bluetoothSocket.outputStream

    val options1 = listMode()
    val options2 = listWaktuMalam()
    val options3 = listWaktuPagi()

    var checked1 by remember { mutableStateOf(true) }
    var checked2 by remember { mutableStateOf(true) }
    var checked3 by remember { mutableStateOf(true) }
    var checked4 by remember { mutableStateOf(true) }
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var sliderPosition2 by remember { mutableFloatStateOf(0f) }
    var theme by remember { mutableStateOf("") }
    var start by remember { mutableStateOf("") }
    var finish by remember { mutableStateOf("") }

    val mode = when(theme){
        "NORMAL" ->  1
        "JAM BESAR" ->  2
        "PADAM" ->  3
        else -> ""
    }


    val kali1 = 255
    val kali2 = 150
    val dataSlider1 = sliderPosition*kali1
    val dataSlider2 = sliderPosition*kali2

    val buzzer = when(checked1){
        true -> 1
        else -> 0
    }
    val imsak = when(checked2){
        true -> 1
        else -> 0
    }
    val terbit = when(checked3){
        true -> 1
        else -> 0
    }
    val dhuha = when(checked4){
        true -> 1
        else -> 0
    }
    val datas = listOf(
        "NBZ$buzzer",
        "NSI$imsak",
        "NST$terbit",
        "NSU$dhuha",
        "NBL$dataSlider1",
        "NRT$dataSlider2",
        "NHS$mode",
        "NHM$start",
        "NHH$finish",
    )


    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column (
            modifier = Modifier
                .fillMaxSize(),
        ){
            JudulInfo(
                judul = "TYPE & NAMA TEMPAT SHOLAT",
                info = "Pilih type tempat sholat, dengan pilihan Masjid/Musholla/Surau/Langgar. " +
                        "Dan input nama tempat sholat dimaksud"
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            )   {
                TextSwitch("SUARA BUZZER/PENGINGAT")

                androidx.compose.material3.Switch(
                    checked = checked1,
                    onCheckedChange = {
                        checked1 = it
                    },
                    thumbContent = if (checked1) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                )
                        }
                    } else {
                        null
                    }
                )

            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            )   {
                TextSwitch("TAMPILAN WAKTU IMSAK")

                androidx.compose.material3.Switch(
                    checked = checked2,
                    onCheckedChange = {
                        checked2 = it
                    },
                    thumbContent = if (checked2) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,

                                )
                        }
                    } else {
                        null
                    }
                )

            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            )   {
                TextSwitch("TAMPILAN WAKTU SURUK/TERBIT")

                androidx.compose.material3.Switch(
                    checked = checked3,
                    onCheckedChange = {
                        checked3 = it
                    },
                    thumbContent = if (checked3) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,

                                )
                        }
                    } else {
                        null
                    }
                )

            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            )   {
                TextSwitch("TAMPILAN WAKTU DHUHA")

                androidx.compose.material3.Switch(
                    checked = checked4,
                    onCheckedChange = {
                        checked4 = it
                    },
                    thumbContent = if (checked4) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,

                                )
                        }
                    } else {
                        null
                    }
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),

            ) {
                Column {
                    Text(text = "KECERAHAN LED = $dataSlider1", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                    androidx.compose.material3.Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it }
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    Text(text = "KECERAHAN LED = $dataSlider2", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                    androidx.compose.material3.Slider(
                        value = sliderPosition2,
                        onValueChange = { sliderPosition2 = it }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                DynamicSelectTextField(
                    selectedValue = theme,
                    options = options1,
                    label = "MODE MALAM",
                    onValueChangedEvent ={theme = it}
                )
            }
            if (theme !== "NORMAL"){

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    DynamicSelectTextFieldSufix2(
                        selectedValue = start,
                        options = options2,
                        label = "START",
                        suffix = "MALAM",
                        onValueChangedEvent ={start = it}
                    )
                    DynamicSelectTextFieldSufix2(
                        selectedValue = finish,
                        options = options3,
                        label = "FINISH",
                        suffix = "PAGI",
                        onValueChangedEvent ={finish = it}
                    )
                }
            } else {
                null
            }
            Spacer(modifier = Modifier.height(30.dp))
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

