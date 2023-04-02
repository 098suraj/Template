package com.example.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.template.ui.theme.TemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            modifier = Modifier.weight(1.0f),
            text = "Hello $name!",
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif
        )
        Button(
            modifier = Modifier.weight(1.0f),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Hello $name!",
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif
            )
        }
        TextField(
            modifier = Modifier
                .align(CenterHorizontally)
                .weight(1.0f),
            value = text,
            onValueChange = {
                text = it
            },

            label = { },

            placeholder = { Text(text = "Your Placeholder/Hint") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemplateTheme {
        Greeting("Android", Modifier)
    }
}
