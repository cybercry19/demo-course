package com.example.democourse.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
  private val _state = MutableStateFlow(LoginState())
  val currentState = _state.asStateFlow()

  fun onEmailChange(email:String){
     _state.value = _state.value.copy(email = email,
         emailError = null,
         errorMessage = null
         )
  }

   fun  onPasswordChange(password:String){
       _state.value = _state.value.copy(
           password=password,
           passwordError = null,
           errorMessage = null
           )
   }

   fun onLogin(){
      viewModelScope.launch {
          val  state = _state.value

          if(validate()){
              _state.value = state.copy(loading = true)
              delay(2000)
              if (state.email == "admin@gmail.com" && state.password == "123456"){
                  _state.value = state.copy(isLoggedIn = true, loading = false)
              }
              else{
                  _state.value =  state.copy(errorMessage = "Email or password is not correct", loading = false)
              }
          }
      }


  }


    private suspend  fun validate():Boolean{
        val state = _state.value

        val emailError = when{
            state.email.isBlank() -> "Email is required"

            !android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> "Enter valid email"

            else -> null
        }

        val passwordError = when{
            state.password.isBlank() -> "Password required"

            state.password.length<6 -> "Password must be at least 6 characters"

            else -> null
        }

        _state.value = _state.value.copy(emailError = emailError, passwordError = passwordError)

        return  emailError==null&& passwordError==null
    }
}