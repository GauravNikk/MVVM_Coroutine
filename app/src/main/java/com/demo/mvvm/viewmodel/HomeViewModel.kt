package com.demo.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.network.exceptions.ApiException
import com.demo.network.exceptions.NoInternetException
import com.demo.network.repository.ImageRepository
import com.demo.network.responseModels.home.ImageResponseModelItem
import kotlinx.coroutines.launch

class HomeViewModel (var repository: ImageRepository) :ViewModel() {

    private val _imageData = MutableLiveData<MutableList<ImageResponseModelItem>>()
    val imageData:LiveData<MutableList<ImageResponseModelItem>> =_imageData

    fun updateImageData(images: MutableList<ImageResponseModelItem>){
        _imageData.value = images
    }

    init {
        getImages("1", "20")
    }

    fun getImages(page:String,limit:String){
        viewModelScope.launch {
            try {
               repository.getImages(page,limit) ?.let {
                   imageResponseModel ->

                   imageResponseModel?.let {  temp ->

                       if (_imageData.value!=null){
                          _imageData.value?.addAll(temp)
                       }else{
                           _imageData.value = temp
                       }
                   }
               }
            }catch (e:ApiException){

            }catch (noE:NoInternetException){

            }
        }
    }
}