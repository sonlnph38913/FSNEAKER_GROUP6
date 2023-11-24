package com.sonlnph38913.fpoly.fsneaker.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonlnph38913.fpoly.fsneaker.database.Dbhelper;
import com.sonlnph38913.fpoly.fsneaker.model.Giay;

import java.util.ArrayList;

public class GiayDao {
    Dbhelper dbhelper;
    public GiayDao(Context context){
        dbhelper = new Dbhelper(context);
    }
    public ArrayList<Giay> getDSGiay(){
        ArrayList<Giay>list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM GIAY ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String imgName = cursor.getString(2);
            int size = cursor.getInt(3);
            int gia = cursor.getInt(4);
            int soluong = cursor.getInt(5);

            Giay g = new Giay(id,name,imgName,size,gia,soluong);
            list.add(g);
            cursor.moveToNext();
        }
        cursor.close();
        return list;

    }

}
