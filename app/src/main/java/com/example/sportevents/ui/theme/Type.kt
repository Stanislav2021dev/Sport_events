package com.example.sportevents.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sportevents.R

private val openSansFontFamily = FontFamily(
    Font(R.font.open_sans_regular, FontWeight.Normal),
    Font(R.font.open_sans_semi_bold, FontWeight.SemiBold)
)

enum class SportEventsTextStyle { HeaderText, CommonText, ErrorText }

@Composable
@ReadOnlyComposable
fun textStyle(style: SportEventsTextStyle): TextStyle =
    when (style) {
        SportEventsTextStyle.HeaderText -> {
            TextStyle(
                fontFamily = openSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Colors.PrimaryTextColor,
                fontSize = 16.sp,
            )
        }
        SportEventsTextStyle.CommonText -> {
            TextStyle(
                fontFamily = openSansFontFamily,
                fontWeight = FontWeight.Normal,
                color = Colors.PrimaryTextColor,
                fontSize = 14.sp
            )
        }
        SportEventsTextStyle.ErrorText -> {
            TextStyle(
                fontFamily = openSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Colors.PrimaryTextColor,
                fontSize = 20.sp
            )
        }
    }