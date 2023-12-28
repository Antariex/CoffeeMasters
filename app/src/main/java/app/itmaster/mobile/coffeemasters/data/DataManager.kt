package app.itmaster.mobile.coffeemasters.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataManager : ViewModel() {
    var menu: List<Category> by mutableStateOf(listOf())
    var cart: List<ItemInCart> by mutableStateOf(listOf())

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            menu = API.menuService.getMenu()
        }
    }

    fun cartAdd(product: Product) {
        val existingItem = cart.find { it.product == product }
        if (existingItem != null) {
            val updatedCart = cart.map {
                if (it == existingItem) {
                    it.copy(quantity = it.quantity + 1)
                } else {
                    it
                }
            }
            cart = updatedCart
        } else {
            cart = cart + ItemInCart(product, 1)
        }
    }

    fun cartRemove(product: Product) {
        cart = cart.filter { it.product != product }
    }
}
