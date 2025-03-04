package com.example.nngtr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@Composable
fun HomeScreen(modifier: Modifier = Modifier, openChat: () -> Unit, openWeather: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFE0F5F8)) // Màu nền toàn màn hình
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item { TopBannerCompose() }
            item { MainFeature(openChat, openWeather) }
            item { DailyCropPrice() }
            item { PopularNews() }
            item { NewsList() }
        }
    }
}

@Composable
fun NewsList() {
    val context = LocalContext.current
    val db = NewsDB.getDatabase(context)  // Lấy instance duy nhất của database
    val newsDao = db.newsDAO()

    val newsList = remember { mutableStateOf<List<NewsItem>>(emptyList()) }

    LaunchedEffect(Unit) {
        val list = withContext(Dispatchers.IO) {
            if (newsDao.getAll().isEmpty()) {
                newsDao.insert(
                    NewsItem(
                        title = "Tiểu thương bán gạo giá rẻ ven đường ở miền Tây",
                        content = "Một số tiểu thương ở Đồng Tháp, Bến Tre, Tiền Giang... dùng xe tải chở gạo, rao bán giá rẻ dọc các tuyến đường nông thôn, người dân đổ xô tới mua về dự trữ.\n" +
                                "Hơn một tháng nay, bà Nguyễn Thị Lan, một thương lái ở thị xã Cai Lậy, tỉnh Tiền Giang đều đặn đem gạo tới bán tại nhiều tuyến đường, chợ ở Tiền Giang, Đồng Tháp.\n" +
                                "\"Mỗi ngày vợ chồng tôi bán hết một xe gạo, có hôm chỉ một tiếng đồng hồ là hết sạch. Đến nay tôi đã bán vài trăm tấn gạo rồi\", bà Lan cho biết. Mỗi kg bà bán 11.800-15.000 đồng, tùy loại.\n" +
                                "Số gạo này được bà xay từ lúa mua của nông dân trước Tết. Thông thường gạo xay xong, bà trữ tại nhà máy, chờ doanh nghiệp đến mua. Năm nay giá giảm mạnh, doanh nghiệp thu mua nhỏ giọt, bà Lan quyết định chở xe bán dạo với hy vọng sớm thu hồi vốn.\n" +
                                "\"Bán mức này cũng chưa có lời đâu, nhưng để trong kho giá xuống nữa thì lỗ thêm\", bà giải thích.\n",
                        imageResId = "demonews1",
                        createdAt = "17/2/2025"
                    ),
                    NewsItem(
                        title = "Xuất khẩu dừa lần đầu vượt tỷ USD",
                        content = "Xuất khẩu dừa Việt Nam vượt 1 tỷ USD trong năm 2024, nhờ mở rộng thị trường và xuất chính ngạch sang Trung Quốc.\n" +
                                "Theo số liệu của cơ quan hải quan, tính đến cuối năm ngoái dừa tươi xuất khẩu đạt 390 triệu USD, tăng 61% so với cùng kỳ.\n" +
                                "Tính chung các sản phẩm từ dừa, xuất khẩu mặt hàng này đạt gần 1,1 tỷ USD, tăng trên 20% so với năm 2023. Đây là lần đầu sau 14 năm, trái dừa đem lại kim ngạch tỷ USD cho Việt Nam.\n" +
                                "Theo Bộ Nông nghiệp & Phát triển nông thôn, cả nước hiện có 200.000 ha dừa, sản lượng 2 triệu tấn một năm. Một phần ba diện tích đạt chuẩn hữu cơ theo tiêu chuẩn của Mỹ và châu Âu, chủ yếu tại miền Trung và đồng bằng sông Cửu Long. Trái dừa xiêm Bến Tre đã được cấp chỉ dẫn địa lý, với 133 mã số vùng trồng và hơn 8.300 ha phục vụ xuất khẩu.\n" +
                                "Với hơn 600 doanh nghiệp sản xuất, chế biến, ngành dừa Việt Nam có lợi thế cạnh tranh trên thị trường quốc tế. Việt Nam đứng thứ 4 về xuất khẩu dừa tại châu Á - Thái Bình Dương và thứ 5 thế giới.\n",
                        imageResId = "demonews2",
                        createdAt = "17/2/2025"
                    ),
                    NewsItem(
                        title = "Nông dân trồng cà phê, sầu riêng thu nhập tiền tỷ",
                        content = "Năng suất cao, giá lập đỉnh giúp người trồng sầu riêng, cà phê năm 2024 lãi hàng trăm triệu đến vài tỷ đồng.\n" +
                                "Tại Kon Tum, anh Thành, một nông dân gắn bó nhiều năm với cây cà phê, cho biết năm ngoái mức giá cà phê tươi tại ruộng đạt 30.000 đồng một kg, cao gấp gần ba lần năm trước. Với sản lượng 27 tấn một ha, gia đình anh thu về hơn 800 triệu đồng từ một vụ cà phê (đã trừ các chi phí).\n" +
                                "Các tỉnh ở Tây Nguyên như Gia Lai, Đăk Lăk cũng có những mùa bội thu khi nhiều hộ gia đình đạt thu nhập hàng tỷ đồng nhờ giá cà phê tăng cao và sản lượng ổn định.\n" +
                                "Một số nông dân còn quyết định giữ lại sản phẩm, chờ giá tăng thêm. Chính quyền địa phương và các hợp tác xã tại những tỉnh này đã tích cực hỗ trợ nông dân trong việc bảo quản nông sản và tìm kiếm đầu ra, nhờ đó giá trị cà phê được nâng cao đáng kể.\n",
                        imageResId = "demonews3",
                        createdAt = "1/2/2025"
                    )
                )
            }
            newsDao.getAll()
        }
        newsList.value = list
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE0F5F8))
    ) {
        newsList.value.forEach { newsItem ->
            News(newsItem)
        }
    }
}


