package com.example.todo.view

import android.view.View
import android.widget.CompoundButton
import com.example.todo.model.Todo

interface TodoCheckedChangeListener{
    fun onTodoCheckedChange(cb:CompoundButton, isChecked:Boolean,obj:Todo)
}

interface TodoEditClickListener{
    fun onTodoEditClick(v:View)
}