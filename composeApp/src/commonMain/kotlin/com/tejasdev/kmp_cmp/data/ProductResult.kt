package com.tejasdev.kmp_cmp.data

import kotlinx.serialization.Serializable

@Serializable
data class ProductResult(
    val data: List<Product>
)