package com.example.myapplication;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast; // For the click listener example

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R; // Make sure this import is correct for your R file
import com.example.myapplication.Category; // Import your Category model class

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categoryList;

    public CategoryAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single category item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false); // Uses item_category.xml
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);

        // Bind data to the views in the ViewHolder
        holder.categoryName.setText(category.getCategoryName());
      //  holder.categoryIcon.setImageResource(category.getIconUrl());

        // Optional: Add a click listener for the entire item
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked Category: " + category.getCategoryName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), IceCreamShowList.class);
            intent.putExtra("category_name", category.getCategoryName());
             v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    // Inner ViewHolder class to hold references to the views for each item
    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.catImageView);
            categoryName = itemView.findViewById(R.id.catNameTextView);
        }
    }
}