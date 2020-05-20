package com.srthk.coronatiem.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.srthk.coronatiem.data.repository.NationalDataRepository

@Suppress("UNCHECKED_CAST")
class NationalViewModelFactory(
    private val repository: NationalDataRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeViewModel(repository) as T

}
