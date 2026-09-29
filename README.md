# JWS Baabul Gaffar (4x1 & 2x1 P10 LED Matrix) + alGaffar Android App

Sistem Jadwal Waktu Sholat (JWS) berbasis Arduino Mega 2560 / ATmega dengan panel LED Matrix P10 (mendukung panel 4x1 dan 2x1) serta modul audio DFPlayer Mini untuk pemutar MP3 Tartil & Tarhim otomatis sebelum adzan, dikontrol secara nirkabel melalui aplikasi Android **alGaffar** via Bluetooth (HC-05, HC-06, atau ESP32).

---

## 🕌 Fitur Utama

### 1. Arduino Firmware (P10 LED Matrix)
- **Tampilan LED Matrix P10**: Mendukung konfigurasi 4x1 panel (128x16 px) dan kompatibel dengan 2x1 panel (64x16 px).
- **Perhitungan Waktu Sholat Otomatis**: Berdasarkan koordinat lintang (*latitude*), bujur (*longitude*), dan zona waktu (GMT+7, GMT+8, GMT+9).
- **Jadwal Lengkap**: Subuh, Terbit, Dhuha, Dzuhur, Ashar, Maghrib, Isya, dan Imsak.
- **Audio DFPlayer Mini**:
  - Auto-play Tartil Al-Qur'an sebelum masuk waktu sholat.
  - Auto-play Tarhim / Sholawat menjelang adzan.
  - Dilengkapi switch ON/OFF individual untuk setiap waktu sholat.
- **Hitung Mundur Iqomah & Alarm**:
  - Hitung mundur iqomah yang dapat diatur per waktu sholat.
  - Bunyi buzzer saat masuk waktu sholat dan saat hitung mundur iqomah selesai.
- **Running Text Informasi**:
  - Nama Masjid dan Alamat Masjid (`CMA`).
  - Berbagai pesan informasi / pengumuman berjalan.
  - Jam digital, tanggal masehi, dan kalender hijriyah.
- **Konektivitas Bluetooth**: Komunikasi Serial UART (Serial1 pin 18 TX1, pin 19 RX1 pada Mega) untuk konfigurasi melalui ponsel Android.

### 2. Aplikasi Android alGaffar
- **Platform**: Native Android dengan Kotlin & Jetpack Compose.
- **Konektivitas Bluetooth SPP**:
  - Multi-tier fallback connection: Insecure RFCOMM, reflection standard port channel 1, and secure RFCOMM.
  - Kompatibel dengan modul HC-05, HC-06, dan ESP32 Bluetooth Classic tanpa putus mendadak (*connection drop fix*).
- **Desain UI/UX Modern**:
  - Tema Islamic Emerald & Gold bernuansa elegan.
  - Wallpaper background bertekstur islami dan kartu glassmorphism.
- **Menu Konfigurasi Lengkap**:
  - **Masjid**: Pengaturan Nama Masjid dan Alamat Masjid (`CMA`).
  - **Waktu**: Sinkronisasi waktu dan tanggal HP ke RTC DS3231 secara presisi.
  - **Koreksi Waktu**: Penyesuaian menit untuk masing-masing waktu sholat.
  - **Iqomah**: Durasi jeda hitung mundur iqomah per waktu sholat.
  - **Tampilan**: Pengaturan tingkat kecerahan panel LED P10.
  - **Informasi / Pesan**: Teks berjalan (*running text*) informasi masjid.
  - **Pengumuman**: Pesan pengingat dan pengumuman khusus.
  - **Lokasi**: Pengaturan koordinat GPS (Latitude, Longitude) dan Timezone.
  - **MP3 Tartil**: Kontrol pemutar murottal, volume suara, durasi tartil, dan pemilihan surat.

---

## 📁 Struktur Repositori

```text
├── JWS_BaabulGaffar_4X1.ino    # Sketch utama Arduino (Setup, Loop, Display state machine)
├── WS_CalculateTime.ino        # Algoritma perhitungan astronomi waktu sholat
├── WS_Drawing.ino              # Fungsi rendering grafis DMD & teks LED P10
├── WS_LoadPrm.ino              # Parsing protokol data serial Bluetooth & EEPROM handler
├── WS_MP3.ino                  # Handler DFPlayer Mini (Tartil & Tarhim)
├── WS_NameIdx.ino              # Definisi index dan nama-nama waktu sholat
├── alGaffar/                   # Source code lengkap aplikasi Android (Kotlin + Jetpack Compose)
│   ├── app/
│   │   └── src/main/java/com/roesch/jwsalgaffar/
│   │       ├── MainActivity.kt
│   │       ├── HomeActivity.kt
│   │       ├── screens/        # Screen01 - Screen09 & ScreenMp3
│   │       ├── utils/          # BluetoothHandler, BluetoothConnectionHolder, dll.
│   │       └── ui/theme/       # Emerald & Gold theme palette
│   ├── build.gradle.kts
│   └── settings.gradle.kts
├── .gitignore
└── README.md
```

---

## 🛠️ Panduan Penggunaan

### Hardware Arduino:
- Board: Arduino Mega 2560 (atau mikrokontroler kompatibel dengan minimal 2 hardware UART).
- Modul RTC: DS3231 (I2C: SDA pin 20, SCL pin 21).
- Modul Bluetooth: HC-05 / HC-06 (Serial1: RX1 pin 19, TX1 pin 18).
- Modul MP3: DFPlayer Mini (Serial2: RX2 pin 17, TX2 pin 16).
- Panel Display: P10 LED Matrix (koneksi via DMD / DMD3 shield).

### Aplikasi Android:
1. Buka folder `alGaffar` di **Android Studio Ladybug / Koala / Iguana**.
2. Sync Gradle dependencies.
3. Build dan jalankan aplikasi pada smartphone Android (mendukung Android 8.0 hingga Android 14+).
4. Nyalakan Bluetooth, lakukan pairing dengan `HC-05` / `HC-06` (PIN default `1234`), lalu buka aplikasi dan hubungkan.

---

## 👤 Author
- **Rusdin** ([@rusdinKalem](https://github.com/rusdinKalem))
