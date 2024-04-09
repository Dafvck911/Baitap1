package com.example.baitap1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dangnhap extends AppCompatActivity {

    EditText tentaikhoan;
    EditText matkhau;
    Button btdangnhap;
    TextView dangkytk;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://modulelogin-9742e-default-rtdb.asia-southeast1.firebasedatabase.app/");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        setupUI();
        setupListener();
    }

    private void setupUI(){
        tentaikhoan = (EditText) findViewById(R.id.tentaikhoan_dn);
        matkhau = (EditText) findViewById(R.id.matkhau_dn);
        btdangnhap = (Button) findViewById(R.id.bt_dangnhap);
        dangkytk = (TextView) findViewById(R.id.dangkytk);
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private void setupListener(){
        btdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String tentaikhoantxt = tentaikhoan.getText().toString();
                final String matkhautxt = matkhau.getText().toString();

                if(checktk()){
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check trong firebase tentaikhoan neu co thi sẽ match voi password
                            if(snapshot.hasChild(tentaikhoantxt)){
                                final String getmathkhau = snapshot.child(tentaikhoantxt).child("mat khau").getValue(String.class);

                                if(getmathkhau.equals(matkhautxt)){
                                    Toast.makeText(Dangnhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Dangnhap.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                else {
                                    Toast.makeText(Dangnhap.this, "Sai mật khẩu!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(Dangnhap.this, "Sai tài khoản!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        dangkytk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dangnhap.this, Dangky.class);
                startActivity(i);
            }
        });
    }

    private boolean checktk(){
        boolean isValid = true;
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
        return isValid;
    }
}
