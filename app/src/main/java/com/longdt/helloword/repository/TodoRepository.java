package com.longdt.helloword.repository;

import com.longdt.helloword.entity.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TodoRepository {

    @GET("todos")
    Call<List<Todo>> getAllTodo(@Query("id") Integer id);

    @GET("todos")
    Call<List<Todo>> getTodoById(@Query("userId") Integer id);
}

