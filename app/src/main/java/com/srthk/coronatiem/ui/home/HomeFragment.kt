package com.srthk.coronatiem.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.srthk.coronatiem.R
import com.srthk.coronatiem.data.db.entries.Statewise
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory: NationalViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.home_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = CoroutineScope(Dispatchers.Main).launch {
        viewModel.nationalData.await().observe(viewLifecycleOwner, Observer {
            initRv(it.toNationalDataItem())
        })
    }


    private fun initRv(stateList: List<NationalDataItem>) {
        println(stateList[0])
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(stateList)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }

}

private fun List<Statewise>.toNationalDataItem() = this.map { NationalDataItem(it) }
