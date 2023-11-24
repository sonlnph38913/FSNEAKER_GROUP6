package com.sonlnph38913.fpoly.fsneaker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.sonlnph38913.fpoly.fsneaker.database.Dbhelper;

public class QuanLyDao {
    Dbhelper dbhelper;
    public QuanLyDao(Context context){dbhelper = new Dbhelper(context);}
    public boolean checkLogin(String maql,String matkhau){
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM QUANLY WHERE maql = ? AND matkhau = ?",new String[]{maql,matkhau});
        if (cursor.getCount() != 0){
            return true;

        }else {
            return false;
        }
    }
    public boolean Register(String username, String password, String hoten) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maql", username);
        contentValues.put("matkhau", password);
        contentValues.put("hoten", hoten);

        long check = sqLiteDatabase.insert("QUANLY", null, contentValues);
        if (check != -1) {
            return true;

        }
        return false;


    }
    public int capNhapMatKhau(String username,String oldPass,String newPass){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM QUANLY WHERE maql = ? and matkhau = ?", new String[]{username,oldPass});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
            long check =  sqLiteDatabase.update("QUANLY",contentValues,"maql = ?",new String[]{username});
            if (check == -1)
                return -1;
            return 1;
        }
        return 0;
    }

}
