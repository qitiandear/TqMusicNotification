package com.tq.tq_music_notification

import android.content.Context
import android.util.Log
import snow.player.PlayerClient
import snow.player.audio.MusicItem
import snow.player.playlist.Playlist

class MyMusicNotification(context: Context){
    private var playerClient: PlayerClient
    init {
        // 创建一个 PlayerClient 对象
        playerClient = PlayerClient.newInstance(context, MyPlayerService::class.java)

        // 连接到 PlayerService
        playerClient.connect { success -> // DEBUG
            Log.d("App", "connect: $success")
        }
    }

    fun play(musicItems: List<MusicInfo>){
        // 创建播放列表
        val playlist = createPlaylist(musicItems)

        playerClient.setPlaylist(playlist!!, true)
    }

    private fun createPlaylist(musicItems: List<MusicInfo>): Playlist? {
        val mutableListOf = mutableListOf<MusicItem>()
        for (musicItem in musicItems) {
            // 创建音乐项
            val song = MusicItem.Builder()
                .setTitle(musicItem.title)
                .setArtist(musicItem.artist?: "未知")
                .setAlbum(musicItem.album?: "未知")
                .setDuration(musicItem.duration)
                .setUri(musicItem.uri)
                .setIconUri(musicItem.iconUri?: "")
                .setForbidSeek(musicItem.forbidSeek?: false)
                .build()

            mutableListOf.add(song)
        }
        val list = mutableListOf.toList()
        return Playlist.Builder()
            .appendAll(list)
            .build()
    }
}