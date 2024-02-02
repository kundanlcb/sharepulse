package com.cm.tradetune.data.model

import java.io.Serializable

class HelloMessage(postContent: String) : Serializable {
    private val name: String = postContent

}
