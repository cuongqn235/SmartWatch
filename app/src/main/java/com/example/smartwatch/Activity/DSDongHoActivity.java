package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Adapter.AdapterSanPham;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.Object.SanPham;
import com.example.smartwatch.R;
import com.example.smartwatch.Until.CheckConnection;
import com.example.smartwatch.Until.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DSDongHoActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;

    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    ListView listView_sanPham;
    ArrayList<SanPham> list_sanPham;
    AdapterSanPham adapterSanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham);

        Controller();

        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            duLieuSanPham();
            SetEvent();
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


        list_sanPham = new ArrayList<>();
        listView_sanPham = findViewById(R.id.listView_sanPham_listSP);
        adapterSanPham = new AdapterSanPham(list_sanPham, this);
        listView_sanPham.setAdapter(adapterSanPham);

    }

    private void duLieuSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.samPham;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {

                            // Khởi tạo biến toàn cục của sản phẩm
                            String tenSP = "", motaSP = "", loaiSP = "", hinhSP = "";
                            int idSP = 0, giaSP = 0, soLuongSP = 0;

                            for (int i = response.length() - 1; i >= 0 ; i--) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    idSP = jsonObject.getInt("idSP");
                                    tenSP = jsonObject.getString("tenSP");
                                    giaSP = jsonObject.getInt("gia");
                                    motaSP = jsonObject.getString("moTa");
                                    JSONObject loai = jsonObject.getJSONObject("loaiSP");
                                    int idLoaiSP = loai.getInt("idLoaiSP");
                                    loaiSP = loai.getString("tenLoai");
                                    JSONObject kieu = jsonObject.getJSONObject("kieusp");
                                    int idKieuSP = kieu.getInt("idKieusp");
                                    String kieuSP = kieu.getString("tenKieu");
                                    hinhSP = jsonObject.getString("anhSP");
                                    soLuongSP = jsonObject.getInt("soLuongTon");
                                    if(idLoaiSP == 1 && soLuongSP > 0) {
                                        list_sanPham.add(new SanPham(idSP, tenSP, giaSP, motaSP, idLoaiSP, loaiSP,idKieuSP,kieuSP, hinhSP, soLuongSP));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapterSanPham.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("============Lỗi rồi===============" + error);

            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    private void SetEvent() {
        listView_sanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DSDongHoActivity.this, ChiTietSanPhamActivity.class);
                intent.putExtra("sanpham",list_sanPham.get(position));
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
                        Intent intent = new Intent(DSDongHoActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(DSDongHoActivity.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(DSDongHoActivity.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(DSDongHoActivity.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(DSDongHoActivity.this, DangNhap.class);
                        startActivity(intent4);
                        break;

                    case 5:
                        Intent intent5 = new Intent(DSDongHoActivity.this, LienHe.class);
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