package com.srthk.coronatiem.ui.Home

import com.srthk.coronatiem.R
import com.srthk.coronatiem.data.db.entries.Statewise
import com.srthk.coronatiem.databinding.ItemListBinding
import com.xwray.groupie.databinding.BindableItem

class NationalDataItem(private val statewise: Statewise) : BindableItem<ItemListBinding>() {
    override fun getLayout() = R.layout.item_list

    override fun bind(viewBinding: ItemListBinding, position: Int) {
        viewBinding.state = statewise
    }
}