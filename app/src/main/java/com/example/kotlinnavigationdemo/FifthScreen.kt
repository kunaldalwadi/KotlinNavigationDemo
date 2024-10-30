package com.example.kotlinnavigationdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FifthScreen(
    somearg: String? = "Sample Arguement",
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Fifth Screen",
            modifier = modifier.padding(16.dp)
        )
        Text(
            text = "Argument: $somearg",
            modifier = modifier.padding(16.dp)
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    name = "FifthScreenPreviewDark"
)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FifthScreenPreview() {
    FifthScreen()
}