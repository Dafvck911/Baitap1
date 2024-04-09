package com.example.baitap1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dangky extends AppCompatActivity {

    EditText tennhanvien;
    EditText tentaikhoan;
    EditText matkhau;
    EditText ngaytaotk;
    Button btdangky;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://modulelogin-9742e-default-rtdb.asia-southeast1.firebasedatabase.app/");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);

        setupUI();
        setupListener();
    }

    private void setupUI(){
        tennhanvien = (EditText) findViewById(R.id.tennhanvien);
        tentaikhoan = (EditText) findViewById(R.id.tentaikhoan_dk);
        matkhau = (EditText) findViewById(R.id.matkhau_dk);
        ngaytaotk = (EditText) findViewById(R.id.ngaytaotk);
        btdangky = (Button) findViewById(R.id.bt_dangky);
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private void setupListener(){
        btdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String tennhanvientxt = tennhanvien.getText().toString();
                final String tentaikhoantxt = tentaikhoan.getText().toString();
                final String matkhautxt = matkhau.getText().toString();
                final String ngaytaotxt = ngaytaotk.getText().toString();

                if(checktk()){
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check tentaikhoan chua dk truoc day
                            if (snapshot.hasChild(tentaikhoantxt)){
                                Toast.makeText(Dangky.this, "Tên tài khoản đã được sử dụng!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("users").child(tentaikhoantxt).child("ten nhan vien").setValue(tennhanvientxt);
                                databaseReference.child("users").child(tentaikhoantxt).child("mat khau").setValue(matkhautxt);
                                databaseReference.child("users").child(tentaikhoantxt).child("ngay tao tai khoan").setValue(ngaytaotxt);

                                Toast.makeText(Dangky.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });
    }

    private boolean checktk(){
        boolean isValid = true;
        if(isEmpty(tennhanvien)){
            tennhanvien.setError("Tên nhân viên không được để trống!");
            isValid = false;
        }
        if (isEmpty(tentaikhoan)){
            tentaikhoan.setError("Tên tài khoản không được để trống!");
            isValid = false;
        }

        if(isEmpty(matkhau)){
            matkhau.setError("Mật khẩu không được để trống!");
            isValid = false;
        } else if (matkhau.length() <= 6) {
            matkhau.setError("Mật khẩu phải dài hơn 6 kí tự!");
            isValid = false;
        }
        if (isEmpty(ngaytaotk)){
            ngaytaotk.setError("Ngày tạo tài khoản không được để trống!");
            isValid = false;
        }

        return isValid;
    }
}
