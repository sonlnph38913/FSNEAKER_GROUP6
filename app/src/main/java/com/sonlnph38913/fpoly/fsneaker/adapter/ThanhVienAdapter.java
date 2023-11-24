package com.sonlnph38913.fpoly.fsneaker.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sonlnph38913.fpoly.fsneaker.R;
import com.sonlnph38913.fpoly.fsneaker.dao.ThanhVienDao;
import com.sonlnph38913.fpoly.fsneaker.model.ThanhVien;

import java.util.ArrayList;
import java.util.Calendar;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDao thanhVienDao;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDao thanhVienDao) {
        this.context = context;
        this.list = list;
        this.thanhVienDao = thanhVienDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaTv.setText("Mã TV: " + list.get(position).getMatv());
        holder.txtTen.setText(list.get(position).getHoten());
        holder.txtNamSinh.setText("Ngày Sinh: " + list.get(position).getNamsinh());
        String imgTV = list.get(position).getImgTv();
        int resId = ((Activity)context).getResources().getIdentifier(imgTV,"drawable",((Activity)context).getPackageName());
        holder.imgTV.setImageResource(resId);

        holder.imDelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = thanhVienDao.xoaThanhVien(list.get(holder.getAdapterPosition()).getMatv());
                switch (check) {
                    case 1:
                        Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                    case -1:
                        Toast.makeText(context, "Không Thể Xóa Thành Viên ", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        holder.itemThanhVien.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDiaLogCapNhap(list.get(holder.getAdapterPosition()));
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaTv, txtNamSinh;
        TextView txtTen;
        ImageView imDelTv;
        CardView itemThanhVien;
        ImageView imgTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaTv = itemView.findViewById(R.id.txtMaTV);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtNamSinh = itemView.findViewById(R.id.txtNamSinh);
            imDelTv = itemView.findViewById(R.id.imDelTV);
            itemThanhVien = itemView.findViewById(R.id.itemThanhVien);
            imgTV = itemView.findViewById(R.id.imgTV);
        }
    }

    private void loadData() {
        list.clear();
        list = thanhVienDao.getDSThanhVien();
        notifyDataSetChanged();
    }
    private void showDiaLogCapNhap(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_updatetv,null);
        builder.setView(view);
        TextView txtMaTvs = view.findViewById(R.id.txtMATvs);
        EditText edtTens = view.findViewById(R.id.edtTens);
        EditText edtNams = view.findViewById(R.id.edtNamSinhs);
        Button btnSuaTv = view.findViewById(R.id.btnSuaTV);
        Button btnCancelTv = view.findViewById(R.id.btncancelTv);

        txtMaTvs.setText("Mã Tv: " + thanhVien.getMatv());
        edtTens.setText(thanhVien.getHoten());
        edtNams.setText(thanhVien.getNamsinh());

        AlertDialog alertDialog =builder.create();
        alertDialog.show();
        Calendar calendar = Calendar.getInstance();
        edtNams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        edtNams.setText(i2 + "/" + (i1+1) +"/"+i);
                    }
                },

                        calendar.get(calendar.YEAR),
                        calendar.get(calendar.MONTH),
                        calendar.get(calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        btnSuaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String hoten = edtTens.getText().toString();
            String namsinh = edtNams.getText().toString();
            int id = thanhVien.getMatv();

            boolean check = thanhVienDao.capNhapThongTinTV(id,hoten,namsinh);
            if (check){
                Toast.makeText(context, "Update Thành Công", Toast.LENGTH_SHORT).show();
                loadData();
            }else {
                Toast.makeText(context, "Update Thất Bại", Toast.LENGTH_SHORT).show();
            }
            alertDialog.dismiss();

            }
        });
        btnCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }
}
