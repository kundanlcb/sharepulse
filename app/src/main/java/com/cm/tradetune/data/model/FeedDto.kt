package com.cm.tradetune.data.model

import java.io.Serializable

data class FeedDto(
    val userName: String,
    val time: String,
    val isPoll: Boolean,
    val pollQuestion: String? = null,
    val pollData: List<PollDataDto>? = null,
    val pollResult: List<Int>? = null,
    val contentText: String? = null,
    val mediaList: List<MediaMetaDto>? = null,
    var alreadyVoted: Boolean,
    var likes: Int,
    var repostCount: Int,
    val shareCount: Int,
    var isReposted: Boolean,
    var likedByUser: Boolean,
    val repostedByUser: Boolean,
    val id: Long
): Serializable
