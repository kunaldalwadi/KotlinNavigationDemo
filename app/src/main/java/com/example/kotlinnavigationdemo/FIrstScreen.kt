package com.example.kotlinnavigationdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FirstScreen(
    toBottomHomeScreen: () -> Unit = {},
    toSecondScreen: () -> Unit = {},
    toThirdScreen: () -> Unit = {},
    toFourthScreen: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = toBottomHomeScreen,
            modifier = modifier.padding(24.dp)
        ) {
            Text(text = "To Landing Screen")
        }
        Text(
            text = "First Screen",
            modifier = modifier.padding(16.dp)
        )
        Button(
            onClick = toSecondScreen
        ) {
            Text(text = "To Second Screen")
        }
        Button(
            onClick = toThirdScreen
        ) {
            Text(text = "To Third Screen")
        }
        Button(
            onClick = toFourthScreen
        ) {
            Text(text = "To Fourth Screen")
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    name = "FirstScreenPreviewDark"
)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FirstScreenPreview() {
    FirstScreen({},{},{})
}