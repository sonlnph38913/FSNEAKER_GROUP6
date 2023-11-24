package com.sonlnph38913.fpoly.fsneaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sonlnph38913.fpoly.fsneaker.dao.QuanLyDao;

public class Login extends AppCompatActivity {
    TextView txtSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtSignUp = findViewById(R.id.txtCreAccount);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        EditText edtUser = findViewById(R.id.edtUser);
        EditText edtPass = findViewById(R.id.edtPass);
        Button btnLogin = findViewById(R.id.btnLogin);

        QuanLyDao quanLyDao = new QuanLyDao(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                boolean check = quanLyDao.checkLogin(user,pass);

                if (check){
                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,MainActivity.class));
                }else {
                    Toast.makeText(Login.this, "Login Fail", Toast.LENGTH_SHORT).show();
                }
                if (quanLyDao.checkLogin(user,pass)){
//                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("maql", user);
//
//                    editor.commit();
//                    startActivity(new Intent(Login.this,MainActivity.class));
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("maql",user);
                    editor.commit();
                    startActivity(new Intent(Login.this,MainActivity.class));

                }else {
                    Toast.makeText(Login.this, "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}