package com.example.todo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.TodoItemLayoutBinding
import com.example.todo.model.Todo
import com.example.todo.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>,val adapterOnClick : (Todo) -> Unit) :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),
    TodoCheckedChangeListener, TodoEditClickListener {
    private lateinit var viewModel: DetailTodoViewModel
    class TodoViewHolder(var view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view= DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater,R.layout.todo_item_layout,parent,false)

        return TodoViewHolder(view)

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.todo=todoList[position]
        holder.view.listener=this
        holder.view.editlistener=this
    /*  holder.view.checkTask.setText(todoList[position].title+" "+todoList[position].priority)
            holder.view.checkTask.setOnCheckedChangeListener{compoundButton, isChecked ->
                if(isChecked == true){
                    adapterOnClick(todoList[position])
                    notifyDataSetChanged()
                }

            }
            holder.view.imgEdit.setOnClickListener {
                val action=TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
                Navigation.findNavController(it).navigate(action)
            }*/

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked){
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val action=TodoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

}