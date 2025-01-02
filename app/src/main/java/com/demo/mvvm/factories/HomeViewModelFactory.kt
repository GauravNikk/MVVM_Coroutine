package com.demo.mvvm.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.demo.mvvm.viewmodel.HomeViewModel
import com.demo.network.repository.ImageRepository


class HomeViewModelFactory(val repository: ImageRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return HomeViewModel(repository) as T
    }

}