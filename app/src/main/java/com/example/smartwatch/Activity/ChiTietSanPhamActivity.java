package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.Object.SanPham;
import com.example.smartwatch.Object.UpdateGioHang;
import com.example.smartwatch.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    final String DATABASE_NAME = "GioHang.db";

    Toolbar toolbar;
    RecyclerView recyclerView_trangChinh;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    Spinner spinner_soLuong;
    TextView textView_tenSP, textView_giaSP, textView_loaiSP, textView_kieuSP, textView_soLuongTon, textView_moTaSP;
    ImageView imageView_hinhSP;
    Button button_themGioHang;

    SanPham sanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sp);

        Controller();
        ActionBar();
        SetEvent();
        CatchEventSpinner();
        SetEventButton();
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

        spinner_soLuong = findViewById(R.id.spinner_chiTietSP_soLuong);
        textView_giaSP = findViewById(R.id.textView_chiTietSP_giaSP);
        textView_tenSP = findViewById(R.id.textView_chiTietSP_tenSP);
        textView_kieuSP = findViewById(R.id.textView_chiTietSP_kieuSP);
        textView_loaiSP = findViewById(R.id.textView_chiTietSP_loaiSP);
        textView_soLuongTon = findViewById(R.id.textView_chiTietSP_soLuongTon);
        textView_moTaSP = findViewById(R.id.textView_chiTietSP_moTaSP);
        imageView_hinhSP = findViewById(R.id.image_chiTiet_hinhSP);
        button_themGioHang = findViewById(R.id.button_chiTietSP_themGioHang);

    }


    private void SetEvent() {
        sanPham = getIntent().getParcelableExtra("sanpham");

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textView_giaSP.setText("Giá: " + decimalFormat.format(sanPham.getGiaSP()) + " Đ");
        textView_tenSP.setText(sanPham.getTenSP());
        textView_loaiSP.setText(sanPham.getLoaiSP());
        textView_kieuSP.setText(sanPham.getKieuSP());
        textView_soLuongTon.setText(sanPham.getSoLuongSP() + "");
        if (sanPham.getMotaSP().equals("null")) {
            textView_moTaSP.setText(sanPham.getTenSP());
        } else {
            textView_moTaSP.setText(sanPham.getMotaSP());
        }
        Picasso.get().load(sanPham.getHinhSP()).placeholder(R.drawable.noimage).error(R.drawable.error).into(imageView_hinhSP);
    }

    private void SetEventButton() {

        button_themGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (GioHangActivity.list_gioHang.size() > 0) {
                    int soLuong = Integer.parseInt(spinner_soLuong.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0; i < GioHangActivity.list_gioHang.size(); i++) {
                        if (GioHangActivity.list_gioHang.get(i).getIdSP() == sanPham.getIdSP()) {
                            soLuong += GioHangActivity.list_gioHang.get(i).getSoLuongSP();
                            if (GioHangActivity.list_gioHang.get(i).getSoLuongSP() >= 10) {
                                soLuong = 10;
                            }
                            UpdateGioHang.UpdateGioHang_SLSP(ChiTietSanPhamActivity.this,soLuong,sanPham.getIdSP());
                            UpdateGioHang.UpdateGioHang_giaSP(ChiTietSanPhamActivity.this,sanPham.getGiaSP()*soLuong,sanPham.getIdSP());
                            exists = true;
                        }
                    }
                    if (exists == false) {
                        int soLuong1 = Integer.parseInt(spinner_soLuong.getSelectedItem().toString());
                        long giaMoi = soLuong1 * sanPham.getGiaSP();
                        UpdateGioHang.InsertGioHang(ChiTietSanPhamActivity.this,sanPham.getIdSP(),sanPham.getTenSP(),giaMoi,sanPham.getHinhSP(),soLuong1);
                    }
                } else {
                    int soLuong = Integer.parseInt(spinner_soLuong.getSelectedItem().toString());
                    long giaMoi = soLuong * sanPham.getGiaSP();
                    UpdateGioHang.InsertGioHang(ChiTietSanPhamActivity.this,sanPham.getIdSP(),sanPham.getTenSP(),giaMoi,sanPham.getHinhSP(),soLuong);
                }
                Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
    }




    private void CatchEventSpinner() {
        Integer[] soLuong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> spinner = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, soLuong);
        spinner_soLuong.setAdapter(spinner);
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
                        Intent intent = new Intent(ChiTietSanPhamActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(ChiTietSanPhamActivity.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(ChiTietSanPhamActivity.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(ChiTietSanPhamActivity.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(ChiTietSanPhamActivity.this, DangNhap.class);
                        startActivity(intent4);
                        break;

                    case 5:
                        Intent intent5 = new Intent(ChiTietSanPhamActivity.this, LienHe.class);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_gioHang:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
