package br.com.ulbra.exemplorecycler.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ulbra.exemplorecycler.commons.Resultado
import br.com.ulbra.exemplorecycler.data.Product
import br.com.ulbra.exemplorecycler.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    val products = MutableLiveData<Resultado<List<Product>>>()
    fun getProducts() {
        products.postValue(Resultado.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)

            try {
                val resultado = productRepository.fetchProducts()
                products.postValue(Resultado.Success(resultado))
            } catch (e: Exception) {
                products.postValue(Resultado.Error(e))
            }
        }
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insertProduct(product = product)
        }
    }
}