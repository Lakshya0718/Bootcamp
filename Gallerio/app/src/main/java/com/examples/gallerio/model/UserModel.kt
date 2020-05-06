package com.examples.gallerio.model

import androidx.lifecycle.LiveData

data class UserModel(val name: String?, val email: String?, val profileImageUrl: String?){

    constructor() : this("", "", "")

}