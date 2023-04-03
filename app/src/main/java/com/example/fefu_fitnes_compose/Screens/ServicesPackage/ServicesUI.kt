package com.example.fefu_fitnes_compose.Screens.ServicesPackage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.example.fefu_fitnes_compose.R

@Composable
fun ServicesUI(){
//    Text(text = "ServicesUI")
    CollapsingEffectScreen()
}

@Composable
fun CollapsingEffectScreen() {
    val items = (1..100).map { "Item $it" }
    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0
    LazyColumn(
        Modifier.fillMaxSize(),
        lazyListState,
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.workout_photo_1),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .graphicsLayer {
                        scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                        translationY = scrolledY * 0.5f
                        previousOffset = lazyListState.firstVisibleItemScrollOffset
                    }
                    .height(240.dp)
                    .fillMaxWidth()
            )
        }
        items(items.count()) {
            Text(
                text = it.toString(),
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}