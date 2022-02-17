package com.example.booksearch.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{
//        https://www.googleapis.com/books/v1/volumes?q=jungle

        val BASE_URL = "https://www.googleapis.com/books/v1/"

        fun getRetroInstance(): Retrofit{

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

}