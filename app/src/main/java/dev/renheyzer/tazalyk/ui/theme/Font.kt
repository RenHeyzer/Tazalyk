package dev.renheyzer.tazalyk.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import dev.renheyzer.tazalyk.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val montserrat = GoogleFont("Oswald")

val montserratFamily = FontFamily(
    /*
    Font(
        googleFont = montserrat,
        fontProvider = provider,
        weight = FontWeight.Normal
    ),
    Font(
        googleFont = montserrat,
        fontProvider = provider,
        weight = FontWeight.Medium
    ),
    Font(
        googleFont = montserrat,
        fontProvider = provider,
        weight = FontWeight.SemiBold
    ),
    Font(
        googleFont = montserrat,
        fontProvider = provider,
        weight = FontWeight.Bold
    ),
    */
    Font(resId = R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(resId = R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(resId = R.font.montserrat_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.montserrat_bold, weight = FontWeight.Bold),
)

