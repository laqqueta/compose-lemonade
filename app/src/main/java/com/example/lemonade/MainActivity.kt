package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import com.example.lemonade.ui.theme.imageButton
import com.example.lemonade.ui.theme.imageButtonBorder
import com.example.lemonade.ui.theme.lemonadeAppBackground
import com.example.lemonade.ui.theme.topBarContainer
import com.example.lemonade.ui.theme.topBarTitle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var steps by remember {
        mutableIntStateOf(1)
    }

    var squeeze by remember {
        mutableIntStateOf(0)
    }

    var index by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topBarContainer,
                    titleContentColor = topBarTitle
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(lemonadeAppBackground)
                .fillMaxSize()
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           when(steps) {
               1 -> ImageButtonWithText(
                   image = painterResource(id = R.drawable.lemon_tree),
                   contentDesc = stringArrayResource(id = R.array.lemon_content_desc)[index],
                   text = stringArrayResource(id = R.array.lemonade_steps)[index])
                   {
                       steps++
                       index++
                       squeeze = (1..5).random()
                   }
               2 -> ImageButtonWithText(
                   image = painterResource(id = R.drawable.lemon_squeeze),
                   contentDesc = stringArrayResource(id = R.array.lemon_content_desc)[index],
                   text = stringArrayResource(id = R.array.lemonade_steps)[index])
                   {
                       if(squeeze-- == 0) {
                           steps++
                           index++
                       }
                   }
               3 -> ImageButtonWithText(
                   image = painterResource(id = R.drawable.lemon_drink),
                   contentDesc = stringArrayResource(id = R.array.lemon_content_desc)[index],
                   text = stringArrayResource(id = R.array.lemonade_steps)[index])
                   {
                       steps++
                       index++

                   }
               else -> ImageButtonWithText(
                   image = painterResource(id = R.drawable.lemon_restart),
                   contentDesc = stringArrayResource(id = R.array.lemon_content_desc)[index],
                   text = stringArrayResource(id = R.array.lemonade_steps)[index])
                   {
                       steps = 1
                       index = 0
                   }
           }
        }
    }
}

@Composable
fun ImageButtonWithText(image: Painter, contentDesc: String, text: String, onClickImage: () -> Unit) {
    Button(
        onClick = onClickImage,
        contentPadding = PaddingValues(50.dp),
        shape = RoundedCornerShape(45.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = imageButton
        ),
        border = BorderStroke(2.dp, imageButtonBorder)
    ) {
        Image(
            painter = image,
            contentDescription = contentDesc
        )
    }

    Spacer(Modifier.height(25.dp))

    Text(text = text, textAlign = TextAlign.Center, fontSize = 15.sp)
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_2_XL
)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}