@Composable
fun News(newsItem: NewsItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(1.dp, Color(0xFFE0F5F8), RoundedCornerShape(8.dp))
            .background(Color(0xFFE0F5F8))
            .padding(9.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(
                    id = LocalContext.current.resources.getIdentifier(
                        newsItem.imageResId, "drawable", LocalContext.current.packageName
                    )
                ),
                contentDescription = "News Image",
                modifier = Modifier
                    .size(94.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFFE0F5F8)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(5.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = newsItem.title,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = newsItem.content,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    maxLines = 5,
                    lineHeight = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
            }
        }
    }
}



@Composable
fun TopBannerCompose(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(356.dp)
        .background(color = Color(0xFFF4F6FC))
    ){
        Image(
            painterResource(id = R.drawable.homescreentopbanner),
            contentDescription = "Home screen Top Banner",
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(393.dp)
                .height(699.dp)
                .padding(top = 50.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.greenline),
            contentDescription = "Overlay slogan",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 40.dp, top = 120.dp)
                .width(3.dp)
                .height(50.dp)
                .align(Alignment.TopStart)
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF809D3C), fontWeight = FontWeight.Bold)) {
                    append("Giải pháp công nghệ\ncho")
                }
                withStyle(style = SpanStyle(color = Color(0xFF355F2E), fontWeight = FontWeight.Black)) {
                    append(" NÔNG NGHIỆP\nVIỆT NAM!")
                }
            },
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight.Normal,  // Font weight cho đoạn đầu
                color = Color.Black // Màu mặc định cho phần còn lại
            ),
            modifier = Modifier
                .padding(start = 50.dp, top = 115.dp)
                .align(Alignment.TopStart)
        )
        Image(
            painter = painterResource(id = R.drawable.weather0210241),
            contentDescription = "weather icon",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 35.dp, top = 57.dp)
                .width(53.dp)
                .height(53.dp)
                .align(Alignment.TopStart)
        )
        TemperatureScreen()
    }
}

@Composable
fun MainFeature(openChat: () -> Unit, openWeather: () -> Unit) {
    Box(modifier = Modifier, contentAlignment = Alignment.TopCenter){
        Box(
            modifier = Modifier
                .fillMaxWidth() // Định kích thước trước
                .height(116.dp)
                .padding(horizontal = 20.dp)
                .border(width = 1.dp, color = Color(0x8078A978), shape = RoundedCornerShape(size = 10.dp)) // Border áp dụng lên kích thước đã set
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp)) // Background nằm trong border
        )
        Box (
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .height(27.dp)
                .offset(y = (-20).dp)
                .background(color = Color(0xFFEB8317), shape = RoundedCornerShape(size = 3.dp))
        )
        Text(
            text = "Đi cùng Nông Trí",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                letterSpacing = 1.8.sp,
            ),
            modifier = Modifier.offset(y = (-18).dp)
        )
        Image(
            painter = painterResource(id = R.drawable.chaticon),
            contentDescription = "Touch to chat",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 45.dp, top = 20.dp)
                .width(50.dp)
                .height(50.dp)
                .align(Alignment.TopStart)
                .clickable { openChat() }
        )
        Image(
            painter = painterResource(id = R.drawable.weathericon),
            contentDescription = "Touch to see weather",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 130.dp, top = 20.dp)
                .width(50.dp)
                .height(50.dp)
                .align(Alignment.TopStart)
                .clickable { openWeather() }
        )
        Text(
            text = "Chat\nngay",
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFF355F2E),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(start = 55.dp, top = 70.dp)
                .align(Alignment.TopStart)
                .clickable { openChat() }
        )
        Text(
            text = "Dự báo\nthời tiết",
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFF355F2E),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(start = 135.dp, top = 70.dp)
                .align(Alignment.TopStart)
        )

    }
}


