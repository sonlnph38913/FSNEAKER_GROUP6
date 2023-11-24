package com.sonlnph38913.fpoly.fsneaker.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonlnph38913.fpoly.fsneaker.database.Dbhelper;

import java.util.ArrayList;

public class ThongKeDao {
    Dbhelper dbhelper;

    public ThongKeDao(Context context) {
        dbhelper = new Dbhelper(context);
    }

    //topbanchay

    //Doanh Thu
    public int getDoanhThu(String ngayBatDau, String ngayKetThuc) {
        ngayBatDau = ngayBatDau.replace("/", "");
        ngayKetThuc = ngayKetThuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(giamua) FROM HOADON WHERE substr(ngaydat,7)||substr(ngaydat,4,2)||substr(ngaydat,1,2) between ? and ?", new String[]{ngayBatDau, ngayKetThuc});
        if (cursor.getCount() != 0 ){
            cursor.moveToFirst();
            return  cursor.getInt(0);

        }else {
            return 0;
        }
    }

}
