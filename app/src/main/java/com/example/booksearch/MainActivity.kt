package com.example.booksearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksearch.adapter.BookListAdapter
import com.example.booksearch.databinding.ActivityMainBinding
import com.example.booksearch.models.BookListModel
import com.example.booksearch.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val TAG: String? = "MY_TAG"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel
    lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSearchBox()
        initRecyclerView()

    }

    private fun initSearchBox(){
        binding.etBookName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loadDataFromAPI(s.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun initRecyclerView(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
            bookListAdapter = BookListAdapter()
            adapter = bookListAdapter
        }
    }

    private fun loadDataFromAPI(query: String) {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getBookListObserver().observe(this, Observer<BookListModel>{
            if(it.items != null){
                bookListAdapter.bookList = it.items!!
                bookListAdapter.notifyDataSetChanged()

            }
            else{
                Toast.makeText(this, "Error in fetching books", Toast.LENGTH_SHORT).show()
//                Log.e(TAG, "loadDataFromAPI:./. it.items!! == null", )
            }
        })
        viewModel.makeAPICall(query)
    }
}