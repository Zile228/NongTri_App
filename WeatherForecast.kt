package com.example.nngtr

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecast(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFE0F5F8))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            WeatherHeader()
            CurrentTemporature()
            WeatherDetail()
            Forecast()
        }
    }
}

@Composable
fun Forecast() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ){
        Box(
            modifier = Modifier
                .padding(start = 15.dp, top = 5.dp)
                .width(328.dp)
                .height(250.dp)
                .align(Alignment.TopStart)
                .background(
                    color = Color(0x33FF9D23),
                    shape = RoundedCornerShape(5.dp)
                )
        ){
            Text(
                text = "Dự báo trong 07 ngày tới",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFC1701B),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(start = 22.dp, top = 12.dp)
                    .align(Alignment.TopStart)
            )
            Image(
                painter = painterResource(R.drawable.weathergraph),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 9.dp, top = 35.dp)
                    .width(307.dp)
                    .height(203.dp)
                    .align(Alignment.TopStart)
            )
        }
    }
}

@Composable
fun WeatherDetail() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 15.dp, top = 5.dp)
                .width(328.dp)
                .height(90.dp)
                .align(Alignment.TopStart)
                .background(
                    color = Color(0x33FF9D23),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top
            ) {
                ShadowCircle {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 17.sp)) {
                                append("41\n")
                            }
                            withStyle(style = SpanStyle(fontSize = 12.sp)) {
                                append("%")
                            }
                        },
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF347A99),
                            textAlign = TextAlign.Center
                        )
                    )
                }
                ShadowCircle {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 17.sp)) {
                                append("4.8\n")
                            }
                            withStyle(style = SpanStyle(fontSize = 12.sp)) {
                                append("km/h")
                            }
                        },
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF347A99),
                            textAlign = TextAlign.Center
                        )
                    )
                }
                ShadowCircle {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 17.sp)) {
                                append("32\n")
                            }
                            withStyle(style = SpanStyle(fontSize = 12.sp)) {
                                append("mm")
                            }
                        },
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF347A99),
                            textAlign = TextAlign.Center
                        )
                    )
                }
                ShadowCircle {
                    Text(
                        text = "32",
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF347A99),
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 65.dp, start = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top
            ){
                Text(
                    text = "Độ ẩm",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF347A99),
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = "Gió",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF347A99),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(start = 33.dp)
                )
                Text(
                    text = "Lượng mưa",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF347A99),
                        textAlign = TextAlign.Center
                    ) ,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = "Chỉ số UV",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF347A99),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Composable
fun ShadowCircle(
    circleSize: Dp = (51.38).dp, // Bạn có thể điều chỉnh kích thước tùy ý
    circleColor: Color = Color(0xFFD9D9D9),
    shadowColor: Color = Color.Black.copy(alpha = 0.25f),
    shadowBlur: Dp = 4.dp,
    shadowOffsetY: Dp = 4.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.size(circleSize)) {
        // Vẽ drop shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = shadowOffsetY)
                .blur(shadowBlur)
                .background(color = shadowColor, shape = CircleShape)
        )
        // Vẽ hình tròn chính và đặt nội dung ở giữa
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(color = circleColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}


@Composable
fun CurrentTemporature() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ){
        Text(
            text = "Dự báo thời tiết Trường Xuân, Đăk Song, Đăk Nông",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFFC66E14)
            ),
            modifier = Modifier
                .padding(start = 15.dp, top = 29.dp)
                .width(328.dp)
                .align(Alignment.TopStart)
        )
        Box(
            modifier = Modifier
                .padding(start = 15.dp, top = 74.dp)
                .width(328.dp)
                .height(70.dp)
                .align(Alignment.TopStart)
                .background(
                    color = Color(0x33FF9D23),
                    shape = RoundedCornerShape(size = 5.dp)
                )
        ){
            Image(
                painter = painterResource(R.drawable.sunnyweather),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(70.dp)
                    .height(70.dp)
            )
            Text(
                text = "27°C",
                style = TextStyle(
                    fontSize = 27.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFEB8317)
                ),
                modifier = Modifier
                    .padding(start = 85.dp, top = 18.dp)
                    .align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(R.drawable.greenline),
                contentDescription = "",
                tint = Color(0xFFC66E14),
                modifier = Modifier
                    .padding(start = 163.dp, 20.dp)
                    .height(30.dp)
                    .width(3.dp)
                    .align(Alignment.TopStart)
            )
            Text(
                text = "Có mây cụm\n" +
                        "Cảm giác như 29°C",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFEB8317)
                ),
                modifier = Modifier
                    .padding(start = 170.dp, top = 17.dp)
                    .align(Alignment.TopStart)
            )
        }
    }
}

@Composable
fun WeatherHeader() {
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
            painter = painterResource(id = R.drawable.weatherheadericon),
            contentDescription = "Header icon of Weather Forecast screen",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 80.dp, top = 54.dp)
                .width(60.dp)
                .height(46.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = "Dự báo thời tiết",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF)
            ),
            modifier = Modifier
                .padding(start = 140.dp, top = 66.dp)
                .fillMaxWidth()
                .height(44.dp)
                .align(Alignment.TopStart)
        )
    }
}