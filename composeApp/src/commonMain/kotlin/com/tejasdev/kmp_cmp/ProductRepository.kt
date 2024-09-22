package com.tejasdev.kmp_cmp

import com.tejasdev.kmp_cmp.apiClient.httpClient
import com.tejasdev.kmp_cmp.data.Product
import com.tejasdev.kmp_cmp.data.ProductResult
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class ProductRepository {

    private val BASE_URL = "https://fakestoreapi.com/products"

    suspend fun getProductsApi(): List<Product>{
        val response = httpClient.get(BASE_URL)
        return response.body()
    }

    fun getProducts() = flow {
        emit(getProductsApi())
    }
}