package com.longdt.helloword.retrofit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.longdt.helloword.R;
import com.longdt.helloword.entity.Todo;
import com.longdt.helloword.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements Callback<List<Todo>> {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private TextView txtApi;
    private TextView txtUserId;
    private TextView txtId;
    private TextView txtTittle;
    private CheckBox cbCompleted;
    private Button btnPre;
    private Button btnNext;

    private List<Todo> todoList;
    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        txtApi = findViewById(R.id.txtApi);
        txtUserId = findViewById(R.id.txtUserID);
        txtId = findViewById(R.id.txtId);
        txtTittle = findViewById(R.id.txtTittle);
        cbCompleted = findViewById(R.id.cbCompleted);
        btnNext = findViewById(R.id.btnNext);
        btnPre = findViewById(R.id.btnPre);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TodoRepository todoRepo = retrofit.create(TodoRepository.class);

        Call<List<Todo>> allTodo = todoRepo.getTodoById(1);
        allTodo.enqueue(this);

        txtApi.setText(BASE_URL);

        buttonAction();

    }

    private void buttonAction(){
        btnPre.setOnClickListener(view -> {
            if(position == 0){
                setInfo(0);
            }else{
                position --;
                setInfo(position);
            }
        });
        btnNext.setOnClickListener(view -> {
            if (position != todoList.size()) {
                position++;
            }
            setInfo(position);
        });
    }

    private void setInfo(int pos){
        txtUserId.setText(todoList.get(pos).getUserId().toString());
        txtId.setText(todoList.get(pos).getTitle());
        txtTittle.setText(todoList.get(pos).getTitle());
        if (todoList.get(pos).getCompleted()) {
            cbCompleted.isChecked();
        }
    }

    @Override
    public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
        if (response.isSuccessful()) {
            todoList = new ArrayList<>();
            List<Todo> todos = response.body();
            for (Todo t : todos) {
                todoList.add(t);
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Todo>> call, Throwable t) {

    }
}
