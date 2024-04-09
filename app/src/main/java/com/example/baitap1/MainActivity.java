package com.example.baitap1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv;
    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv = findViewById(R.id.recycleview);
        productAdapter = new ProductAdapter(this);

        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        rcv.setLayoutManager(linearLayoutManager);

        productAdapter.setData(getListProduct());
        rcv.setAdapter(productAdapter);
    }

    private List<Product> getListProduct(){
        List<Product> list = new ArrayList<>();

        list.add(new Product("Tượng Thần Tài", "Thuận lợi công việc", 50000, R.drawable.than_tai, R.drawable.add_shopping));
        list.add(new Product("Bể cá", "Sinh vượng khí", 65000, R.drawable.be_ca, R.drawable.add_shopping));
        list.add(new Product("Cây cảnh", "Mang tài lộc", 30000, R.drawable.cay_canh, R.drawable.add_shopping));
        list.add(new Product("Thiềm Thừ", "Cóc ba chân", 10000, R.drawable.coc_ba_chan, R.drawable.add_shopping));
        list.add(new Product("Đồng xu may mắn", "Mang đến vận may", 5000, R.drawable.dong_xu, R.drawable.add_shopping));
        list.add(new Product("Long Quy", "Thăng tiến", 99900, R.drawable.long_quy, R.drawable.add_shopping));
        list.add(new Product("Rồng vàng", "Xua đuổi tà ma", 150000, R.drawable.rong, R.drawable.add_shopping));
        list.add(new Product("Thác nước", "Phong thủy", 400000, R.drawable.thac_nuoc, R.drawable.add_shopping));
        list.add(new Product("Tỳ Hưu", "Chiêu tài", 20000, R.drawable.ty_huu, R.drawable.add_shopping));
        list.add(new Product("Voi Thần", "Thu hút may mắn", 55000, R.drawable.voi, R.drawable.add_shopping));

        return list;
    }
}