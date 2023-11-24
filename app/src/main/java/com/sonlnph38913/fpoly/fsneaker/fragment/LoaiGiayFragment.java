package com.sonlnph38913.fpoly.fsneaker.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sonlnph38913.fpoly.fsneaker.R;
import com.sonlnph38913.fpoly.fsneaker.adapter.LoaiGiayAdapter;
import com.sonlnph38913.fpoly.fsneaker.dao.LoaiGiayDao;
import com.sonlnph38913.fpoly.fsneaker.model.LoaiGiay;

import java.util.ArrayList;

public class LoaiGiayFragment extends Fragment {
    RecyclerView rcvLoai;

    LoaiGiayDao loaiGiayDao;
    LoaiGiayAdapter loaiGiayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loaigiay_fragment,container,false);
        rcvLoai = view.findViewById(R.id.rcvLoai);
        FloatingActionButton fltAddLoai = view.findViewById(R.id.fltAddLoai);
        SearchView svLoai = view.findViewById(R.id.svLoai);
        loaiGiayDao = new LoaiGiayDao(getContext());
        loadData();

        fltAddLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLogThem();
            }
        });
        svLoai.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<LoaiGiay> listFilter = new ArrayList<>();
                for (LoaiGiay loaiGiay: loaiGiayDao.getDSLoaiGiay()){
                    if (loaiGiay.getTenloai().toLowerCase().contains(s.toLowerCase())){
                        listFilter.add(loaiGiay);
                    }
                }
                loadSearch(listFilter);
                return false;
            }
        });

        return view;
    }
    private void loadSearch(ArrayList<LoaiGiay>listlg){
        LoaiGiayAdapter adapter = new LoaiGiayAdapter(getContext(),listlg,loaiGiayDao);
        rcvLoai.setAdapter(adapter);
    }
    public void loadData(){
        ArrayList<LoaiGiay>list = loaiGiayDao.getDSLoaiGiay();
       GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcvLoai.setLayoutManager(gridLayoutManager);
        loaiGiayAdapter = new LoaiGiayAdapter(getContext(),list,loaiGiayDao);
        rcvLoai.setAdapter(loaiGiayAdapter);
    }
    private void showDiaLogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_themloai,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        EditText edtTenLoai =view.findViewById(R.id.edtTenLoaiAdd);
        Button btnAdd = view.findViewById(R.id.btnthem);
        Button btnCancel = view.findViewById(R.id.btncancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edtTenLoai.getText().toString();
                boolean check = loaiGiayDao.themLoaiGiay(tenloai);
                if (check){
                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    loadData();



                }else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });





    }
}
