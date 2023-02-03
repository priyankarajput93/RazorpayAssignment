package com.razorpayassignment.activities

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.razorpayassignment.api.ApiService
import com.razorpayassignment.data.ApiResponse
import com.razorpayassignment.ui.ImageFromURL
import com.razorpayassignment.ui.ItemButton
import com.razorpayassignment.ui.ItemTextField
import com.razorpayassignment.ui.ItemTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import timber.log.Timber.DebugTree
import android.app.AlertDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.razorpayassignment.BuildConfig

class MainActivity : AppCompatActivity() {

    val responseData = mutableStateOf(ApiResponse())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        setContent {
            Surface() {
                Column(Modifier.background(Color.White)) {
                    DisplayItemList()
                }
            }
        }
    }

    @Composable
    fun DisplayItemList(
        modifier: Modifier = Modifier,
    ) {
        LaunchedEffect(Unit, block = {
            getData()
        })
        if (responseData.value.logoUrl != null)
            ImageFromURL(url = responseData.value.logoUrl!!)
        if (responseData.value.headingText != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = responseData.value.headingText!!,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        LazyColumn(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            items(responseData.value.uiData.size) { index ->
                when (responseData.value.uiData[index].uitype) {
                    "label" -> ItemTextView(data = responseData.value.uiData[index])
                    "edittext" -> ItemTextField(data = responseData.value.uiData[index])
                    "button" -> ItemButton(
                        data = responseData.value.uiData[index],
                        responseData.value
                    )
                }
            }
        }
    }

    private fun getData() {
        val apiService = ApiService.getInstance()
        val call: Call<ApiResponse> = apiService.getData()
        call.enqueue(object : Callback<ApiResponse?> {
            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {
                if (response.isSuccessful) {
                    responseData.value = response.body()!!
                }
            }
            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                customAlertDialog(message = t.message.toString())
            }
        })
    }

    private fun customAlertDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("", DialogInterface.OnClickListener { _, _ ->
                finish()
            })
            .setNegativeButton("Ok", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Alert")
        alert.show()
    }
}