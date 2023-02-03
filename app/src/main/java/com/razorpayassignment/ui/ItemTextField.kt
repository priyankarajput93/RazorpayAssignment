package com.razorpayassignment.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.razorpayassignment.data.UIData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemTextField(data: UIData) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        modifier = Modifier.padding(horizontal = 20.dp),
        value = text,
        maxLines = 1,
        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
        onValueChange = {
            text = it
            data.key = text.text
        },
        label = { data.value?.let { Text(text = it) } },
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
        keyboardOptions = if (data.hint == "Enter your phone no")
            KeyboardOptions(keyboardType = KeyboardType.Number)
        else
            KeyboardOptions(keyboardType = KeyboardType.Text),

        placeholder = { data.value?.let { Text(text = it) } },
    )
}