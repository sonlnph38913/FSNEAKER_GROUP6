package com.sonlnph38913.fpoly.fsneaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sonlnph38913.fpoly.fsneaker.dao.QuanLyDao;

public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText edtCreName = findViewById(R.id.edtName);
        EditText edtCreUser = findViewById(R.id.edtUser);
        EditText edtCrePass = findViewById(R.id.edtPass);
        EditText edtRePass = findViewById(R.id.edtRePass);
        Button btnRegister = findViewById(R.id.btnRegister);
        QuanLyDao quanLyDao = new QuanLyDao(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String user = edtCreUser.getText().toString();
               String pass = edtCrePass.getText().toString();
               String repass = edtRePass.getText().toString();
               String fullname = edtCreName.getText().toString();
               
               if (!pass.equals(repass)){
                   Toast.makeText(Register.this, "PassWord Incorrect", Toast.LENGTH_SHORT).show();
               }else {
                   boolean check = quanLyDao.Register(user,pass,fullname);
                   if (check){
                       Toast.makeText(Register.this, "Account Registration Successful", Toast.LENGTH_SHORT).show();
                        finish();
                   }else {
                       Toast.makeText(Register.this, "Account Registration Fail", Toast.LENGTH_SHORT).show();
                   }
               }
               
                
            }
        });



    }
}