package com.longdt.helloword.ProductC;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.longdt.helloword.R;
import com.longdt.helloword.entity.Product;
import com.longdt.helloword.recycleView.MyAdapter;

import java.util.List;

public class ProductList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Product> listP;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recycleview_listProduct);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        Intent intent = getIntent();

        int id = Integer.parseInt(intent.getStringExtra("category"));

        System.out.println(id);
        Product p = new Product();
//        for (Product pro : p.getList()) {
//            if(id == pro.getCateId()){
//                listP.add(pro);
//            }
//        }

        MyAdapter myAdapter = new MyAdapter((List<Object>)(Object)p.getList(), getApplicationContext(), "product");
        recyclerView.setAdapter(myAdapter);




//        imageView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//            @Override
//            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//                getMenuInflater().inflate(R.menu.menu_context, contextMenu);
//
//            }
//        });

    }
}
