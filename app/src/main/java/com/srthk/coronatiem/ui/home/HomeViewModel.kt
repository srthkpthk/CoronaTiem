package com.srthk.coronatiem.ui.home

import androidx.lifecycle.ViewModel
import com.srthk.coronatiem.data.repository.NationalDataRepository
import com.srthk.coronatiem.util.lazyDeferred

class HomeViewModel(
    repository: NationalDataRepository
) : ViewModel() {
    val nationalData by lazyDeferred { repository.getNationalData() }
    val isInternetAvailable by lazy { repository.isInternetAvailable }
    val isApiException by lazy { repository.isApiException }
}
