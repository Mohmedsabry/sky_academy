package com.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.centery.ui.R
import ir.kaaveh.sdpcompose.ssp
import kotlin.math.roundToInt

val Tajawal = FontFamily(
    Font(R.font.tajawal_regular, FontWeight.Normal),
    Font(R.font.tajawal_bold, FontWeight.Bold),
    Font(R.font.tajawal_medium, FontWeight.Medium),
)
val Montserrat = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_extralight, FontWeight.ExtraLight)
)

// Set of Material typography styles to start with
@Composable
fun TextUnit.toSsp() = this.value.roundToInt().ssp

@Composable
fun getEnglishFrenchTypography(): Typography {
    val scaling = LocalDensity.current.fontScale.coerceIn(1f, 1.2f)
    return Typography(
        //Header/H 1
        headlineLarge = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp.toSsp().times(scaling),
            lineHeight = 15.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp()
        ),
        //Paragraph/Pa 1
        headlineMedium = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp.toSsp().times(scaling),
            lineHeight = 12.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp()
        ),
        //Paragraph/Pa 3
        bodyLarge = TextStyle( // main body
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp.toSsp().times(scaling),
            lineHeight = 12.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp()
        ),
        bodyMedium = TextStyle( // medium body
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp.toSsp().times(scaling),
            lineHeight = 11.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp()
        ),
        bodySmall = TextStyle( // small body
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp.toSsp().times(scaling),
            lineHeight = 10.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp()
        )
    )
}

@Composable
fun getArabicTypography(): Typography {
    val scaling = LocalDensity.current.fontScale.coerceIn(1f, 1.3f)
    return Typography(
        //Header/H 1
        headlineLarge = TextStyle(
            fontFamily = Tajawal,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp.toSsp().times(scaling),
            lineHeight = 16.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp(),
            platformStyle = PlatformTextStyle(
                includeFontPadding = true
            ),
        ),
        //Paragraph/Pa 1
        headlineMedium = TextStyle(
            fontFamily = Tajawal,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp.toSsp().times(scaling),
            lineHeight = 15.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp(),
            platformStyle = PlatformTextStyle(
                includeFontPadding = true
            ),
        ),
        //Paragraph/Pa 3
        bodyLarge = TextStyle(
            // main body
            fontFamily = Tajawal,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp.toSsp().times(scaling),
            lineHeight = 14.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp(),
            platformStyle = PlatformTextStyle(
                includeFontPadding = true
            ),
        ),
        bodyMedium = TextStyle(
            // small body
            fontFamily = Tajawal,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp.toSsp().times(scaling),
            lineHeight = 12.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp(),
            platformStyle = PlatformTextStyle(
                includeFontPadding = true
            ),
        ),
        bodySmall = TextStyle(
            // small body
            fontFamily = Tajawal,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp.toSsp().times(scaling),
            lineHeight = 11.sp.toSsp().times(scaling),
            letterSpacing = 0.sp.toSsp(),
            platformStyle = PlatformTextStyle(
                includeFontPadding = true
            ),
        )
    )
}

val Typography = Typography(
    //old font
    bodyLarge = TextStyle(
        fontFamily = FontFamily(
            Font(
                R.font.cairo_regular,
            ),
            Font(
                R.font.cairo_bold,
            ),
            Font(
                R.font.cairo_medium,
            ),
            Font(
                R.font.cairo_black,
            ),
            Font(
                R.font.cairo_light,
            ),
            Font(
                R.font.cairo_extra_bold,
            ),
            Font(
                R.font.cairo_extra_light,
            ),
            Font(
                R.font.cairo_semi_bold,
            ),
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

var newTypography: Typography = Typography

@Composable
fun ChangeTypography(type: Typography = getEnglishFrenchTypography()) {
    newTypography = type
}
