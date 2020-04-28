package com.examples.gallerio

import androidx.lifecycle.LiveData

data class UserModel(val name: String?, val email: String?, val mobileno: String?){

    constructor() : this("", "", "")

}