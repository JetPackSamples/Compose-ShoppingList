package com.artf.data.database

import androidx.lifecycle.LiveData
import com.artf.data.database.model.Product
import com.artf.data.database.model.ShoppingList
import com.artf.data.status.ResultStatus

interface ShoppingListSource {
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    suspend fun updateShoppingList(shoppingList: ShoppingList)

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    fun getCurrentShoppingList(): LiveData<ResultStatus<List<ShoppingList>>>

    fun getArchivedShoppingList(): LiveData<ResultStatus<List<ShoppingList>>>

    fun getProductList(listId: Long): LiveData<ResultStatus<List<Product>>>
}