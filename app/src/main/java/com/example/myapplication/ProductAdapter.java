package com.example.myapplication;

import android.content.Context;
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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;

    private ExtendedFloatingActionButton fab;
    public ProductAdapter(List<Product> productList,ExtendedFloatingActionButton fab) {
        this.productList = productList;
        this.fab = fab;
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
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(product.getProductPrice());
       // int resourceId = context.getResources().getIdentifier(product.getProductImageUrl(), "drawable", context.getPackageName());
       // holder.productImage.setImageResource(resourceId);
        //holder.productQuantity.setText(String.valueOf(product.getInStock()));
        holder.buttonPlus.setTextColor(Color.BLACK);
        holder.buttonMinus.setTextColor(Color.BLACK);
        holder.buttonPlus.setVisibility(View.VISIBLE);
        holder.buttonMinus.setVisibility(View.VISIBLE);
        holder.buttonPlus.setAlpha(1.0f);
        holder.buttonMinus.setAlpha(1.0f);


        holder.buttonPlus.setOnClickListener(v -> {
            product.setInStock(Integer.parseInt(holder.productQuantity.getText().toString())+ 1);
            holder.productQuantity.setText(String.valueOf(product.getInStock()));
            if(fab.getVisibility() == View.INVISIBLE){
                fab.setVisibility(View.VISIBLE);
            }

        });

        holder.buttonMinus.setOnClickListener(v -> {
            if (product.getInStock() > 1) {
                product.setInStock(product.getInStock() - 1);
                holder.productQuantity.setText(String.valueOf(product.getInStock()));
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