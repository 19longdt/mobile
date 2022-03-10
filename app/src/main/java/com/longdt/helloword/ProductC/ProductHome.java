package com.longdt.helloword.ProductC;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.longdt.helloword.R;
import com.longdt.helloword.dao.CategoryDAO;
import com.longdt.helloword.db.DBConnection;
import com.longdt.helloword.entity.Category;
import com.longdt.helloword.recycleView.MyAdapter;

import java.util.List;

public class ProductHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Category> listCategory;
    private DBConnection dbConnection;
    private CategoryDAO categoryDAO;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_home);

        recyclerView = findViewById(R.id.recycleview_listCate);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        dbConnection = Room.databaseBuilder(this, DBConnection.class, "category")
                .allowMainThreadQueries()
                .build();
        categoryDAO = dbConnection.createCateDAO();
//        categoryList = categoryDAO.listCate();

        MyAdapter myAdapter = new MyAdapter((List<Object>)(Object)categoryDAO.listCate(), getApplicationContext(), "category");
        recyclerView.setAdapter(myAdapter);


    }

}
