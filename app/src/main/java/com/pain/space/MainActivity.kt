package com.pain.space

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.webkit.WebViewAssetLoader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val webView = findViewById<WebView>(R.id.webView)

        // Enable JavaScript and WebGL rendering
        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccess = true
        webView.settings.allowFileAccessFromFileURLs = true
        webView.settings.allowUniversalAccessFromFileURLs = true
        webView.settings.domStorageEnabled = true

        // Enable WebGL
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()

        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccess = false // Block direct file access for security
        webView.settings.domStorageEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                val url = request?.url.toString()
                Log.d("WebViewDebug", "Intercepting: $url")
                return request?.url?.let { assetLoader.shouldInterceptRequest(it) }
            }
        }
        webView.webChromeClient = WebChromeClient()

        // Load the local HTML file from assets
        val keyString = intent.getStringExtra("key")
        val key = keyString?.toIntOrNull()

        when (key) {
            0 -> webView.loadUrl("https://sketchfab.com/models/9ef1c68fbb944147bcfcc891d3912645/embed") // Sun
            1 -> webView.loadUrl("https://sketchfab.com/models/b306aaadbf2b4fcea1afa2db5ed75b4f/embed") // Venus
            2 -> webView.loadUrl("https://sketchfab.com/models/215f364627054d25ae03bcd72afda714/embed") // Earth
            3 -> webView.loadUrl("https://sketchfab.com/models/5f9c35be31a047928eace8b415a8ee3a/embed") // Mars
            4 -> webView.loadUrl("https://sketchfab.com/models/99be254b68da48d092c3b8917020c67a/embed") // Ceres
            5 -> webView.loadUrl("https://sketchfab.com/models/c5275eb96af245e4a8453837ac728a62/embed") // Jupiter
            6 -> webView.loadUrl("https://sketchfab.com/models/c09a1970148c43ad99db134a9d6d00b5/embed") // Saturn
            7 -> webView.loadUrl("https://sketchfab.com/models/08a86fd8e84a4426b1b4393b63346ce4/embed") // Uranus
            8 -> webView.loadUrl("https://sketchfab.com/models/fe05e06a265d4a8f9285d34c933878ee/embed") // Neptune
            9 -> webView.loadUrl("https://sketchfab.com/models/b879944fba414eb897c7282d2c1a4ab3/embed") // Pluto
            else -> webView.loadUrl("https://sketchfab.com") // Fallback URL
        }
    }
}