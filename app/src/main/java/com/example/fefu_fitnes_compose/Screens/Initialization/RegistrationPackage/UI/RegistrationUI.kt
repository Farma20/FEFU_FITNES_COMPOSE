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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Controllers.RegistrationFormEvent
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.RegistrationFromStateModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.ViewModel.RegistrationViewModel
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

    val registrationViewModel = viewModel<RegistrationViewModel>()
    val state = registrationViewModel.state
    val context = LocalContext.current

    Scaffold(
        topBar = {RegistrationTopBar(text = "Регистрация")}
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LaunchedEffect(key1 = context){
                registrationViewModel.validationEvents.collect{ event ->
                    when(event){
                        is RegistrationViewModel.ValidationEvent.Success->{
                            Toast.makeText(
                                context,
                                "Регистрация произедена успешно",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

            val spacerPadding = 20.dp
            RegistrationLogin(state, registrationViewModel)
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationPhone(state, registrationViewModel)
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationEmail(state, registrationViewModel)
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationGender(state, registrationViewModel)
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationBirthday(state, registrationViewModel, context)
            Spacer(modifier = Modifier.height(spacerPadding-10.dp))
            RegistrationPassword(state, registrationViewModel)
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationRepeatPassword(state, registrationViewModel)
            Spacer(modifier = Modifier.height(spacerPadding))
            RegistrationCheckBox(state, registrationViewModel)
            Spacer(modifier = Modifier.height(40.dp))
            RegistrationSubmitButton(registrationViewModel)
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}


@Composable
private fun RegistrationLogin(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
    Column(){
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.login,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.LoginChanged(it))
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
        if(state.loginError != null){
            Text(
                text = state.loginError!!,
                color = MaterialTheme.colors.error
            )
        }
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
        if(state.phoneError != null){
            Text(
                text = state.phoneError!!,
                color = MaterialTheme.colors.error
            )
        }
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
        if(state.emailError != null){
            Text(
                text = state.emailError!!,
                color = MaterialTheme.colors.error
            )
        }
    }

}

@Composable
private fun RegistrationGender(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
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
private fun RegistrationBirthday(state: RegistrationFromStateModel, viewModel: RegistrationViewModel, context: Context){
    val currentDate = LocalDate.now()

    val year:Int = currentDate.year
    val month: Int = currentDate.month.value
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
                "0${month}"
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
                    text = if(state.birthday.isEmpty()) "Выберите дату рождения" else state.birthday,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                )
            }
        }
        if(state.birthdayError != null){
            Text(
                text = state.birthdayError!!,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Composable
private fun RegistrationPassword(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){

    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if(passwordVisibility){
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }else{
        painterResource(id = R.drawable.baseline_visibility_24)
    }
    Column() {
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.password,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
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
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = icon,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
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
    }
}

@Composable
private fun RegistrationRepeatPassword(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if(passwordVisibility){
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }else{
        painterResource(id = R.drawable.baseline_visibility_24)
    }
    Column() {
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = state.repeatPassword,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.RepeatPasswordChanged(it))
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
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = icon,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
        )
        if(state.repeatPasswordError != null){
            Text(
                text = state.repeatPasswordError!!,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Composable
private fun RegistrationCheckBox(state: RegistrationFromStateModel, viewModel: RegistrationViewModel){
    Column(){
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
                checked = state.terms,
                onCheckedChange = {
                    viewModel.onEvent(RegistrationFormEvent.TermsChanged(!state.terms))
                },
            )
            Text(
                "Я согласен на обработку персональных даннных*",
                fontWeight = FontWeight.Light
            )
        }
        if(state.termsError != null){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp),
                text = state.termsError!!,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Composable
private fun RegistrationSubmitButton(viewModel: RegistrationViewModel){
    Button(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(16.dp)),
        onClick = {
            viewModel.onEvent(RegistrationFormEvent.Submit)
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

