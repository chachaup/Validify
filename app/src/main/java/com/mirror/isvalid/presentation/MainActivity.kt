package com.mirror.isvalid.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mirror.isvalid.ui.theme.IsValidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsValidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = viewModel<RegistrationViewModel>()
                    val state = viewModel.state
                    val context = LocalContext.current
                    LaunchedEffect(key1 = context) {
                        viewModel.validationEventChannel.collect { event ->
                            when (event) {
                                is RegistrationViewModel.ValidationEvent.Success -> {
                                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = state.email, onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
                            },
                            isError = state.EmailError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Email")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )

                        if (state.EmailError != null) {
                            Text(
                                text = state.EmailError!!,
                                modifier = Modifier.align(Alignment.End),
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = state.phoneNumber, onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.PhoneNumberChanged(it))
                            },
                            isError = state.PhoneNumberError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Phone Number")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                        )

                        if (state.PhoneNumberError != null) {
                            Text(
                                text = state.PhoneNumberError!!,
                                modifier = Modifier.align(Alignment.End),
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = state.firstName, onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.FirstNameChanged(it))
                            },
                            isError = state.FirstNameError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "First Name")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        if (state.FirstNameError != null) {
                            Text(
                                text = state.FirstNameError!!,
                                modifier = Modifier.align(Alignment.End),
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = state.lastName, onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.LastNameChanged(it))
                            },
                            isError = state.LastNameError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "First Name")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        if (state.LastNameError != null) {
                            Text(
                                text = state.LastNameError!!,
                                modifier = Modifier.align(Alignment.End),
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = state.password, onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
                            },
                            isError = state.PasswordError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Password")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = PasswordVisualTransformation()
                        )


                        if (state.PasswordError != null) {
                            Text(
                                text = state.PasswordError!!,
                                modifier = Modifier.align(Alignment.End),
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = state.confirmPassword, onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.ConfirmPasswordChanged(it))
                            },
                            isError = state.ConfirmPasswordError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Confirm Password")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = PasswordVisualTransformation()
                        )


                        if (state.ConfirmPasswordError != null) {
                            Text(
                                text = state.ConfirmPasswordError!!,
                                modifier = Modifier.align(Alignment.End),
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                viewModel.onEvent(RegistrationFormEvent.Submit)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.End)
                                .height(50.dp)
                        ) {
                            Text(text = "Submit")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IsValidTheme {
        Greeting("Android")
    }
}