package br.brunocarvalhs.agenda.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color

@Composable
fun MainTopBar() {
    TopAppBar(
        title = { Text(text = "AppBar") },
        backgroundColor = Color.White
    )
}

@Preview
@Composable
private fun PreviewToolbar() {
    return MainTopBar()
}
