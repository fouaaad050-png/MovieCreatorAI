package com.moviecreatorai.app

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MovieCreatorApp() }
    }
}

@Composable
fun MovieCreatorApp() {
    var videoUri by remember { mutableStateOf<Uri?>(null) }
    var script by remember { mutableStateOf("") }
    val picker = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri -> videoUri = uri }

    val bg = Color(0xFF0B1020)
    val purple = Color(0xFF8B7CFF)

    MaterialTheme(
        colorScheme = darkColorScheme(primary = purple)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bg)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(30.dp))

            Icon(
                Icons.Default.Movie,
                contentDescription = null,
                tint = purple,
                modifier = Modifier.size(55.dp)
            )

            Text(
                "MOVIE CREATOR AI",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Black
            )

            Text(
                "استوديو صناعة ملخصات الأفلام بالذكاء الاصطناعي",
                color = Color.LightGray,
                fontSize = 13.sp
            )

            Spacer(Modifier.height(35.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF171F35)
                ),
                shape = RoundedCornerShape(22.dp)
            ) {
                Column(
                    modifier = Modifier.padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.CloudUpload,
                        contentDescription = null,
                        tint = purple,
                        modifier = Modifier.size(45.dp)
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "ابدأ مشروعك الجديد",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        "ارفع الفيديو للبدء في صناعة ملخص احترافي",
                        color = Color.LightGray
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = {
                            picker.launch(arrayOf("video/*"))
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.VideoLibrary, null)
                        Spacer(Modifier.width(8.dp))
                        Text("اختيار فيديو من الهاتف")
                    }

                    Spacer(Modifier.height(12.dp))

                    Text(
                        if (videoUri == null)
                            "لم يتم اختيار فيديو بعد"
                        else
                            "تم اختيار الفيديو بنجاح",
                        color = Color(0xFF8FE3B0),
                        fontSize = 12.sp
                    )
                }
            }
OutlinedTextField(
    value = script,
    onValueChange = { script = it },
    label = { Text("اكتب السيناريو هنا") },
    modifier = Modifier.fillMaxWidth(),
    minLines = 4
)

Spacer(Modifier.height(16.dp))
            Spacer(Modifier.height(25.dp))

            Text(
                "مراحل الإنتاج",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(12.dp))

            Stage("استخراج النص الأصلي", "تحويل الكلام إلى نص")
            Stage("إعادة صياغة النص", "كتابة سيناريو عربي احترافي")
            Stage("إنشاء الصوت", "تحويل النص إلى MP3")
            Stage("المونتاج والترجمة", "دمج الصوت والكتابة مع الفيديو")
            Stage("تصدير الفيديو", "حفظ MP4 بجودة 1080p")
        }
    }
}

@Composable
fun Stage(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF171F35)
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = Color(0xFF9B91FF)
            )

            Spacer(Modifier.width(13.dp))

            Column {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                Text(description, color = Color.LightGray, fontSize = 12.sp)
            }
        }
    }
}
