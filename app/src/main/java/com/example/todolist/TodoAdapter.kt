package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding


class TodoAdapter (private val todos : MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    lateinit var binding : ItemTodoBinding
    class TodoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TodoViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return todos.size
    }


    fun addTodo(todo : Todo){
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }


    fun deleteTodos(){
        todos.removeAll { todo ->
            todo.isCheckd
        }
        notifyDataSetChanged()
    }


    // to check an item in recycler view
    private fun toggleStrikeThrough(tvTodoTitle : TextView , isChecked : Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var currTodo =todos[position]
        //binding = ItemTodoBinding.inflate()
        holder.itemView.apply {
            binding.tvTodoTask.text = currTodo.title
            binding.cbDone.isChecked = currTodo.isCheckd
            toggleStrikeThrough(binding.tvTodoTask , currTodo.isCheckd)
            binding.cbDone.setOnCheckedChangeListener {_ , isChecked ->
                toggleStrikeThrough(binding.tvTodoTask , isChecked)
                currTodo.isCheckd = !currTodo.isCheckd
            }
        }
    }
}