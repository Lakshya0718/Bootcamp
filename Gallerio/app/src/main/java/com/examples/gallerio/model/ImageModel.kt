package com.examples.gallerio.model

class ImageModel(val documentId: String?, val categoryName: String?, val imageUrl: String?) {
    constructor(): this( "", "", "")
}