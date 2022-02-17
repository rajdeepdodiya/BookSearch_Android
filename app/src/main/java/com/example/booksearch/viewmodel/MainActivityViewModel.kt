package com.example.booksearch.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksearch.models.BookListModel
import com.example.booksearch.network.RetroInstance
import com.example.booksearch.network.RetroServiceInterface
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel: ViewModel() {

    private val TAG = "MY_TAG"
    lateinit var bookList: MutableLiveData<BookListModel>

    init {
        bookList = MutableLiveData()
    }

    fun getBookListObserver(): MutableLiveData<BookListModel>{
        return bookList
    }

    fun makeAPICall(query: String){

        val retroInstance = RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)

        retroInstance.getBookList(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBookListObserverRx())
    }

    private fun getBookListObserverRx(): Observer<BookListModel>{

        return object : Observer<BookListModel>{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe() called ")
            }

            override fun onNext(t: BookListModel) {
                Log.d(TAG, "onNext() called ")
                bookList.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError() called: ${e.localizedMessage} ")
                bookList.postValue(null)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete() called ")
            }
        }
    }
}