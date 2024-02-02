package com.cm.tradetune.websocket

// WebSocketManager.kt
import com.cm.tradetune.data.model.HelloMessage
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketManager() : WebSocketListener() {

    private var webSocket: WebSocket? = null

    init {
        initWebSocket()
    }

    private fun initWebSocket() {
        val request = Request.Builder().url("ws://0.tcp.in.ngrok.io:19969/websocket").build()
        val client = OkHttpClient.Builder().build()
        webSocket = client.newWebSocket(request, this)
    }

    fun disconnectWebSocket() {
        webSocket?.close(1000, "User disconnected")
    }

    fun sendName(message: HelloMessage) {
        val json: String = Gson().toJson(message)
        val status = webSocket?.send(json)
        println("sendName: $status")
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        println("onOpen$response")
        super.onOpen(webSocket, response)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        println("onFailure $response")
        println("onFailure ${t.message}")
        super.onFailure(webSocket, t, response)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        println("onMessage$text")
        super.onMessage(webSocket, text)
    }
}