@Composable
fun DailyCropPrice() {
    Box(modifier = Modifier
        .offset(y = 8.dp)
        .width(393.dp)
        .height(173.dp),
        contentAlignment = Alignment.TopCenter){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(27.dp)
            .padding(horizontal = 20.dp)
            .background(color = Color(0xFFEB8317), shape = RoundedCornerShape(size = 3.dp))
        )
        Text(
            text = "Giá nông sản trong ngày",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                letterSpacing = 1.8.sp,
            ),
            modifier = Modifier.offset(y = 2.dp)
        )
        Box(modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .width(160.dp)
            .align(Alignment.TopStart)
        ){
            Icon(
                painter = painterResource(id = R.drawable.greenline),
                modifier = Modifier
                    .padding(start = 20.dp, top = 33.dp)
                    .width(3.dp)
                    .height(41.dp)
                    .align(Alignment.TopStart),
                contentDescription = "",
                tint = Color(0xFFDCA713)
            )
            Text(
                text = "Cà phê",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF5D8736),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .padding(start = 28.dp, top = 26.dp)
                    .align(Alignment.TopStart)
            )
            Box(modifier = Modifier
                .padding(top = 30.dp)
                .width(40.dp)
                .height(16.dp)
                .align(Alignment.TopEnd)
                .background(color = Color(0xFF355F2E), shape = RoundedCornerShape(size = 3.dp))
            )
            {
                Icon(
                    painter = painterResource(R.drawable.bleach),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .padding(top = 4.dp, start = 3.dp)
                        .align(Alignment.TopStart)
                )
                Text(
                    text = "2.5%",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(start = 13.dp, top = (1.5).dp)
                        .align(Alignment.TopStart)
                )
            }
            Text(
                text = "115,300", // Để cứng tạm rồi thay sau khi tích hợp API
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF355F2E),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .padding(start = 28.dp, top = 54.dp)
                    .align(Alignment.TopStart)
            )
            Text(
                text = "₫/kg", // Để cứng tạm rồi thay sau khi tích hợp API
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF8B9C88),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .padding(start = 115.dp, top = 57.dp)
                    .align(Alignment.TopStart)
            )
        }
        Box(modifier = Modifier
            .padding(end = 40.dp, top = 10.dp)
            .width(160.dp)
            .align(Alignment.TopEnd)
        ){
            Icon(
                painter = painterResource(id = R.drawable.greenline),
                modifier = Modifier
                    .padding(start = 20.dp, top = 33.dp)
                    .width(3.dp)
                    .height(41.dp)
                    .align(Alignment.TopStart),
                contentDescription = "",
                tint = Color(0xFFDCA713)
            )
            Box(modifier = Modifier
                .padding(top = 30.dp)
                .width(40.dp)
                .height(16.dp)
                .align(Alignment.TopEnd)
                .background(color = Color(0xFF923232), shape = RoundedCornerShape(size = 3.dp))
            )
            {
                Icon(
                    painter = painterResource(R.drawable.down),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .padding(top = 4.dp, start = 3.dp)
                        .align(Alignment.TopStart)
                )
                Text(
                    text = "1.3%",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(start = 13.dp, top = (1.5).dp)
                        .align(Alignment.TopStart)
                )
            }
            Text(
                text = "Tiêu",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF5D8736),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .padding(start = 28.dp, top = 26.dp)
                    .align(Alignment.TopStart)
            )
            Text(
                text = "143,500", // Để cứng tạm rồi thay sau khi tích hợp API
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF355F2E),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .padding(start = 28.dp, top = 54.dp)
                    .align(Alignment.TopStart)
            )
            Text(
                text = "₫/kg", // Để cứng tạm rồi thay sau khi tích hợp API
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF8B9C88),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .padding(start = 115.dp, top = 57.dp)
                    .align(Alignment.TopStart)
            )
        }
        Box(modifier = Modifier
            .padding(start = 10.dp, bottom = 20.dp)
            .width(160.dp)
            .align(Alignment.BottomStart)
        ){
            Icon(
                painter = painterResource(id = R.drawable.greenline),
                modifier = Modifier
                    .padding(start = 20.dp, top = 33.dp)
                    .width(3.dp)
                    .height(41.dp)
                    .align(Alignment.TopStart),
                contentDescription = "",
                tint = Color(0xFFDCA713)
            )
            Box(modifier = Modifier
                .padding(top = 30.dp)
                .width(40.dp)
                .height(16.dp)
                .align(Alignment.TopEnd)
                .background(color = Color(0xFFA1A1A1), shape = RoundedCornerShape(size = 3.dp))
            )
            {
                Icon(
                    painter = painterResource(R.drawable.down),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .padding(top = 4.dp, start = 3.dp)
                        .align(Alignment.TopStart)
                )
                Text(
                    text = "0%",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(start = 13.dp, top = (1.5).dp)
                        .align(Alignment.TopStart)
                )
            }
            Text(
                text = "Mít thái",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF5D8736),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .padding(start = 28.dp, top = 26.dp)
                    .align(Alignment.TopStart)
            )

            Text(
                text = "14,000", // Để cứng tạm rồi thay sau khi tích hợp API
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF355F2E),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .padding(start = 28.dp, top = 54.dp)
                    .align(Alignment.TopStart)
            )
            Text(
                text = "₫/kg", // Để cứng tạm rồi thay sau khi tích hợp API
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF8B9C88),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                ),
                modifier = Modifier
                    .padding(start = 115.dp, top = 57.dp)
                    .align(Alignment.TopStart)
            )
        }
        Box(
            modifier = Modifier
                .padding(bottom = 20.dp, end = 40.dp)
                .width(129.dp)
                .height(49.dp)
                .align(Alignment.BottomEnd)
                .background(
                    color = Color(0xFFF2EAEA),
                    shape = RoundedCornerShape(5.dp)
                )
                .drawBehind {
                    // Thiết lập thông số cho viền
                    val strokeWidth = 1.dp.toPx()
                    val dashWidth = 5.dp.toPx()
                    val dashGap = 5.dp.toPx()
                    val cornerRadius = 5.dp.toPx()

                    // Vẽ đường viền đen nét đứt
                    drawRoundRect(
                        color = Color.Black,
                        size = size,
                        cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                        style = Stroke(
                            width = strokeWidth,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap), 0f)
                        )
                    )
                }
        ) {
            Text(
                text = "+",
                style = TextStyle(
                    color = Color(0xFF818181),
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .align(Alignment.TopCenter)
            )
            Text(
                text = "Thêm nông sản",
                style = TextStyle(
                    color = Color(0xFF818181),
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(top = 25.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun PopularNews() {
    Box(
        modifier = Modifier
            .offset(y = 8.dp)
            .width(393.dp)
            .height(32.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(27.dp)
                .padding(horizontal = 20.dp)
                .background(color = Color(0xFFEB8317), shape = RoundedCornerShape(size = 3.dp))
        )
        Text(
            text = "Thông tin phổ biến",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                letterSpacing = 1.8.sp,
            ),
            modifier = Modifier
                .offset(y = 1.dp)
        )

    }
}


@Composable
fun TemperatureScreen() {
    var temp by remember { mutableStateOf(36) }   // Giá trị nhiệt độ ban đầu

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000) // Cập nhật mỗi 5 giây
            temp = (16..40).random() // Giả lập nhiệt độ ngẫu nhiên từ 30 - 40 độ C
        }
    }

    val status = when (temp) {
        in 16..20 -> "Mát mẻ"
        in 21..27 -> "Nắng nhẹ"
        in 28..33 -> "Nắng nóng"
        else -> "Nắng gắt"
    }

    Text(text = "$temp °C\n$status",
        fontFamily = FontFamily(Font(R.font.montserrat)),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFDCA713),
        lineHeight = 20.sp,
        modifier = Modifier.offset(x = 90.dp, y = 65.dp))
}