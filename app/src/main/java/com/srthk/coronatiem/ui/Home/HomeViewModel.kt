package com.srthk.coronatiem.ui.Home

import androidx.lifecycle.ViewModel
import com.srthk.coronatiem.data.repository.NotionalDataRepository
import com.srthk.coronatiem.util.lazyDeferred

class HomeViewModel(
    repository: NotionalDataRepository
) : ViewModel() {
    val nationalData by lazyDeferred { repository.getNationalData() }
}
