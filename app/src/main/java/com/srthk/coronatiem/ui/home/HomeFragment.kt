package com.srthk.coronatiem.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.srthk.coronatiem.R
import com.srthk.coronatiem.data.db.entries.Statewise
import com.srthk.coronatiem.databinding.HomeFragmentBinding
import com.srthk.coronatiem.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment : Fragment(R.layout.home_fragment), KodeinAware {
    override val kodein by kodein()
    private val factory: NationalViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel
    private var homeFragmentBinding: HomeFragmentBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        homeFragmentBinding = HomeFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        bindUI()
    }

    private fun showLoading(flag: Boolean) {
        shimmer.apply {
            isVisible = flag
            showShimmer(flag)
        }
    }

    private fun bindUI() = CoroutineScope(Dispatchers.Main).launch {
        viewModel.nationalData.await().observe(viewLifecycleOwner, Observer {
            when {
                viewModel.isInternetAvailable && !viewModel.isApiException -> {
                    when {
                        !it.isNullOrEmpty() -> {
                            homeFragmentBinding!!.total = it[0]
                            initRv(it.filter { it.statecode != "TT" }.toNationalDataItem())
                        }
                    }
                }
                else -> {
                    this@HomeFragment.requireContext()
                        .toast("There's an error better check for internet")
                    showLoading(false)

                }
            }
        })
    }


    private fun initRv(stateList: List<NationalDataItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(stateList)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter

        }
        showLoading(false)
    }

}

private fun List<Statewise>.toNationalDataItem() = this.map { NationalDataItem(it) }
