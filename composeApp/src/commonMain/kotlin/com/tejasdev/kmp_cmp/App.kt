package com.tejasdev.kmp_cmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import com.tejasdev.kmp_cmp.data.Product
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import multiplatform.network.cmptoast.showToast

@Composable
@Preview
fun App() {
    MaterialTheme {
        Home(
            viewModel = ProductViewModel()
        )
    }
}


@Composable
fun Home(
    viewModel: ProductViewModel
){
   val products = viewModel.products.collectAsState()

    showToast(
        message = "size: ${products.value.size}"
    )

   BoxWithConstraints {
       val scope = this
       val maxWidth = scope.maxWidth
       val minWidth = scope.minWidth

       var cols = 2
       var modifier = Modifier.fillMaxWidth()
       if(maxWidth > 840.dp) {
           cols = 3
           modifier = Modifier.widthIn(max = 1080.dp)
       }

       val scrollState = rememberLazyGridState()
       Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.fillMaxWidth()
       ) {
           LazyVerticalGrid(
               columns = GridCells.Fixed(cols),
               state = scrollState,
               contentPadding = PaddingValues(16.dp)
           ) {
               items(items = products.value, key = {it.id}) {
                   ProductCard(it)
               }
           }
       }
   }
}

@Composable
fun ProductCard(
    item: Product
){
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth(),
        elevation = 8.dp
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val painter = rememberImagePainter(
                url = item.image
            )
            Image(
                painter = painter,
                contentDescription = item.title,
                modifier = Modifier.height(130.dp)
            )
            Text(
                text = item.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}