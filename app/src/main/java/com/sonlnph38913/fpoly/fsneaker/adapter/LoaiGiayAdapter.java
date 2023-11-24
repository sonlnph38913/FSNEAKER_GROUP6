package com.sonlnph38913.fpoly.fsneaker.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sonlnph38913.fpoly.fsneaker.R;
import com.sonlnph38913.fpoly.fsneaker.dao.LoaiGiayDao;
import com.sonlnph38913.fpoly.fsneaker.model.LoaiGiay;

import java.util.ArrayList;

public class LoaiGiayAdapter extends RecyclerView.Adapter<LoaiGiayAdapter.ViewHolder> {
   private Context context;
   private ArrayList<LoaiGiay>list;
   private LoaiGiayDao loaiGiayDao;

    public LoaiGiayAdapter(Context context, ArrayList<LoaiGiay> list, LoaiGiayDao loaiGiayDao) {
        this.context = context;
        this.list = list;
        this.loaiGiayDao = loaiGiayDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaigiay,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiGiay loaiGiay = list.get(position);

        holder.txtTenLoai.setText(loaiGiay.getTenloai());
        holder.txtMaLoai.setText("Mã: " + loaiGiay.getMaloai());


        holder.itemLoai.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDiaLogUpdate(list.get(holder.getAdapterPosition()));
                return false;
            }
        });
        holder.imDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = loaiGiayDao.xoaLoaiGiay(list.get(position).getMaloai());
                if (check == 1){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    loadData();
                    return;
                } else if (check == 0) {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                    return;
                } else if (check == -1) {
                    Toast.makeText(context, "Không Thể Xóa Loại Giày Này ", Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTenLoai,txtMaLoai;
        CardView itemLoai;

        ImageView imDel;
        //ImageView imgLoai;
        public ViewHolder(@NonNull View view){
            super(view);
            //imgLoai = view.findViewById(R.id.imgLoai);
            txtTenLoai = view.findViewById(R.id.txtTenLoai);
            txtMaLoai = view.findViewById(R.id.txtMaLoai);
            itemLoai = view.findViewById(R.id.itemLoai);
            imDel = view.findViewById(R.id.imDel);

        }



    }
    private void loadData(){
        list.clear();
        list = loaiGiayDao.getDSLoaiGiay();
        notifyDataSetChanged();
    }
    private void showDiaLogUpdate(LoaiGiay loaiGiay){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_updateloai,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextView txtMaLoais = view.findViewById(R.id.txtMaLoai);
        EditText edtTenLoai = view.findViewById(R.id.edtTenLoai);
        Button btnSua = view.findViewById(R.id.btnSua);
        Button btnCancel = view.findViewById(R.id.btncancel);

        edtTenLoai.setText(loaiGiay.getTenloai());
        txtMaLoais.setText("Mã Loại: " + loaiGiay.getMaloai());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edtTenLoai.getText().toString();
                int maloai = loaiGiay.getMaloai();
                boolean check = loaiGiayDao.thayDoiLoaiGiay(maloai,tenloai);
                if (check){
                    Toast.makeText(context, "Update Thành Công", Toast.LENGTH_SHORT).show();
                loadData();
                }else {
                    Toast.makeText(context, "Update Thất Bại", Toast.LENGTH_SHORT).show();

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
