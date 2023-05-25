package com.example.fefu_fitnes_compose.Screens.ScreenElements

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Controllers.RegistrationFormEvent
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.RegistrationFromStateModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.ViewModel.RegistrationViewModel
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import java.time.LocalDate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SubmitUserDialog(openDialog: MutableState<Boolean>) {
    val registrationViewModel = viewModel<RegistrationViewModel>()
    val state = registrationViewModel.state
    val context = LocalContext.current

    Dialog(onDismissRequest = { openDialog.value = false }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .clip(
                    RoundedCornerShape(7.dp)
                ),
            elevation = 3.dp
        ) {

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
                RegistrationSecondName(state, registrationViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationFirstName(state, registrationViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationMiddleName(state, registrationViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationPhone(state, registrationViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationEmail(state, registrationViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationGender(state, registrationViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationStatus(state, registrationViewModel)
                Spacer(modifier = Modifier.height(spacerPadding))
                RegistrationBirthday(state, registrationViewModel, context)
                Spacer(modifier = Modifier.height(40.dp))
                RegistrationSubmitButton(registrationViewModel)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
private fun RegistrationSecondName(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.secondName,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.SecondNameChanged(it))
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
private fun RegistrationFirstName(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.firstName,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.FirstNameChanged(it))
            },
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
private fun RegistrationMiddleName(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.middleName,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.MiddleNameChanged(it))
            },
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
private fun RegistrationPhone(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
    Column() {
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.phone,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.PhoneChanged(it))
            },
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
private fun RegistrationEmail(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
    Column() {
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.email,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
            },
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
private fun RegistrationGender(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = BlueLight
                    ),
                    selected = state.gender,
                    onClick = {
                        viewModel.onEvent(RegistrationFormEvent.GenderChanged(true))
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
                    selected = !state.gender,
                    onClick = {
                        viewModel.onEvent(RegistrationFormEvent.GenderChanged(false))
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
private fun RegistrationStatus(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
    Column(
        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
    ){
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
                    selected = state.status == "student",
                    onClick = {
                        viewModel.onEvent(RegistrationFormEvent.StatusChanged("student"))
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
                    selected = state.status == "guest",
                    onClick = {
                        viewModel.onEvent(RegistrationFormEvent.StatusChanged("guest"))
                    },
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
                    selected = state.status == "employee",
                    onClick = {
                        viewModel.onEvent(RegistrationFormEvent.StatusChanged("employee"))
                    },
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
private fun RegistrationBirthday(state: RegistrationFromStateModel, viewModel: RegistrationViewModel, context: Context){
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

            viewModel.onEvent(RegistrationFormEvent.BirthdayChanged("$myDay.$myMonth.$year"))
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
                        text = if(state.birthday.isEmpty()) "Дата" else state.birthday,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}



@Composable
private fun RegistrationSubmitButton(viewModel: RegistrationViewModel){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        onClick = {
            viewModel.onEvent(RegistrationFormEvent.Submit)
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor =  Yellow,
            contentColor = Color.White
        ),
    ) {
        Text(
            text = "Подтвердить пользователя",
        )
    }
}