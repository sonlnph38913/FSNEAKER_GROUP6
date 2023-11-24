package com.sonlnph38913.fpoly.fsneaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.sonlnph38913.fpoly.fsneaker.dao.QuanLyDao;
import com.sonlnph38913.fpoly.fsneaker.fragment.DangXuatFragment;
import com.sonlnph38913.fpoly.fsneaker.fragment.DoanhThuFragment;
import com.sonlnph38913.fpoly.fsneaker.fragment.DoiMatKhauFragment;
import com.sonlnph38913.fpoly.fsneaker.fragment.GiayFragment;
import com.sonlnph38913.fpoly.fsneaker.fragment.HoaDonFragment;
import com.sonlnph38913.fpoly.fsneaker.fragment.HomeFragment;
import com.sonlnph38913.fpoly.fsneaker.fragment.LoaiGiayFragment;
import com.sonlnph38913.fpoly.fsneaker.fragment.SettingFragment;
import com.sonlnph38913.fpoly.fsneaker.fragment.ThanhVienFragment;
import com.sonlnph38913.fpoly.fsneaker.fragment.TopFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.navigationview);
       // BottomNavigationView bottomNavigationView = findViewById(R.id.frmBottom);
        drawerLayout = findViewById(R.id.drawerlayout);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.icon_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                if (menuItem.getItemId() == R.id.mLoaiGiay){
                    LoaiGiayFragment loaiGiayFragment = new LoaiGiayFragment();
                    replaceFrg(loaiGiayFragment);
                }else if (menuItem.getItemId() == R.id.mGiay){
                    GiayFragment giayFragment = new GiayFragment();
                    replaceFrg(giayFragment);
                }else if (menuItem.getItemId() == R.id.mHoaDon){
                    HoaDonFragment hoaDonFragment = new HoaDonFragment();
                    replaceFrg(hoaDonFragment);
                }else if (menuItem.getItemId() == R.id.mThanhVien){
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    replaceFrg(thanhVienFragment);
                }else if (menuItem.getItemId() == R.id.mDoanhThu){
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    replaceFrg(doanhThuFragment);
                }else if (menuItem.getItemId() == R.id.mTop){
                    TopFragment topFragment = new TopFragment();
                    replaceFrg(topFragment);
                }else if (menuItem.getItemId() == R.id.mDoiMatKhau){
                    DoiMatKhauFragment doiMatKhauFragment = new DoiMatKhauFragment();
                    replaceFrg(doiMatKhauFragment);
                    showDialogDoiMatKhau();
                }else if (menuItem.getItemId() == R.id.mDangXuat){
                    DangXuatFragment dangXuatFragment = new DangXuatFragment();
                    replaceFrg(dangXuatFragment);
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (menuItem.getItemId() == R.id.mHome) {
                    HomeFragment homeFragment = new HomeFragment();
                    replaceFrg(homeFragment);
                } else if (menuItem.getItemId() == R.id.mSetting) {
                    SettingFragment settingFragment = new SettingFragment();
                   replaceFrg(settingFragment);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle(menuItem.getTitle());

                return true;
            }
        });
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                if (menuItem.getItemId() == R.id.bmHome){
//                    HomeFragment homeFragment = new HomeFragment();
//                    replaceFrg(homeFragment);
//                }else if (menuItem.getItemId() == R.id.bmGiay){
//                    GiayFragment giayFragment = new GiayFragment();
//                    replaceFrg(giayFragment);
//                } else if (menuItem.getItemId() == R.id.bmSetting) {
//                    SettingFragment settingFragment = new SettingFragment();
//                    replaceFrg(settingFragment);
//                }
//                drawerLayout.closeDrawer(GravityCompat.START);
//                toolbar.setTitle(menuItem.getTitle());
//
//                return true;
//            }
//        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frameLayout, frg).commit();

    }
    private void showDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau,null);

        TextInputEditText edtOldPass = view.findViewById(R.id.edtOldPass);
        TextInputEditText edtNewPass = view.findViewById(R.id.edtNewPass);
        TextInputEditText edtRePass = view.findViewById(R.id.edtRePass);
        Button btnConfirm = view.findViewById(R.id.btnConfirm);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        builder.setView(view);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = edtOldPass.getText().toString();
                String newpass = edtNewPass.getText().toString();
                String repass = edtRePass.getText().toString();
                if (oldpass.equals("") | newpass.equals("") | repass.equals("")) {
                    Toast.makeText(MainActivity.this, "Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (newpass.equals(repass)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                        String maql = sharedPreferences.getString("maql", "");
                        QuanLyDao quanLyDao = new QuanLyDao(MainActivity.this);
                        int check = quanLyDao.capNhapMatKhau(maql,oldpass,newpass);

                        if (check == 1) {

                            Toast.makeText(MainActivity.this, "Cập Nhập Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Login.class);
                            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            alertDialog.dismiss();
                        }  else if (check == 0) {
                           Toast.makeText(MainActivity.this, "Mật Khẩu Cũ Không Đúng", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Cập Nhập Mật Khẩu Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Nhập Mật Khẩu Không Khớp Nhau", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


    }
}



