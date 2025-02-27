package com.example.vkgooglecast.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkgooglecast.domain.PlayVideoUseCase
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadRequestData
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.SessionManager
import kotlinx.coroutines.launch

class CastVideoViewModel(
    private val castContext: CastContext,
    private val playVideoUseCase: PlayVideoUseCase
) : ViewModel() {

    private val sessionManager: SessionManager by lazy {
        castContext.sessionManager
    }

    fun playVideo() {
        viewModelScope.launch {
            sessionManager.currentCastSession?.remoteMediaClient?.let { remoteMediaClient ->
                val videoUrl = playVideoUseCase.getVideoUrl()
                val mediaLoadRequestData = createMediaLoadRequest(videoUrl)
                remoteMediaClient.load(mediaLoadRequestData)
            }
        }
    }

    private fun createMediaLoadRequest(videoUrl: String): MediaLoadRequestData {
        val mediaInfo = MediaInfo.Builder(videoUrl)
            .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
            .setContentType("video/mp4")
            .build()

        return MediaLoadRequestData.Builder()
            .setMediaInfo(mediaInfo)
            .build()
    }
}

