package com.example.vkgooglecast.domain

import com.example.vkgooglecast.data.repository.CastVideoRepository

class PlayVideoUseCase(private val videoRepository: CastVideoRepository) {

    fun getVideoUrl(): String {
        return videoRepository.getVideoUrl()
    }
}
