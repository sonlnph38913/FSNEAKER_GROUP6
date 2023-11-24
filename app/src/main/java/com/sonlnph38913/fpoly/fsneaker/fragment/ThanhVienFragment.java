package com.sonlnph38913.fpoly.fsneaker.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sonlnph38913.fpoly.fsneaker.R;
import com.sonlnph38913.fpoly.fsneaker.adapter.LoaiGiayAdapter;
import com.sonlnph38913.fpoly.fsneaker.adapter.ThanhVienAdapter;
import com.sonlnph38913.fpoly.fsneaker.dao.ThanhVienDao;
import com.sonlnph38913.fpoly.fsneaker.model.LoaiGiay;
import com.sonlnph38913.fpoly.fsneaker.model.ThanhVien;

import java.util.ArrayList;
import java.util.Calendar;

public class ThanhVienFragment extends Fragment {
    ThanhVienDao thanhVienDao;
    RecyclerView rcvThanhVien;
    ThanhVienAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thanhvien_fragment, container, false);
        rcvThanhVien = view.findViewById(R.id.rcvThanhVien);
        FloatingActionButton floatAddTV = view.findViewById(R.id.fltAddThanhVien);
        SearchView svThanhVien = view.findViewById(R.id.svThanhVien);
        thanhVienDao = new ThanhVienDao(getContext());
        loadData();

//        floatAddTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDiaLogThem();
//            }
//        });
        svThanhVien.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<ThanhVien> listFilter = new ArrayList<>();
                for (ThanhVien thanhVien : thanhVienDao.getDSThanhVien()) {
                    if (thanhVien.getHoten().toLowerCase().contains(s.toLowerCase())) {
                        listFilter.add(thanhVien);
                    }
                    loadSearch(listFilter);
                }
                return false;
            }
        });
        return view;
    }

    private void loadSearch(ArrayList<ThanhVien> listtv) {
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(), listtv, thanhVienDao);
        rcvThanhVien.setAdapter(adapter);
    }

    private void loadData() {
        ArrayList<ThanhVien> list = thanhVienDao.getDSThanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rcvThanhVien.setLayoutManager(linearLayoutManager);
        adapter = new ThanhVienAdapter(getContext(), list, thanhVienDao);
        rcvThanhVien.setAdapter(adapter);
    }

    private void showDiaLogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themtv, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        TextInputEditText edtTena = view.findViewById(R.id.edtTena);
        TextInputEditText edtNgaySinh = view.findViewById(R.id.edtNamSinha);
        ImageView imgTVAdd = view.findViewById(R.id.imgTvAdd);
        Button btnThem = view.findViewById(R.id.btnAddTV);
        Calendar calendar = Calendar.getInstance();
        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        edtNgaySinh.setText(i2 + "/" + (i1 + 1) + "/" + i);

                    }
                },
                        calendar.get(calendar.YEAR),
                        calendar.get(calendar.MONTH),
                        calendar.get(calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String hoten = edtTena.getText().toString();
//                String namsinh = edtNgaySinh.getText().toString();
//                String hinhanh =
//                boolean check = thanhVienDao.themThanhVien(hoten, namsinh);
//                if (check) {
//                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
//                    loadData();
//                    alertDialog.dismiss();
//                } else {
//                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}
