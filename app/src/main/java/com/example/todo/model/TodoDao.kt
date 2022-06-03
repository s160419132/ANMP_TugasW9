package com.example.todo.model

import android.icu.text.CaseMap
import androidx.room.*

@Dao
interface TodoDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(vararg todo: Todo)

  @Query("Select * From todo WHERE is_done = 0 ORDER BY priority DESC")
  suspend fun selectAllTodo(): List<Todo>

  @Query("SELECT * FROM todo WHERE uuid= :id")
  suspend fun selectTodo(id:Int): Todo

  @Query("UPDATE todo Set is_done=:is_done WHERE uuid=:uuid")
  suspend fun update(is_done:Int,uuid: Int)

  @Query("UPDATE todo Set title=:title, notes=:notes, priority=:priority WHERE uuid=:uuid")
  suspend fun update(title: String,notes:String,priority:Int, uuid:Int)
  @Delete
  suspend fun deleteTodo(todo:Todo)
}