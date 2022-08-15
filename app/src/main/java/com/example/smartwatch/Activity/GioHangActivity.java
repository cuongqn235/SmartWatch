package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.smartwatch.Adapter.AdapterGioHang;
import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Object.GioHang;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.R;
import com.example.smartwatch.Until.CheckConnection;
import com.example.smartwatch.Until.Database;
import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    final String DATABASE_NAME = "GioHang.db";
    Toolbar toolbar;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;

    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    ListView listView_gioHang;
    public static ArrayList<GioHang> list_gioHang = new ArrayList<>();
    AdapterGioHang adapterGioHang;
    Button button_thanhToan;

    public static TextView textView_tongTien;
    public static TextView textView_gioTrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        Controller();

        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            SetEventGioHang();
            SetEventThanhToan();
        } else {
            CheckConnection.Check(getApplicationContext());
            finish();
        }
    }


    private void Controller() {
        toolbar = findViewById(R.id.toolbar_trangChinh);
        navigationView = findViewById(R.id.naviView_trangChinh);
        drawerLayout_trangChinh = findViewById(R.id.draLayout_trangChinh);

        listView_trangChinh = findViewById(R.id.listView_trangChinh);
        list_loaiSP = new ArrayList<>();
        adapterLoaiSP = new AdapterLoaiSP(list_loaiSP, this);
        listView_trangChinh.setAdapter(adapterLoaiSP);

        list_gioHang = new ArrayList<>();
        listView_gioHang = findViewById(R.id.listView_gioHang_danhSachSP);
        adapterGioHang = new AdapterGioHang(list_gioHang, this);
        listView_gioHang.setAdapter(adapterGioHang);
        textView_tongTien = findViewById(R.id.textView_gioHang_tongTien);
        button_thanhToan = findViewById(R.id.button_gioHang_thanhToan);
        textView_gioTrong = findViewById(R.id.textView_gioHang_gioTrong);
    }

    private void SetEventGioHang() {
        long tongTien = 0;

        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM GIOHANG ", null);

        list_gioHang.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int maSP = cursor.getInt(0);
            String tenSP = cursor.getString(1);
            long giaSP = cursor.getLong(2);
            String hinhSP = cursor.getString(3);
            int soLuongSP = cursor.getInt(4);
            tongTien += giaSP;
            list_gioHang.add(new GioHang(maSP, tenSP, giaSP, hinhSP,soLuongSP));
        }
        if(list_gioHang.isEmpty() == false){
            textView_gioTrong.setVisibility(View.INVISIBLE);
        }else{
            textView_gioTrong.setVisibility(View.VISIBLE);
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
        textView_tongTien.setText(decimalFormat.format(tongTien) + " Đ");
        adapterGioHang.notifyDataSetChanged();
    }
    private void SetEventThanhToan() {
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        button_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = database.rawQuery("SELECT * FROM User ", null);
            }
        });
    }


    @SuppressLint("RestrictedApi")
    private void ActionBar() {
        list_loaiSP.add(new LoaiSP("Trang Chủ", R.drawable.home));
        list_loaiSP.add(new LoaiSP("Đồng Hồ Chính Hãng", R.drawable.dongho));
        list_loaiSP.add(new LoaiSP("Mắt Kính Thời Trang", R.drawable.matkinh));
        list_loaiSP.add(new LoaiSP("Phụ Kiện Đồng Hồ", R.drawable.phukien));
        list_loaiSP.add(new LoaiSP("Tài Khoản", R.drawable.taikhoan));
        list_loaiSP.add(new LoaiSP("Liên hệ", R.drawable.lienhe));
        list_loaiSP.add(new LoaiSP("Thoát", R.drawable.thoat));
        adapterLoaiSP.notifyDataSetChanged();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout_trangChinh.openDrawer(GravityCompat.START);
            }
        });

        listView_trangChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(GioHangActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(GioHangActivity.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(GioHangActivity.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(GioHangActivity.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(GioHangActivity.this, DangNhap.class);
                        startActivity(intent4);
                        break;

                    case 5:
                        Intent intent5 = new Intent(GioHangActivity.this, LienHe.class);
                        startActivity(intent5);
                        break;

                    case 6:
                        finishAffinity();
                        System.exit(0);
                        break;
                }
            }
        });
    }

}