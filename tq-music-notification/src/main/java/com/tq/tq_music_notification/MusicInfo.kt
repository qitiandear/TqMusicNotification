package com.tq.tq_music_notification

class MusicInfo(
    var title: String,
    var artist: String?,
    var album: String?,
    var uri: String,
    var iconUri: String?,
    var duration: Int,
    var forbidSeek : Boolean?
) {
}