package com.longdt.helloword.recycleView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longdt.helloword.ProductC.ProductDetail;
import com.longdt.helloword.ProductC.ProductList;
import com.longdt.helloword.R;
import com.longdt.helloword.entity.Product;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView txtView;
    private ImageView imageView;
    private LayoutInflater inflater;

    public MyViewHolder(@NonNull View itemView, String check) {
        super(itemView);

        if(check.equals("category")){
            txtView = itemView.findViewById(R.id.txtCateName);
            imageView = itemView.findViewById(R.id.imgCate);

            imageView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ProductList.class);
                intent.putExtra("category", imageView.getTag().toString());

                view.getContext().startActivity(intent);
            });
        }else if(check.equals("product")){
            txtView = itemView.findViewById(R.id.txtPName);
            imageView = itemView.findViewById(R.id.imgP);

            imageView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ProductDetail.class);
                Product p = (Product) imageView.getTag();
                intent.putExtra("product", p);

                view.getContext().startActivity(intent);
            });
        }

    }

    public TextView getTextView() {
        return txtView;
    }

    public ImageView getImageView() {
        return imageView;
    }

}
