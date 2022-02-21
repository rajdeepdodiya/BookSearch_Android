package com.example.booksearch.models

data class BookListModel(val items: ArrayList<Items>?)
data class Items(val volumeInfo: VolumeInfo?)
data class VolumeInfo(val title: String?, val authors: ArrayList<String>?, val description: String?, val imageLinks: ImageLinks?)
data class ImageLinks(val smallThumbnail: String?)