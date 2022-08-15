package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView_trangChinh;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    Button dangKy, dangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        Controller();
        ActionBar();
        SetEvent();
    }


    private void Controller() {
        toolbar = findViewById(R.id.toolbar_trangChinh);
        recyclerView_trangChinh = findViewById(R.id.recyc_spMoiNhat);
        navigationView = findViewById(R.id.naviView_trangChinh);
        listView_trangChinh = findViewById(R.id.listView_trangChinh);
        drawerLayout_trangChinh = findViewById(R.id.draLayout_trangChinh);

        list_loaiSP = new ArrayList<>();
        adapterLoaiSP = new AdapterLoaiSP(list_loaiSP, this);
        listView_trangChinh.setAdapter(adapterLoaiSP);

        dangKy = findViewById(R.id.btn_dangNhap_dangKy);
    }

    private void SetEvent() {
        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, DangKyOTP.class);
                startActivity(intent);
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
                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(DangNhap.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(DangNhap.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(DangNhap.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(DangNhap.this, DangNhap.class);
                        startActivity(intent4);
                        break;

                    case 5:
                        Intent intent5 = new Intent(DangNhap.this, LienHe.class);
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
