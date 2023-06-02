package com.example.fefu_fitnes_compose.Screens.ServicesPackage.Elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.FEFU_FITNES_COMPOSETheme
import com.example.fefu_fitnes_compose.ui.theme.Shapes
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import com.example.fefu_fitnes_compose.ui.theme.accentTextColor
import com.example.fefu_fitnes_compose.ui.theme.sportButtonColor
import com.example.fefu_fitnes_compose.ui.theme.standartTextColor


@Composable
fun ServicesClassSportCard(position:Int, scrollState: ScrollState, serviceName: String, serviceImage:Int, eventNameList:List<String>){

    var isClicked by remember { mutableStateOf(false) }
    var rotation by remember { mutableStateOf(-90f) }
    var cardHeight by remember { mutableStateOf(0) }
    val animateRotation by animateFloatAsState(targetValue = rotation)
    val interactionSource = remember { MutableInteractionSource() }

    rotation = if (isClicked) 90f else -90f

    FEFU_FITNES_COMPOSETheme() {
        Card(
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .padding(top = 14.dp)
                .shadow(1.dp, RoundedCornerShape(24.dp), true)
                .onGloballyPositioned {
                    cardHeight = it.size.height
                }
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(radius = 400.dp),
                    onClick = {
                        isClicked = !isClicked
//                        val cardPosition = position * (cardHeight + 14)
//                        val maxScroll = scrollState.maxValue - scrollState.
                    },
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Column(
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(126.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = serviceImage),
                    contentDescription = "pool_img"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = serviceName,
                        fontSize = 16.sp,
                        color = accentTextColor,
                    )
                    Image(
                        modifier = Modifier
                            .size(16.dp)
                            .rotate(animateRotation),
                        painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                        contentDescription = "arrow"
                    )
                }

                AnimatedVisibility(visible = isClicked) {
                    Column() {
                        for (name in eventNameList)
                            ButtonSportEvent(eventName = name)
                        Spacer(modifier = Modifier.height(18.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ButtonSportEvent(eventName: String){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = 18.dp)
            .padding(top = 10.dp),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = sportButtonColor,
            contentColor = standartTextColor
        )
    ) {
        Text(
            text = eventName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}