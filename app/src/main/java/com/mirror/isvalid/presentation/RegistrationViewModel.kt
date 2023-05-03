package com.mirror.isvalid.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirror.isvalid.domain.use_case.ValidateEmail
import com.mirror.isvalid.domain.use_case.ValidateFirstName
import com.mirror.isvalid.domain.use_case.ValidateLastName
import com.mirror.isvalid.domain.use_case.ValidatePassword
import com.mirror.isvalid.domain.use_case.ValidatePhoneNumber
import com.mirror.isvalid.domain.use_case.ValidateRepeatedPassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val validatePhoneNumber: ValidatePhoneNumber = ValidatePhoneNumber(),
    private val validateFirstName: ValidateFirstName = ValidateFirstName(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateConfirmPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
): ViewModel() {

    var state by mutableStateOf(FormValidationState())

    private val _validationEventChannel = Channel<ValidationEvent>()
    val validationEventChannel = _validationEventChannel.receiveAsFlow()


    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.PhoneNumberChanged -> {
                state = state.copy(phoneNumber = event.phoneNumber)
            }

            is RegistrationFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }

            is RegistrationFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }

            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is RegistrationFormEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPassword = event.confirmPassword)
            }

            is RegistrationFormEvent.Submit -> {
                submitData()

            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val firstNameResult = validateFirstName.execute(state.firstName)
        val lastNameResult = validateLastName.execute(state.lastName)
        val phoneNumberResult = validatePhoneNumber.execute(state.phoneNumber)
        val passwordResult = validatePassword.execute(state.password)
        val confirmPasswordResult = validateConfirmPassword.execute(state.password, state.confirmPassword)

        val hasError = listOf(
            emailResult,
            firstNameResult,
            lastNameResult,
            phoneNumberResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.first }

        if (hasError){
            state = state.copy(
                EmailError = emailResult.second,
                FirstNameError = firstNameResult.second,
                LastNameError = lastNameResult.second,
                PhoneNumberError = phoneNumberResult.second,
                PasswordError = passwordResult.second,
                ConfirmPasswordError = confirmPasswordResult.second,
            )
            return
        }

        viewModelScope.launch {
            _validationEventChannel.send(ValidationEvent.Success)
        }

    }

    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }

}