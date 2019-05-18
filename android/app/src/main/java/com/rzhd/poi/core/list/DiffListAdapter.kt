package com.rzhd.poi.core.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class DiffListAdapter<T : DiffListItemModel> : RecyclerView.Adapter<BaseViewHolder>(), DiffAdapter<T> {

    val items: List<T>
        get() = _items

    private val _items = arrayListOf<T>()
    private val diffCallback by lazy { DiffCallbackImpl() }

    protected abstract fun getListItemLayoutId(type: Int = 0): Int

    protected abstract fun fillHolderViews(
        holder: BaseViewHolder?,
        item: T,
        viewType: Int,
        payloads: MutableList<Any>? = null
    )

    protected open fun getChangePayload(oldItem: T, newItem: T): String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val viewDataBinding: ViewDataBinding =
            DataBindingUtil.inflate(inflater!!, getListItemLayoutId(viewType), parent, false)

        return BaseViewHolder(viewDataBinding, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        val item = _items[position]

        fillHolderViews(holder, item, getItemViewType(position))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, payloads: MutableList<Any>) {

        val item = _items[position]

        fillHolderViews(holder, item, getItemViewType(position), payloads)
    }

    override fun getItemCount() = _items.size

    protected fun getItem(position: Int): T? = _items[position]

    fun getItemAtPosition(position: Int): T? = _items[position]

    override fun add(item: T) {

        val newItems = ArrayList(_items)

        newItems.add(item)

        updateItems(newItems)
    }

    override fun addAll(itemList: List<T>) {

        val newItems = ArrayList(_items)

        newItems.addAll(itemList)

        updateItems(newItems)
    }

    override fun addAtPosition(position: Int, item: T) {

        val newItems = ArrayList(_items)

        newItems.add(position, item)

        updateItems(newItems)
    }

    override fun addAllAtPosition(position: Int, itemList: List<T>) {

        val newItems = ArrayList(_items)

        newItems.addAll(position, itemList)

        updateItems(newItems)
    }

    fun removeItemAtPosition(position: Int) {

        val newItems = ArrayList(_items)

        newItems.removeAt(position)

        updateItems(newItems)
    }

    fun removeByCondition(removeCondition: (T) -> Boolean) {

        val tempItems = ArrayList(_items.filterNot(removeCondition))

        updateItems(tempItems)
    }

    fun removeItemsAtRange(range: IntRange) {

        val newItems = ArrayList(_items)

        for (index in range.last downTo range.first) {

            newItems.removeAt(index)
        }

        updateItems(newItems)
    }

    override fun clearAll() {

        _items.clear()

        notifyDataSetChanged()
    }

    override fun updateItems(itemList: List<T>) {

        if (itemList.isEmpty()) {
            clearAll()
            return
        }

        diffCallback.setLists(_items, itemList)

        val diffResult = DiffUtil.calculateDiff(diffCallback)

        _items.clear()
        _items.addAll(itemList)
        diffResult.dispatchUpdatesTo(this)
    }

    private inner class DiffCallbackImpl : DiffUtil.Callback() {

        private val oldItems = ArrayList<T>()
        private val newItems = ArrayList<T>()

        fun setLists(

            oldItems: List<T>,
            newItems: List<T>

        ) {

            this.oldItems.clear()
            this.oldItems.addAll(oldItems)

            this.newItems.clear()
            this.newItems.addAll(newItems)
        }

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]

            return oldItem.isSameAs(newItem)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]

            return oldItem == newItem
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {

            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]

            return this@DiffListAdapter.getChangePayload(oldItem, newItem)
        }
    }
}