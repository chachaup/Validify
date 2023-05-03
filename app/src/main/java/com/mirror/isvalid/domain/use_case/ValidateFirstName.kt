package com.mirror.isvalid.domain.use_case

class ValidateFirstName {

    fun execute(name: String): Pair<Boolean, String?> {
        if (name.isBlank()){
            return Pair(false, "The name can't be blank")
        }
        return Pair(true, null)
    }
}