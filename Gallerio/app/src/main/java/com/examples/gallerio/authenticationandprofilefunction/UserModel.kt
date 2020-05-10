package com.examples.gallerio.authenticationandprofilefunction

data class UserModel(val name: String?, val email: String?, val profileImageUrl: String){

    constructor() : this("", "", "")

}
