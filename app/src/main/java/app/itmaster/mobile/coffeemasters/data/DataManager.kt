package app.itmaster.mobile.coffeemasters.data

import android.content.ClipData.Item
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DataManager : ViewModel() {
    var menu: List<Category> by mutableStateOf(listOf())
    var cart: List<ItemInCart> by mutableStateOf(listOf())

    init {
        fetchData()
    }

    fun fetchData() {
        // Ejecuta el getMenu en una corutina (algo así como un thread)
        viewModelScope.launch {
            menu = API.menuService.getMenu()
        }
    }

    fun cartAdd(product: Product) {
        // Verifica si el producto ya está en el carrito y sumarle uno
        val existingItem = cart.find { it.product == product }
        if (existingItem != null) {
            // El producto ya está en el carrito, incrementa la cantidad
            val updatedCart = cart.map {
                if (it == existingItem) {
                    it.copy(quantity = it.quantity + 1)
                } else {
                    it
                }
            }
            cart = updatedCart
        } else {
            // El producto no está en el carrito, agrégalo con cantidad 1
            cart = cart + ItemInCart(product, 1)
        }
        println(cart)
    }

    fun cartRemove(product: Product) {
        // Verifica si el producto está en el carrito y réstale uno
        val existingItem = cart.find { it.product == product }
        if (existingItem != null) {
            // El producto está en el carrito, reduce la cantidad
            val updatedCart = cart.map {
                if (it == existingItem && it.quantity > 1) {
                    it.copy(quantity = it.quantity - 1)
                } else {
                    it
                }
            }.filter { it.quantity > 0 } // Filtra los elementos con cantidad 0 o menos
            cart = updatedCart
        }
    }
}
