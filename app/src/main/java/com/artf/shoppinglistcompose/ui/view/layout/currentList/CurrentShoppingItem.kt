package com.artf.shoppinglistcompose.ui.view.layout.currentList

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.IconButton
import androidx.ui.material.Surface
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.data.SharedViewModel
import com.artf.shoppinglistcompose.ui.data.state.ScreenState
import com.artf.shoppinglistcompose.ui.data.model.ShoppingListUi
import com.artf.shoppinglistcompose.util.getDateFormat

@Composable
fun ShoppingListCurrentItem(
    sharedViewModel: SharedViewModel,
    post: ShoppingListUi
) {
    Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
        Surface(shape = RoundedCornerShape(8.dp), elevation = 4.dp) {
            Clickable(
                modifier = Modifier.ripple(),
                onClick = { sharedViewModel.pushBackStack(ScreenState.CurrentProductList(post)) }
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
                    Text(
                        text = post.shoppingListName,
                        modifier = Modifier.weight(1f).gravity(Alignment.CenterVertically)
                            .padding(8.dp)
                    )
                    Column(horizontalGravity = Alignment.End) {
                        IconButton(onClick = { sharedViewModel.updateShoppingList(post, true) }) {
                            Icon(
                                vectorResource(R.drawable.ic_archive_black_24dp),
                                Modifier.fillMaxSize()
                            )
                        }
                        Text(text = post.shoppingListTimestamp.getDateFormat())
                    }
                }
            }
        }
    }
}
