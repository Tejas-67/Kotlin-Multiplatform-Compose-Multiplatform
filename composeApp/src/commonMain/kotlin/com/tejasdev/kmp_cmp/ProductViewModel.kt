package com.tejasdev.kmp_cmp

import com.tejasdev.kmp_cmp.data.Product
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {

    private val _products = MutableStateFlow(listOf<Product>())
    val products = _products.asStateFlow()

    val repository = ProductRepository()

    init {
        viewModelScope.launch {
            repository.getProducts().collect{ productResult ->
                _products.update { it + productResult.data }
            }
        }
    }
}