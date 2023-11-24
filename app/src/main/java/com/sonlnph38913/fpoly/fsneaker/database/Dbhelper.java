package com.sonlnph38913.fpoly.fsneaker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(Context context){
        super(context,"FSNEAKER",null,7);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbQuanLy = "CREATE TABLE QUANLY(maql text primary key,hoten text,matkhau text)";
        sqLiteDatabase.execSQL(dbQuanLy);
        String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key autoincrement, hoten text,namsinh text,anh text)";
        sqLiteDatabase.execSQL(dbThanhVien);
        String dbLoai = "CREATE TABLE LOAIGIAY(maloai integer primary key autoincrement,  tenloai text)";
        sqLiteDatabase.execSQL(dbLoai);
        String dbGiay = "CREATE TABLE GIAY(magiay integer primary key autoincrement,tengiay text,hinhanh text, size integer,giaban integer,maloai integer references LOAIGIAY(maloai),soluong integer )";
        sqLiteDatabase.execSQL(dbGiay);
        String dbHoaDon = "CREATE TABLE HOADON(mahd integer primary key autoincrement,matv integer references THANHVIEN(matv),maql text references QUANLY(maql),magiay integer references GIAY(magiay),ngaydat text,thanhtoan integer,hinhanh integer,giamua integer)";
        sqLiteDatabase.execSQL(dbHoaDon);

        sqLiteDatabase.execSQL("INSERT INTO QUANLY VALUES ('admin1','Lê Nam Sơn','123'),('admin2','Đặng Tuấn Nghĩa','123'),('admin3','Trần Việt Hoàng','123')");
        sqLiteDatabase.execSQL("INSERT INTO THANHVIEN VALUES (1,'Đặng Huy Tuấn','2000','mem1'),(2,'Nguyễn Văn Hải','2001','mem2'),(3,'Chu Việt Dũng','2002','mem3')");
        sqLiteDatabase.execSQL("INSERT INTO LOAIGIAY VALUES (1,'NIKE'),(2,'ADIDAS'),(3,'VANS'),(4,'PUMA')");
        sqLiteDatabase.execSQL("INSERT INTO GIAY VALUES (1,'NIKE AIR MAX','avatar5',42,200000,1,20),(2,'ADIDAS ALPHA','avatar4',41,250000,2,20),(3,'VANS OLD','avatar2',42,300000,3,20),(4,'PUMA ULTRA','avatar3',42,500000,4,20)");
        sqLiteDatabase.execSQL("INSERT INTO HOADON VALUES (1,1,'admin1',1,'1/1/2020',0,1,200000),(2,2,'admin2',2,'2/2/2023',2,1,300000),(3,3,'admin3',3,'7/9/2021',2,2,500000)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    if (i != i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS QUANLY");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAIGIAY");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS GIAY");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS HOADON");
        onCreate(sqLiteDatabase);
    }

    }
}
