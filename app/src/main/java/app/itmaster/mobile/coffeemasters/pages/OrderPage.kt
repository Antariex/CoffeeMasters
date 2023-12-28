package app.itmaster.mobile.coffeemasters.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.itmaster.mobile.coffeemasters.data.DataManager

@Composable
fun OrderPage(dataManager: DataManager) {
    LazyColumn(
        modifier = Modifier
            .padding(4.dp)
            .background(Color(0xFFFFD3DC))
            .fillMaxWidth()
    ) {
        if (dataManager.cart.isEmpty()) {
            item {
                Text(
                    text = "Empty Cart",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8B4513)
                )
            }
        } else {
            item {
                Text(
                    text = "ITEMS",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8B4513)
                )
            }
            items(dataManager.cart) { itemInCart ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${itemInCart.quantity}x",
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8B4513),
                        modifier = Modifier.padding(24.dp)
                    )

                    Text(
                        text = itemInCart.product.name,
                        textAlign = TextAlign.Start,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${itemInCart.product.price}",
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "trash icon",
                        tint = Color(0xFF8B4513),
                        modifier = Modifier.clickable {
                            dataManager.cartRemove(itemInCart.product)
                        }
                    )
                }
            }
        }
    }
}