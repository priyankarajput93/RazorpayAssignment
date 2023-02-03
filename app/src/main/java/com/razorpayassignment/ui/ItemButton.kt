package com.razorpayassignment.ui

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.razorpayassignment.activities.HomeActivity
import com.razorpayassignment.data.ApiResponse
import com.razorpayassignment.data.UIData

@Composable
fun ItemButton(data: UIData, apiResponse: ApiResponse) {
    val context = LocalContext.current
    Spacer(modifier = Modifier.height(40.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val intent = Intent(context, HomeActivity::class.java)
                intent.putExtra("data", apiResponse)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier
                .height(48.dp)
                .width(180.dp)
        )

        {
            Text(text = data.value!!, color = Color.White)
        }

    }
}