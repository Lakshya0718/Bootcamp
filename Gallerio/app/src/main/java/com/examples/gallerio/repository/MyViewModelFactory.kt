package com.examples.gallerio.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.examples.gallerio.firebasefunction.FirebaseAuthViewModel
import com.examples.gallerio.firebasefunction.FirebaseCategoryViewModel
import com.examples.gallerio.firebasefunction.FirebaseImageViewModel

class MyViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirebaseImageViewModel::class.java)){
            return FirebaseImageViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(FirebaseAuthViewModel::class.java)){
            return FirebaseAuthViewModel(Repository()) as T
        } else if (modelClass.isAssignableFrom(FirebaseCategoryViewModel::class.java)){
            return FirebaseCategoryViewModel(Repository()) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}