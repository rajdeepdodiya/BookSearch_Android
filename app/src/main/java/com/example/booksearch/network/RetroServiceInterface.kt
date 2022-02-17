package com.example.booksearch.network

import com.example.booksearch.models.BookListModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("volumes")
    fun getBookList(@Query("q") query: String): Observable<BookListModel>

}