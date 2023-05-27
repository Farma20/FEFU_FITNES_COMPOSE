package com.example.fefu_fitnes_compose.Screens.QrScannerPackage.UI

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.RegistrationFromStateModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.ViewModel.RegistrationViewModel
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Controllers.SubmitUserEvent
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.ViewModel.QrViewModel
import com.example.fefu_fitnes_compose.Screens.ScreenElements.Animation.LoadingAnimation
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SubmitUserDialog(openDialog: MutableState<Boolean>, qrViewModel: QrViewModel) {
    val registrationViewModel = viewModel<RegistrationViewModel>()
    val state = registrationViewModel.state
    val context = LocalContext.current

    Dialog(onDismissRequest = { openDialog.value = false }) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp)
                .clip(
                    RoundedCornerShape(7.dp)
                ),
            elevation = 3.dp
        ) {
            if (qrViewModel.qrUserDataFool.value == null){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.54f)
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    LoadingAnimation(
                        circleSize = 8.dp,
                        circleColor = BlueLight,
                        spaceBetween = 4.dp,
                        travelDistance = 6.dp
                    )
                }
            }else{
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LaunchedEffect(key1 = context){
                        registrationViewModel.validationEvents.collect{ event ->
                            when(event){
                                is RegistrationViewModel.ValidationEvent.Success->{

                                }
                            }
                        }
                    }

                    val spacerPadding = 20.dp

//                Box(modifier = Modifier.fillMaxWidth()){
//                    Text(
//                        modifier = Modifier
//                            .padding(top = 8.dp, start = 16.dp, bottom = 16.dp),
//                        text = "Данные пользователя",
//                        fontSize = 20.sp,
//                    )
//                }
                    Image(
                        modifier = Modifier
                            .size(140.dp)
                            .padding(horizontal = 6.dp),
                        painter = painterResource(id = R.drawable.baseline_account_circle_24),
                        contentDescription = "userImg"
                    )
                    RegistrationSecondName(state, qrViewModel)
                    Spacer(modifier = Modifier.height(spacerPadding))
                    RegistrationFirstName(state, qrViewModel)
                    Spacer(modifier = Modifier.height(spacerPadding))
                    RegistrationMiddleName(state, qrViewModel)
                    Spacer(modifier = Modifier.height(spacerPadding))
                    RegistrationPhone(state, qrViewModel)
                    Spacer(modifier = Modifier.height(spacerPadding))
                    RegistrationEmail(state, qrViewModel)
                    Spacer(modifier = Modifier.height(spacerPadding))
                    RegistrationTelegramID(state, qrViewModel)
                    Spacer(modifier = Modifier.height(spacerPadding))
                    RegistrationBirthday(state, qrViewModel, context)
                    Spacer(modifier = Modifier.height(spacerPadding))
                    RegistrationGender(state, qrViewModel)
                    Spacer(modifier = Modifier.height(spacerPadding))
                    RegistrationStatus(state, qrViewModel)
                    Spacer(modifier = Modifier.height(40.dp))
                    RegistrationSubmitButton(qrViewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


@Composable
private fun RegistrationSecondName(state: RegistrationFromStateModel, viewModel: QrViewModel){
    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = viewModel.qrUserDataFool.value!!.secondName,
            onValueChange = {
                viewModel.onEvent(SubmitUserEvent.SecondNameChanged(it))
            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = {
                Row() {
                    Text(
                        text = "Фамилия",
                    )
                }
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
        )
    }
}

@Composable
private fun RegistrationFirstName(state: RegistrationFromStateModel, viewModel: QrViewModel){
    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = viewModel.qrUserDataFool.value!!.firstName,
            onValueChange = {
                viewModel.onEvent(SubmitUserEvent.FirstNameChanged(it))            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = {
                Row() {
                    Text(
                        text = "Имя",
                    )
                }
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
        )
    }
}

@Composable
private fun RegistrationMiddleName(state: RegistrationFromStateModel, viewModel: QrViewModel){
    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = viewModel.qrUserDataFool.value!!.thirdName,
            onValueChange = {
                viewModel.onEvent(SubmitUserEvent.MiddleNameChanged(it))            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = {
                Row() {
                    Text(
                        text = "Отчество",
                    )
                }
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
        )
    }
}



@Composable
private fun RegistrationPhone(state: RegistrationFromStateModel, viewModel: QrViewModel){
    Column() {
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = if(viewModel.qrUserDataFool.value!!.phoneNumber == null)
                ""
            else
                viewModel.qrUserDataFool.value!!.phoneNumber!!,
            onValueChange = {
                viewModel.onEvent(SubmitUserEvent.PhoneChanged(it)) },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            label = {
                Row() {
                    Text(
                        text = "Номер телефона",
                    )
                }
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
        )
    }
}

@Composable
private fun RegistrationEmail(state: RegistrationFromStateModel, viewModel: QrViewModel){
    Column() {
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = viewModel.qrUserDataFool.value!!.email,
            onValueChange = {
                viewModel.onEvent(SubmitUserEvent.EmailChanged(it))            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            label = {
                Row() {
                    Text(
                        text = "Почта",
                    )
                }
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
        )
    }

}

@Composable
private fun RegistrationTelegramID(state: RegistrationFromStateModel, viewModel: QrViewModel){
    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = if(viewModel.qrUserDataFool.value!!.telegramId == null)
                ""
            else
                viewModel.qrUserDataFool.value!!.telegramId!!,
            onValueChange = {
                viewModel.onEvent(SubmitUserEvent.TelegramId(it))            },
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            label = {
                Row() {
                    Text(
                        text = "Telegram",
                    )
                }
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
        )
    }
}

@Composable
private fun RegistrationGender(state: RegistrationFromStateModel, viewModel: QrViewModel){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ){
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "Пол",
            fontWeight = FontWeight.Light,
            fontSize = 16.sp
        )
        Column(modifier = Modifier
            .padding(start = 6.dp)
            .selectableGroup()){

            val gender = viewModel.qrUserDataFool.value!!.gender == "m"

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = BlueLight
                    ),
                    selected = gender,
                    onClick = {
                        viewModel.onEvent(SubmitUserEvent.GenderChanged("m"))
                    },
                )
                Text(
                    text = "Мужской",
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = BlueLight
                    ),
                    selected = !gender,
                    onClick = {
                        viewModel.onEvent(SubmitUserEvent.GenderChanged("f"))
                    },
                )
                Text(
                    text = "Женский",
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun RegistrationStatus(state: RegistrationFromStateModel, viewModel: QrViewModel){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ){
        val status = viewModel.qrUserDataFool.value!!.status
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "Статус",
            fontWeight = FontWeight.Light,
            fontSize = 16.sp
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 5.dp, end = 20.dp)
            .selectableGroup(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = BlueLight
                    ),
                    selected = status == "student",
                    onClick = {
                        viewModel.onEvent(SubmitUserEvent.StatusChanged("student"))
                      },
                )
                Text(
                    text = "Студент",
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = BlueLight
                    ),
                    selected = status == "guest",
                    onClick = {
                        viewModel.onEvent(SubmitUserEvent.StatusChanged("guest"))                    },
                )
                Text(
                    text = "Гость",
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = BlueLight
                    ),
                    selected = status == "employee",
                    onClick = {
                        viewModel.onEvent(SubmitUserEvent.StatusChanged("employee"))                    },
                )
                Text(
                    text = "Сотрудник",
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun RegistrationBirthday(state: RegistrationFromStateModel, viewModel: QrViewModel, context: Context){
    val currentDate = LocalDate.now()

    val year:Int = currentDate.year
    val month: Int = currentDate.month.value-1
    val day:Int = currentDate.dayOfMonth

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            val myDay = if(day.toString().length == 1){
                "0${day}"
            }else{
                day.toString()
            }

            val myMonth = if(month.toString().length == 1){
                "0${month+1}"
            }else{
                month.toString()
            }
//            viewModel.onEvent(SubmitUserEvent.BirthdayChanged("$myDay.$myMonth.$year"))
        },year, month, day
    )

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Дата рождения:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                elevation = 3.dp
            ) {
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(32.dp),
                    onClick = {
                        datePickerDialog.show()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = BlueDark
                    )
                ) {
                    Text(
                        text =  viewModel.qrUserDataFool.value!!.birthdate,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}



@Composable
private fun RegistrationSubmitButton(viewModel: QrViewModel){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        onClick = {
            if(!viewModel.qrUserDataFool.value!!.verified){
                viewModel.qrUserDataFool.value!!.verified = true
                MainRepository.conformUserInServer(viewModel.qrUserDataFool.value!!)
            }else{
                viewModel.qrUserDataFool.value!!.verified = false
                MainRepository.conformUserInServer(viewModel.qrUserDataFool.value!!)
            }
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor =  if (!viewModel.qrUserDataFool.value!!.verified)Yellow else Color.Red,
            contentColor = Color.White
        ),
    ) {
        Text(
            text = if (!viewModel.qrUserDataFool.value!!.verified)"Верифицировать пользователя" else "Отменить верификацию",
        )
    }
}
