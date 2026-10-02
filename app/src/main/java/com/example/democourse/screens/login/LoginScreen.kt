package com.example.democourse.screens.login

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen (viewModel: LoginViewModel = viewModel(), onLoginSuccess:()-> Unit ){
    Scaffold (){ innerPadding ->
        Column (modifier = Modifier.fillMaxSize().padding(innerPadding),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
            ){
            val state by viewModel.currentState.collectAsState()
            LaunchedEffect(state.isLoggedIn) {
                if (state.isLoggedIn) {
                    onLoginSuccess()
                }
            }

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text("Welcome,", style = MaterialTheme.typography.headlineLarge)
                Text("Let's start your journey,", style = MaterialTheme.typography.bodyLarge)
                OutlinedTextField(
                    value = state.email,
                    onValueChange = viewModel::onEmailChange,
                    isError = state.emailError!=null,
                    label = { Text("Email") },
                    supportingText = { state.emailError?.let { it -> Text(it) } },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                    )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = viewModel::onPasswordChange,
                    visualTransformation = PasswordVisualTransformation(),
                    isError = state.passwordError!=null,
                    label = { Text("Password") },
                    supportingText = { state.passwordError?.let { it -> Text(it) } },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                )
                state.errorMessage?.let {
                    Text(state.errorMessage!!, style = MaterialTheme.typography.labelMedium.copy(
                        Color.Red))

                }
                ElevatedButton(onClick = viewModel::onLogin,
                    enabled = !state.loading,
                    modifier = Modifier.fillMaxWidth()
                    ) {
                    if(state.loading) {
                        CircularProgressIndicator()
                    } else {
                        Text("Login")
                    }

                }
            }

        }
    }
}