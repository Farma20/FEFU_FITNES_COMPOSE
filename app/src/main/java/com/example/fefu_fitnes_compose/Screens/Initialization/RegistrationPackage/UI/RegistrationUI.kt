package com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.UI

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.Initialization.TopBars.RegistrationTopBar
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Controllers.InitializationFormEvent
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.ViewModel.InitializationViewModel
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import java.time.LocalDate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegistrationUI() {
    Scaffold(
        topBar = {RegistrationTopBar(text = "Регистрация")}
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val spacerPadding = 20.dp
            RegistrationLogin()
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationPhone()
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationEmail()
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationGender()
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationBirthday()
            Spacer(modifier = Modifier.height(spacerPadding-10.dp))
            RegistrationPassword()
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationRepeatPassword()
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationCheckBox()
            Spacer(modifier = Modifier.height(60.dp))
            RegistrationSubmitButton()
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
private fun RegistrationInput(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val initializationViewModel = viewModel<InitializationViewModel>()
        val state = initializationViewModel.state
        val context = LocalContext.current

        LaunchedEffect(key1 = context ){
            initializationViewModel.validationEvents.collect{ event->
                when(event){
                    is InitializationViewModel.ValidationEvent.Success ->{
                        Toast.makeText(
                            context,
                            "Вход произведен успешно",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.email,
            onValueChange = {
                initializationViewModel.onEvent(InitializationFormEvent.EmailChanged(it))
            },
            isError = state.emailError != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            placeholder = {
                Text(
                    text = "Почта",
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.registration_email),
                    contentDescription = null,
                )
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
        )
        if(state.emailError != null){
            Text(
                text = state.emailError!!,
                color = MaterialTheme.colors.error
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.password,
            onValueChange = {
                initializationViewModel.onEvent(InitializationFormEvent.PasswordChanged(it))
            },
            isError = state.passwordError != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            placeholder = {
                Text(text = "Пароль")
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.registration_password),
                    contentDescription = null,
                )
            },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
        )
        if(state.passwordError != null){
            Text(
                text = state.passwordError!!,
                color = MaterialTheme.colors.error
            )
        }
        Spacer(modifier = Modifier.height(45.dp))

        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(16.dp)),
            onClick = {
                initializationViewModel.onEvent(InitializationFormEvent.Submit)
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor =  Yellow,
                contentColor = Color.White
            ),
        ) {
            Text(
                text = "Войти",
                fontSize = 19.sp
            )
        }
    }
}

@Composable
private fun RegistrationLogin(){
    TextField(
        modifier = Modifier.fillMaxWidth(0.9f),
        value = "",
        onValueChange = {

        },
        isError = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        placeholder = {
            Row() {
                Text(
                    text = "Логин",
                )
                Text(
                    text = "*",
                    color = Color.Red
                )
            }
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.registration_user),
                contentDescription = null,
            )
        },
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
    )
}

@Composable
private fun RegistrationPhone(){
    TextField(
        modifier = Modifier.fillMaxWidth(0.9f),
        value = "",
        onValueChange = {

        },
        isError = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone
        ),
        placeholder = {
            Row() {
                Text(
                    text = "Номер телефона",
                )
                Text(
                    text = "*",
                    color = Color.Red
                )
            }
        },
        leadingIcon = {
            Image(
                modifier = Modifier
                    .width(25.dp)
                    .height(27.dp),
                painter = painterResource(id = R.drawable.phone_icon),
                contentDescription = null,
            )
        },
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
    )
}

@Composable
private fun RegistrationEmail(){
    TextField(
        modifier = Modifier.fillMaxWidth(0.9f),
        value = "",
        onValueChange = {

        },
        isError = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        placeholder = {
            Row() {
                Text(
                    text = "Почта",
                )
                Text(
                    text = "*",
                    color = Color.Red
                )
            }
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.registration_email),
                contentDescription = null,
            )
        },
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
    )
}

@Composable
private fun RegistrationGender(){
    val state = remember { mutableStateOf(true) }
    Row(
        modifier = Modifier.fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Пол",
            fontWeight = FontWeight.Light,
            fontSize = 16.sp
        )
        Row(modifier = Modifier
            .selectableGroup()){
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = BlueLight
                    ),
                    selected = state.value,
                    onClick = { state.value = true },
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
                    selected = !state.value,
                    onClick = { state.value = false },
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
private fun RegistrationBirthday(){
    val dateText = remember { mutableStateOf("Выберите дату рождения") }
    val context = LocalContext.current

    val currentDate = LocalDate.now()

    val year:Int = currentDate.year
    val month: Int = currentDate.month.value
    val day:Int = currentDate.dayOfMonth

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            dateText.value = "$day.$month.$year"
        },year, month, day
    )

    Row(
        modifier = Modifier.fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Дата рождения",
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )
        TextButton(
            modifier = Modifier.fillMaxWidth(0.9f),
            onClick = {
                datePickerDialog.show()
            },
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.Black
            )
        ) {
            Text(
                text = dateText.value,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun RegistrationPassword(){
    TextField(
        modifier = Modifier.fillMaxWidth(0.9f),
        value = "",
        onValueChange = {

        },
        isError = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        placeholder = {
            Row() {
                Text(
                    text = "Пароль",
                )
                Text(
                    text = "*",
                    color = Color.Red
                )
            }
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.registration_password),
                contentDescription = null,
            )
        },
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
    )
}

@Composable
private fun RegistrationRepeatPassword(){
    TextField(
        modifier = Modifier.fillMaxWidth(0.9f),
        value = "",
        onValueChange = {

        },
        isError = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        placeholder = {
            Row() {
                Text(
                    text = "Повторите пароль",
                )
                Text(
                    text = "*",
                    color = Color.Red
                )
            }
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.registration_password),
                contentDescription = null,
            )
        },
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
    )
}

@Composable
private fun RegistrationCheckBox(){
    val checkedState = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            colors = CheckboxDefaults.colors(
                uncheckedColor = BlueLight,
                checkedColor = Yellow
            ),
            checked = checkedState.value,
            onCheckedChange = {checkedState.value = it},
        )
        Text(
            "Я согласен на обработку персональных даннных*",
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun RegistrationSubmitButton(){
    Button(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(16.dp)),
        onClick = {

        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor =  Yellow,
            contentColor = Color.White
        ),
    ) {
        Text(
            text = "Зарегистрироваться",
            fontSize = 19.sp
        )
    }
}

