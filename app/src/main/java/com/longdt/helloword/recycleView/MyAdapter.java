package com.longdt.helloword.recycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longdt.helloword.R;
import com.longdt.helloword.entity.Category;
import com.longdt.helloword.entity.Product;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Category> listCategory;
    private List<Product> listProduct;
    private LayoutInflater inflater;

    public MyAdapter(List<Object> list, Context context, String check) {
        if(check.equals("category")){
            listProduct = null;
            this.listCategory =  (List<Category>)(Object)list;
            this.inflater = LayoutInflater.from(context);
        }else if(check.equals("product")){
            listCategory = null;
            this.listProduct =  (List<Product>)(Object)list;
            this.inflater = LayoutInflater.from(context);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(listCategory != null){
            View view = inflater.inflate(R.layout.recycleview_category_row, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view, "category");
            return viewHolder;
        }else if(listProduct != null){
            View view = inflater.inflate(R.layout.recycleview_product_row, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view, "product");
            return viewHolder;
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(listCategory != null){
            holder.getTextView().setText(listCategory.get(position).getName());
            holder.getImageView().setImageResource(listCategory.get(position).getImg());

            holder.getImageView().setTag(listCategory.get(position).getId());
        }else if(listProduct != null){
            holder.getTextView().setText(listProduct.get(position).getName());
            holder.getImageView().setImageResource(listProduct.get(position).getImg());

            holder.getImageView().setTag(listProduct.get(position));
        }

    }

    @Override
    public int getItemCount() {
        if(listCategory != null){
            return listCategory.size();
        }
        return listProduct.size();
    }
}
