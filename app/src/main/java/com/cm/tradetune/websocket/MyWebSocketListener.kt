package com.cm.tradetune.websocket// WebSocketListener.kt
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class MyWebSocketListener : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        println("onOpen: "+response)
    // Handle the connection open event
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        println("onMessage: "+text)
        // Handle received text message
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {

        // Handle received binary message
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        println("onClosing: "+reason)
        // Handle the connection closing event
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        println("onFailure: "+response)
        println(t.message)
        // Handle connection failure
    }
}
