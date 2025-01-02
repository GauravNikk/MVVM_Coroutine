package com.demo.network.repository

import com.demo.network.ApiService
import com.demo.network.SafeApiRequest
import com.demo.network.responseModels.home.ImageResponseModel
import com.demo.network.responseModels.home.ImageResponseModelItem

class ImageRepository(private val apiService: ApiService) :SafeApiRequest(){

    suspend fun getImages(page:String,limit:String): MutableList<ImageResponseModelItem>{
        return  apiRequest { apiService.getImageList(page , limit) }
    }

}