package com.example.smartwatch.Object;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartwatch.Activity.GioHangActivity;
import com.example.smartwatch.Until.Database;

public class UpdateGioHang {


    public static void UpdateGioHang_SLSP(Context context, int soLuong, int maSP) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("soLuongSP", soLuong);
        SQLiteDatabase database = Database.initDatabase((Activity) context, "GioHang.db");
        database.update("GIOHANG", contentValues, "idSP=?", new String[]{maSP + ""});
    }

    public static void UpdateGioHang_giaSP(Context context, long giaSP, int maSP) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("giaSP", giaSP);
        SQLiteDatabase database = Database.initDatabase((Activity) context, "GioHang.db");
        database.update("GIOHANG", contentValues, "idSP=?", new String[]{maSP + ""});
    }

    public static void InsertGioHang(Context context, int idSP, String tenSP, long giaSP, String hinhSP, int soLuongSP) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("idSP", idSP);
        contentValues.put("tenSP", tenSP);
        contentValues.put("giaSP", giaSP);
        contentValues.put("hinhSP", hinhSP);
        contentValues.put("soLuongSP", soLuongSP);
        SQLiteDatabase database = Database.initDatabase((Activity) context, "GioHang.db");
        database.insert("GIOHANG", null, contentValues);
    }

    public static void DeleteGioHang(Context context, int idSP) {
        SQLiteDatabase database = Database.initDatabase((Activity) context, "GioHang.db");
        database.delete("GIOHANG", "idSP=?", new String[]{idSP + ""});
        GioHangActivity.list_gioHang.clear();
        Cursor cursor = database.rawQuery("Select * from GIOHANG", null);
        while (cursor.moveToNext()) {
            int maSP = cursor.getInt(0);
            String tenSP = cursor.getString(1);
            long giaSP = cursor.getLong(2);
            String hinhSP = cursor.getString(3);
            int soLuongSP = cursor.getInt(4);
            System.out.println(maSP+ " ==== " + tenSP + " ===== " + giaSP  + " ===== " + hinhSP +  " ===== " + soLuongSP);
            GioHangActivity.list_gioHang.add(new GioHang(maSP, tenSP, giaSP, hinhSP, soLuongSP));
        }
    }


}
