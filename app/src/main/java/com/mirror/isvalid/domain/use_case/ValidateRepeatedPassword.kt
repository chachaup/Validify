package com.mirror.isvalid.domain.use_case

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): Pair<Boolean, String?> {
        if (password != repeatedPassword){
            return Pair(false, "The passwords don't match")
        }

        return Pair(true, null)
    }
}