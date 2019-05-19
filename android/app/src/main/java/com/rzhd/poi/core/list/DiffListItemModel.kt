package com.rzhd.poi.core.list

interface DiffListItemModel {

    fun isSameAs(other: DiffListItemModel): Boolean
}