package com.example.fefu_fitnes_compose.Screens.ProfilePackage

import android.service.autofill.UserData
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Repository.DataBaseRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.UI.Calendar
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import java.time.LocalDate


@Composable
fun ProfileUI(){
    UpBar()
}


@Composable
private fun UpBar(){
    val userData by remember {
        mutableStateOf(MainRepository.currentUser)
    }

    val openDialog = remember {
        mutableStateOf(false)
    }
    
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
                    text = "${userData.value!!.firstName} ${userData.value!!.secondName}",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                
                if (openDialog.value)
                    ExitDialog(openDialog = openDialog)
                
                IconButton(
                    onClick = {openDialog.value = !openDialog.value},
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_logout_24),
                        contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ExitDialog(openDialog: MutableState<Boolean>){
    Dialog(
        onDismissRequest = {openDialog.value = false}
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(7.dp))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Image(
                        modifier = Modifier.width(50.dp).padding(end = 4.dp),
                        painter = painterResource(id = R.drawable.fefu_label),
                        contentDescription = null)
                    Image(
                        modifier = Modifier.padding(bottom = 2.dp),
                        painter = painterResource(id = R.drawable.registration_fefu_label_two),
                        contentDescription = null)
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                    ,
                    text = "Вы уверены, что хотите выйти из профиля?",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {openDialog.value = false},
                        modifier = Modifier.width(120.dp),
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor =  BlueDark ,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Отмена")
                    }
                    Button(
                        onClick = {
                          DataBaseRepository.get().getAllUserData().observeForever(){
                              DataBaseRepository.get().deleteUserData(it[0])
                              RegisterRepository.userToken = ""
                              RegisterRepository.qrToken = ""
                              RegisterRepository.userType = ""
                              RegisterRepository.userInit = false
                          }
                        },
                        modifier = Modifier.width(120.dp),
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor =  BlueDark ,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Выйти")
                    }
                }
            }
        }
    }
}