package com.razorpayassignment.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.razorpayassignment.data.UIData

@Composable
fun ItemTextView(data: UIData) {
    Spacer(modifier = Modifier.height(20.dp))
    data.value?.let {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = it,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}
