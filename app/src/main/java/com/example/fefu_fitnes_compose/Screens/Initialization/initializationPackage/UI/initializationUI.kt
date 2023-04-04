package com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.Initialization.TopBars.MainTopBar
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Controllers.InitializationFormEvent
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.ViewModel.InitializationViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InitializationUI() {
    Scaffold(topBar = { MainTopBar(text = "Вход") },){
        Column(
            modifier = Modifier.padding(top = 60.dp)
        ) {
            Label()
            InitializationInput()
        }
    }
}

@Composable
private fun Label(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(){
            Image(
                painter = painterResource(id = R.drawable.registration_fefu_label_one),
                contentDescription = null
            )
            Image(
                painter = painterResource(id = R.drawable.registration_fefu_label_two),
                contentDescription = null,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun InitializationInput(){
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
                Text(text = "Почта")
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
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                initializationViewModel.onEvent(InitializationFormEvent.Submit)
            },
        ) {
            Text(text = "Войти")
        }
    }
}