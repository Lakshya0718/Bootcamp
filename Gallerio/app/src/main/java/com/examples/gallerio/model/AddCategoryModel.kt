package com.examples.gallerio.model

import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable

data class AddCategoryModel(val catId: String?, val catImage: String?, val timestamp: Any?): Serializable {
    constructor(): this("", "", "")
}