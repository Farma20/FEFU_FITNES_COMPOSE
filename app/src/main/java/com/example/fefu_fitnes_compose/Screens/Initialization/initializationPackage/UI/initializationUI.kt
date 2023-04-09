package com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.Initialization.Navigation.Screen
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.UI.RegistrationUI
import com.example.fefu_fitnes_compose.Screens.Initialization.TopBars.MainTopBar
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Controllers.InitializationFormEvent
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.ViewModel.InitializationViewModel
import com.example.fefu_fitnes_compose.ui.theme.BlueURL
import com.example.fefu_fitnes_compose.ui.theme.Yellow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InitializationUI(navController: NavController, successInitialization: MutableState<Boolean>) {
    Scaffold(topBar = { MainTopBar(text = "") },){
        Column(
            modifier = Modifier.padding(top = 60.dp)
        ) {
            Label()
            InitializationInput(successInitialization)
            Spacer(modifier = Modifier.height(60.dp))
            RegistrationURL(navController)
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
private fun InitializationInput(successInitialization: MutableState<Boolean>){
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
                        successInitialization.value = true
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

        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if(passwordVisibility){
            painterResource(id = R.drawable.baseline_visibility_off_24)
        }else{
            painterResource(id = R.drawable.baseline_visibility_24)
        }

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
private fun RegistrationURL(navController: NavController){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            text = "Нет аккаунта? "
        )
        Text(
            modifier = Modifier.clickable {
                navController.navigate(Screen.RegistrationScreen.route)
            },
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            text = "Зарегистрируйтесь!",
            color = BlueURL
        )
    }
}