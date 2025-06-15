package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductSummaryAdapter extends RecyclerView.Adapter<ProductSummaryAdapter.ViewHolder> {
    private List<ProductAddToCart> productList = new ArrayList<>();

    public ProductSummaryAdapter(List<ProductAddToCart> productList) {
        this.productList = productList;

    }

    @Override
    public ProductSummaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.per_product_order_summary, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductAddToCart product = productList.get(position);
        System.out.println( productList.size());
        holder.name.setText(product.getProductName());
        holder.price.setText(product.getProductPrice());
        holder.qty.setText(String.valueOf(product.getQtySelected()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, qty;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.productimage);
            qty = itemView.findViewById(R.id.qty);
        }
    }
}
