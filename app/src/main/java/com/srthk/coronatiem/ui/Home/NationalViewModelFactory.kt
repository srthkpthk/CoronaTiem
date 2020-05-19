package com.srthk.coronatiem.ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.srthk.coronatiem.data.repository.NotionalDataRepository

@Suppress("UNCHECKED_CAST")
class NationalViewModelFactory(
    private val repository: NotionalDataRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeViewModel(repository) as T

}
