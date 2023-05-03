package com.mirror.isvalid.presentation

data class FormValidationState(
    val phoneNumber: String = "",
    val PhoneNumberError: String? = null,
    val firstName: String = "",
    val FirstNameError: String? = null,
    val lastName: String = "",
    val LastNameError: String? = null,
    val email: String = "",
    val EmailError: String? = null,
    val password: String = "",
    val PasswordError: String? = null,
    val confirmPassword: String = "",
    val ConfirmPasswordError: String? = null,
)
//{
//    private val PhoneNumberError
//        get() = phoneNumber.length == 10
//    private val FirstNameError
//        get() = firstName.length >= 2
//    private val LastNameError
//        get() = lastName.length >= 2
//    private val EmailError
//        get() = email.contains("@") && email.contains(".")
//    private val PasswordError
//        get() = password.length >= 8
//    private val ConfirmPasswordError
//        get() = password == confirmPassword
//    val FormError =
//        PhoneNumberError &&
//                FirstNameError &&
//                LastNameError &&
//                EmailError &&
//                PasswordError &&
//                ConfirmPasswordError
//}
