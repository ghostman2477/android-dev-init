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

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;


    private ExtendedFloatingActionButton fab;
    public ProductAdapter( List<Product> productList,ExtendedFloatingActionButton fab) {
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

        ProductAddToCart p = new ProductAddToCart();
        holder.buttonPlus.setOnClickListener(v -> {
            int qtyselected = Integer.parseInt(holder.productQuantity.getText().toString())+ 1;
            product.setInStock(qtyselected);
            holder.productQuantity.setText(String.valueOf(product.getInStock()));
            if(fab.getVisibility() == View.INVISIBLE){
                fab.setVisibility(View.VISIBLE);
            }
            if(CurrentlyInCart.getInstance().getSharedList().contains(p)){
                for (int i=0;i<CurrentlyInCart.getInstance().getSharedList().size();i++) {
                    ProductAddToCart productsInCart = CurrentlyInCart.getInstance().getSharedList().get(i);
                    if (productsInCart.getProductId().equals(product.getProductId())) {
                        productsInCart.setQtySelected(qtyselected);
                        CurrentlyInCart.getInstance().getSharedList().set(i, productsInCart);
                        break;
                    }
                }
            }
            else {
                p.setQtySelected(qtyselected);
                p.setProductId(product.getProductId());
                p.setProductName(product.getProductName());
                p.setBrandName(product.getProductName());
                p.setCategoryName(product.getCategoryName());
                p.setFlavourName(product.getFlavourName());
                p.setProductPrice(product.getProductPrice());
                CurrentlyInCart.getInstance().getSharedList().add(p);
            }

        });

        holder.buttonMinus.setOnClickListener(v -> {
            int qtyremoved = product.getInStock() - 1;
            if (qtyremoved >= 1) {
                product.setInStock(qtyremoved);
                            }
            else{
                CurrentlyInCart.getInstance().getSharedList().remove(p);
            }
            if(qtyremoved >= 0) {
                holder.productQuantity.setText(String.valueOf(qtyremoved));
            }

            if(CurrentlyInCart.getInstance().getSharedList().contains(p)){
                for (int i=0;i<CurrentlyInCart.getInstance().getSharedList().size();i++) {
                    ProductAddToCart productsInCart = CurrentlyInCart.getInstance().getSharedList().get(i);
                    if (productsInCart.getProductId().equals(product.getProductId())) {
                        productsInCart.setQtySelected(qtyremoved);
                        CurrentlyInCart.getInstance().getSharedList().set(i, productsInCart);
                        break;
                    }
                }
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