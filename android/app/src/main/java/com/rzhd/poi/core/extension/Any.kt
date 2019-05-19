package com.rzhd.poi.core.extension

inline fun <T, R> T.applyIfNotNull(item: R?, block: T.(R) -> Unit): T = item?.let { apply { block(it) } } ?: this