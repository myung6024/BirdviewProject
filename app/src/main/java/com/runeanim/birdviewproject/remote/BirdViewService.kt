package com.runeanim.birdviewproject.remote

import retrofit2.http.GET

interface BirdViewService {

    @GET("products")
    suspend fun searchRepositories()
}