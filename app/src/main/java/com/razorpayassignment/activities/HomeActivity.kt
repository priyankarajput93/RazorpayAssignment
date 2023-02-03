package com.razorpayassignment.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.razorpayassignment.data.ApiResponse
import com.razorpayassignment.data.UIData

class HomeActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = intent.getParcelableExtra<ApiResponse>("data")
        val listItems = list?.uiData!!
        setContent {
            Surface() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DataItemList(list = listItems)
                }
            }
        }
    }

    @Composable
    fun DataItemList(
        modifier: Modifier = Modifier,
        list: ArrayList<UIData>
    ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                val customList = list.filter { it -> it.uitype != "label" }
                items(customList.size) { index ->

                    customList[index].key?.let {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = it,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                        )
                    }
                }
            }
        }
    }
}