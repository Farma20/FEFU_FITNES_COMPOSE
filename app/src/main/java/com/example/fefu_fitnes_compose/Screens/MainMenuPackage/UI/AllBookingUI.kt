package com.example.fefu_fitnes_compose.Screens.MainMenuPackage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.ViewModel.MainMenuViewModel
import com.example.fefu_fitnes_compose.Screens.ScreenElements.NearEventCard
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight

@Composable
fun AllBookingUI(mainMenuViewModel: MainMenuViewModel = viewModel()) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UpBar()
            if(mainMenuViewModel.bookingEventData.isEmpty() || mainMenuViewModel.bookingEventData[0].eventId == null){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "У вас пока нет записей.")
                }
            }else{
                LazyColumn(){
                    items(mainMenuViewModel.bookingEventData.size){
                        NearEventCard(event = mainMenuViewModel.bookingEventData[it])
                    }
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }

            }
        }
    }
}


@Composable
private fun UpBar(){
    Surface(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .shadow(AppBarDefaults.TopAppBarElevation)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(BlueDark, BlueLight)
                    )
                )
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Все мои записи",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                
                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.burger_menu_pick),
                        contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
