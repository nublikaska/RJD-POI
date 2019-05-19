package com.rzhd.poi.core.list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(

        val binding: ViewDataBinding,
        val viewType: Int

) : RecyclerView.ViewHolder(binding.root)