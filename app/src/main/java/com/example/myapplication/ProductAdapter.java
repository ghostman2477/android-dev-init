package com.example.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast; // For the click listener example

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R; // Make sure this import is correct for your R file
import com.example.myapplication.Product; // Import your Product model class

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single product item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Bind data to the views in the ViewHolder
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        holder.productImage.setImageResource(product.getImageUrl());
        holder.productQuantity.setText(String.valueOf(product.getQuantity()));
        holder.buttonPlus.setTextColor(Color.BLACK);
        holder.buttonMinus.setTextColor(Color.BLACK);
        holder.buttonPlus.setVisibility(View.VISIBLE);
        holder.buttonMinus.setVisibility(View.VISIBLE);
        holder.buttonPlus.setAlpha(1.0f);
        holder.buttonMinus.setAlpha(1.0f);


        holder.buttonPlus.setOnClickListener(v -> {
            product.setQuantity(product.getQuantity() + 1);
            holder.productQuantity.setText(String.valueOf(product.getQuantity()));
        });

        holder.buttonMinus.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                holder.productQuantity.setText(String.valueOf(product.getQuantity()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Inner ViewHolder class to hold references to the views for each item
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;

        TextView productQuantity;

        Button buttonPlus, buttonMinus;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productQuantity = itemView.findViewById(R.id.textQuantity);
            buttonPlus = itemView.findViewById(R.id.buttonPlus);
            buttonMinus = itemView.findViewById(R.id.buttonMinus);
        }


    }

}