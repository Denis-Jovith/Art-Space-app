package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ArtSpaceAppScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceAppScreen() {
    var result by remember { mutableStateOf(1) }

    val artist = when (result) {
        1 -> {
            ArtSpaceImageText(
                image = R.drawable.kilimanjaro,
                title = R.string.kilimanjaro,
                artist = R.string.kilimanjaro_artist,
                year = R.string.kilimanjaro_year
            )
        }
        2 -> {
            ArtSpaceImageText(
                image = R.drawable.mwanza,
                title = R.string.mwanza,
                artist = R.string.mwanza_artist,
                year = R.string.mwanza_year
            )
        }
        3 -> {
            ArtSpaceImageText(
                image = R.drawable.dodoma,
                title = R.string.dodoma,
                artist = R.string.dodoma_artist,
                year = R.string.dodoma_year
            )
        }
        else -> {
            Text("Error: Unknown artwork")
        }
    }

    ButtonArt(
        modifierButton = Modifier
            .height(30.dp)
            .width(150.dp),
        clickPrev = { if (result == 1) result = 3 else result-- },
        clickNext = { if (result == 3) result = 1 else result++ }
    )
}

@Composable
fun ArtSpaceImageText(image: Int, title: Int, artist: Int, year: Int) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .heightIn(500.dp, 530.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = stringResource(title),
                modifier = Modifier
                    .border(3.dp, Color.Black, RectangleShape)
                    .padding(16.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Row {
                Surface(
                    modifier = Modifier
                        .heightIn(80.dp, 300.dp)
                        .widthIn(300.dp, 380.dp),
                    shape = RectangleShape,
                ) {
                    Box {
                        Text(
                            text = stringResource(title),
                            fontSize = 30.sp,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Box(modifier = Modifier.padding(top = 50.dp)) {
                        Text(
                            text = "${stringResource(artist)} - ${stringResource(year)}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonArt(
    modifierButton: Modifier,
    clickPrev: () -> Unit,
    clickNext: () -> Unit
) {
    Column(
        modifier = Modifier.padding(top = 300.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = clickPrev, modifier = modifierButton) {
                Text(text = "Previous", fontSize = 18.sp)
            }
            Button(onClick = clickNext, modifier = modifierButton) {
                Text(text = "Next", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceAppTheme {
        ArtSpaceAppScreen()
    }
}
