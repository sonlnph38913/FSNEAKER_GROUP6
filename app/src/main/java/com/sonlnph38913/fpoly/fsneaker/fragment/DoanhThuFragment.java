package com.sonlnph38913.fpoly.fsneaker.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sonlnph38913.fpoly.fsneaker.R;
import com.sonlnph38913.fpoly.fsneaker.dao.ThongKeDao;

import java.util.Calendar;

public class DoanhThuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doanhthu_fragment,container,false);
        EditText edtBatDau = view.findViewById(R.id.edtBatDau);
        EditText edtKetThuc = view.findViewById(R.id.edtKetThuc);
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView txtThongKe = view.findViewById(R.id.txtThongKe);

        Calendar calendar = Calendar.getInstance();
        edtBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        edtBatDau.setText(i + "/" + (i1 + 1) + "/" + i2);
                    }
                },
                        calendar.get(calendar.YEAR),
                        calendar.get(calendar.MONTH),
                        calendar.get(calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        edtKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    edtKetThuc.setText(i + "/" + (i1 + 1) + "/" + i2);
                }
            },
                    calendar.get(calendar.YEAR),
                    calendar.get(calendar.MONTH),
                    calendar.get(calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThongKeDao thongKeDao = new ThongKeDao(getContext());
                String ngayBatDau = edtBatDau.getText().toString();
                String ngayKetThuc = edtKetThuc.getText().toString();

                int doanhThu = thongKeDao.getDoanhThu(ngayBatDau,ngayKetThuc);
                txtThongKe.setText(doanhThu + "VNĐ");
            }
        });


        return view;
    }
}
