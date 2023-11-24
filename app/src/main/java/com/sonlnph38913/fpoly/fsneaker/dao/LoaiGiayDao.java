package com.sonlnph38913.fpoly.fsneaker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonlnph38913.fpoly.fsneaker.database.Dbhelper;
import com.sonlnph38913.fpoly.fsneaker.model.LoaiGiay;

import java.util.ArrayList;

public class LoaiGiayDao {
    Dbhelper dbhelper;
    public LoaiGiayDao(Context context){dbhelper = new Dbhelper(context);}

    public ArrayList<LoaiGiay> getDSLoaiGiay(){
        ArrayList<LoaiGiay>list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAIGIAY",null);
        if (cursor.getCount() != 0 ){
            cursor.moveToFirst();
            do {
                list.add(new LoaiGiay(cursor.getInt(0),cursor.getString(1)));

            }while (cursor.moveToNext());

        }
        return list;
    }
    public boolean themLoaiGiay(String tenloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenloai);
        long check = sqLiteDatabase.insert("LOAIGIAY", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }
    public int xoaLoaiGiay(int maloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM GIAY WHERE maloai = ?", new String[]{String.valueOf(maloai)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("LOAIGIAY", "maloai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1)
            return 0;
        return 1;
    }
    public boolean thayDoiLoaiGiay(int maloai,String tenloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenloai);
        long check = sqLiteDatabase.update("LOAIGIAY", contentValues, "maloai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1)
            return false;
        return true;
    }
}
