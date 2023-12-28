package com.example.crud_sqlite_db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productIdTextView.setText("Product ID: " + product.getProductId());
        holder.productNameTextView.setText("Product Name: " + product.getProductName());
        holder.productCategoryTextView.setText("Category: " + product.getProductCategory());
        holder.productQuantityTextView.setText("Quantity: " + product.getProductQuantity());
        holder.productAvailabilityTextView.setText("Availability: " + product.getProductAvailability());
        holder.productPriceTextView.setText("Price: " + product.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productIdTextView, productNameTextView, productCategoryTextView,
                productQuantityTextView, productAvailabilityTextView, productPriceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productIdTextView = itemView.findViewById(R.id.productIdTextView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productCategoryTextView = itemView.findViewById(R.id.productCategoryTextView);
            productQuantityTextView = itemView.findViewById(R.id.productQuantityTextView);
            productAvailabilityTextView = itemView.findViewById(R.id.productAvailabilityTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
        }
    }
}