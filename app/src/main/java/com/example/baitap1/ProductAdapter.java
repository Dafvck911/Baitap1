package com.example.baitap1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if(product == null){
            return;
        }
        holder.imageView.setImageResource(product.getHinh());
        holder.txtName.setText(product.getName());
        holder.txtMota.setText(product.getMota());
        holder.flGia.setText(String.valueOf(product.getPrice()));
        holder.imageAdd.setImageResource(product.getAdd());
    }

    @Override
    public int getItemCount() {
        if(productList != null){
            return productList.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView txtName;
        private TextView txtMota;
        private TextView flGia;
        private ImageView imageAdd;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView1);
            txtName = itemView.findViewById(R.id.tensp);
            txtMota = itemView.findViewById(R.id.mota);
            flGia = itemView.findViewById(R.id.giatien);
            imageAdd = itemView.findViewById(R.id.money);
        }
    }
}
