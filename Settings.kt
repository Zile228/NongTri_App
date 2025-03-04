package com.example.nngtr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Settings(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFE0F5F8))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            SettingsHeader()
            SettingOptions()
        }
    }
}

@Composable
fun SettingOptions() {
    Box(
        modifier = Modifier
            .padding(start = 22.dp, top = 30.dp)
            .width(320.dp)
            .height(115.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(28.dp)
                    .background(
                        color = Color(0x7AEDD7B4),
                        shape = RoundedCornerShape(5.dp)
                    )
            ){
                Text(
                    text = "Thông tin về chúng tôi",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.montserratitalic)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000)
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(28.dp)
                    .background(
                        color = Color(0x7AEDD7B4),
                        shape = RoundedCornerShape(5.dp)
                    )
            ){
                Text(
                    text = "Các câu hỏi thường gặp (FAQs)",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.montserratitalic)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000)
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun SettingsHeader() {
    Box(
        modifier = Modifier
            .padding(0.dp)
            .width(393.dp)
            .height(100.dp)
    ) {
        Box(
            modifier = Modifier
                .width(393.dp)
                .height(100.dp)
                .background(color = Color(0xFFFF9D23))
        )
        Image(
            painter = painterResource(id = R.drawable.settingheader),
            contentDescription = "Header icon of Setting screen",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 135.dp, top = 57.dp)
                .width(39.dp)
                .height(39.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = "Cài đặt",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .padding(start = 184.dp, top = 66.dp)
                .fillMaxWidth()
                .height(44.dp)
                .align(Alignment.TopStart)
        )
    }
}