package com.artf.shoppinglistcompose.ui.view.layout.currentList

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.FloatingActionButton
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar
import androidx.ui.material.contentColorFor
import androidx.ui.res.stringResource
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.artf.shoppinglistcompose.data.status.ResultStatus
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.ScreenStateAmbient
import com.artf.shoppinglistcompose.ui.model.SharedViewModelAmbient
import com.artf.shoppinglistcompose.ui.model.model.ProductUi
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentProductListModel.showDialogState
import com.artf.shoppinglistcompose.ui.view.layout.EmptyScreen

@Composable
fun ProductListCurrentScreen(
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    val sharedViewModel = SharedViewModelAmbient.current
    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { sharedViewModel.popBackStack() }) {
                        Icon(vectorResource(R.drawable.ic_baseline_arrow_back_24))
                    }
                }
            )
        },
        bodyContent = {
            ScreenBody()
            CreateProductDialog()
        },
        floatingActionButton = { Fab() }
    )
}

@Composable
private fun ScreenBody() {
    when (val result = ScreenStateAmbient.current.productListUi) {
        is ResultStatus.Loading -> LoadingScreen()
        is ResultStatus.Success -> SuccessScreen(result.data)
        is ResultStatus.Error -> ErrorScreen()
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        gravity = ContentGravity.Center
    ) { CircularProgressIndicator() }
}

@Composable
private fun SuccessScreen(productList: List<ProductUi>) {
    val sharedViewModel = SharedViewModelAmbient.current
    VerticalScroller {
        Column(Modifier.fillMaxWidth().padding(8.dp, 8.dp, 8.dp, 96.dp)) {
            productList.forEach { post -> ProductCurrentItem(sharedViewModel, post) }
        }
    }
}

@Composable
private fun ErrorScreen() {
    EmptyScreen(
        R.string.empty_view_product_list_title,
        R.string.empty_view_product_list_subtitle_text
    )
}

@Composable
private fun Fab() {
    FloatingActionButton(
        onClick = { showDialogState = true },
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = contentColorFor(MaterialTheme.colors.onSecondary),
        elevation = 6.dp,
        icon = { Icon(vectorResource(R.drawable.ic_add_black_24dp)) }
    )
}