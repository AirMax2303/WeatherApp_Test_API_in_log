package com.example.weatherapp

import android.app.DownloadManager.Request
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import org.json.JSONObject
import java.lang.reflect.Method

const val API_KEY = "d1f03197da864cd095c85457221711"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bGet.setOnClickListener {
            getResult("London")
        }
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET,
            url,
            {
              response ->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog","Volley good: ${temp.getString("temp_c")}")
            },
            {
                Log.d("MyLog","Volley error: $it")
            }
        )
        queue.add(stringRequest)
    }
}