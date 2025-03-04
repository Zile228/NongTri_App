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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun PersonalInfo(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFE0F5F8))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            PersonalHeader()
            AvatarInfo()
            PersonalFeatures()
        }
    }
}

@Composable
fun PersonalFeatures() {
    // Thay đổi Box ngoài cùng: dùng fillMaxWidth() để thích ứng với các màn hình khác nhau
    Box(
        modifier = Modifier
            .padding(22.dp)
            .fillMaxWidth() // Đã thay thế width(345.dp) để chiếm toàn bộ chiều rộng thiết bị
        // .height(115.dp) // Đã loại bỏ height cố định để chiều cao do nội dung tự quyết định
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), // Sử dụng fillMaxWidth() để chiều rộng cố định, chiều cao tự điều chỉnh
            verticalArrangement = Arrangement.spacedBy(8.dp) // Sử dụng khoảng cách cố định giữa các phần tử
        ) {
            // Sử dụng composable FeatureBox cho các chức năng, tránh lặp code
            FeatureBox(
                text = "Thay đổi thông tin cá nhân",
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.montserratitalic)),
                    fontWeight = FontWeight.Normal, // Đã chuyển từ giá trị số sang enum
                    color = Color(0xFF000000)
                )
            )
            FeatureBox(
                text = "Nâng cấp tài khoản VIP",
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.montserratitalic)),
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000)
                )
            )
            FeatureBox(
                text = "Xóa tài khoản",
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.montserratitalic)),
                    fontWeight = FontWeight.ExtraBold, // Dùng ExtraBold thay cho 900
                    color = Color(0xFFC10000)
                )
            )
        }
    }
}

// Composable tái sử dụng cho từng Feature Box
@Composable
fun FeatureBox(text: String, textStyle: TextStyle) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
            .background(
                color = Color(0x7AEDD7B4),
                shape = RoundedCornerShape(5.dp)
            ),
        contentAlignment = Alignment.Center // Dùng contentAlignment để căn giữa nội dung trong Box
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}


@Composable
fun AvatarInfo() {
    Box(
        modifier = Modifier
            .padding(start = 80.dp, top = 60.dp)
            .width(250.dp)
            .height(155.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.useravt),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(84.dp)
                .clip(CircleShape)
                .align(Alignment.TopCenter)
                .offset(x = (-15).dp)
        )
        Text(
            text = "Nguyễn Văn A",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000)
            ),
            modifier = Modifier
                .padding(start = 47.dp, top = 91.dp)
                .align(Alignment.TopStart)
        )
        Icon(
            painter = painterResource(R.drawable.location),
            contentDescription = "User Location",
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(top = 115.dp)
                .size(width = 15.dp, height = 20.dp)
        )
        Icon(
            painter = painterResource(R.drawable.tree),
            contentDescription = "User Selected Tree",
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(top = 135.dp)
                .size(width = 15.dp, height = 20.dp)
        )
        Text(
            text = "Trường Xuân, Đăk Song, Đăk Nông",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.montserratitalic)),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000)
            ),
            modifier = Modifier
                .padding(start = 20.dp, top = 118.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = "Cà phê, tiêu, điều",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.montserratitalic)),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000)
            ),
            modifier = Modifier
                .padding(start = 18.dp, top = 138.dp)
                .align(Alignment.TopStart)
        )

        // Box hình chữ nhật có góc bo tròn 3.dp, nền màu cam (#FF9D23)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 128.dp, top = 7.dp)
                .background(color = Color(0xFFFF9D23), shape = RoundedCornerShape(3.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp) // padding nội dung để căn giữa chữ "TC"
        ) {
            Text(
                text = "TC",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight.W700,
                    color = Color.White
                )
            )
        }

        // Box hình tròn cỡ 20.dp, nền màu cam (#FF9D23)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 136.dp, top = 64.dp)
                .size(20.dp)
                .background(color = Color(0xFFFF9D23), shape = CircleShape)
        ) {
            Text(
                text = "+",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight.W700,
                    color = Color.White
                )
            )
        }
    }
}


@Composable
fun PersonalHeader() {
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
            painter = painterResource(id = R.drawable.chatboticon),
            contentDescription = "Header icon of Personal Info screen",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 86.dp, top = 54.dp)
                .width(54.dp)
                .height(46.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = "Hồ sơ người dùng",
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