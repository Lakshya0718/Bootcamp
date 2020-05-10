package com.examples.gallerio.categoryfuntion

import java.io.Serializable

data class AddCategoryModel(val catId: String?, val catImage: String?, val timestamp: Any?): Serializable {
    constructor(): this("", "", "")
}