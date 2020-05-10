package com.examples.gallerio.firebasefunction

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.examples.gallerio.repository.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot

class FirebaseAuthViewModel(repository: Repository) : ViewModel() {

    private val TAG = "FIRESTORE_AUTH_VIEW_MODEL"

    private val repository: Repository =
        Repository()

    fun login(email: String, password: String): Task<AuthResult> {
        return repository.login(email, password)
    }

    fun signin(email: String, password: String, name: String) {
        repository.sigin(email, password, name)
    }

    fun loadUserData(): Task<DocumentSnapshot>{
        return repository.loadUserData()
    }

    fun addProfileImage(profileImage: Uri){
        repository.addProfileImage(profileImage)
    }

    fun logout(){
        repository.logout()
    }

}