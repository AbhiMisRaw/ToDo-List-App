package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var todoAdapter : TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf())
        binding.rvTodoItems.adapter = todoAdapter

        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener{
            val todoTask = binding.etTodoTitle.text.toString()
            if(todoTask.isNotEmpty()){
                todoAdapter.addTodo(Todo(todoTask , false))
                binding.etTodoTitle.text.clear()
            }

        }
        binding.btnDeleteTodo.setOnClickListener {
            todoAdapter.deleteTodos()
        }

    }




}