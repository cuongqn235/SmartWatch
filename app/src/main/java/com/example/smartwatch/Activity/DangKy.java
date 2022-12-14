package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class DangKy extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView_trangChinh;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    Button btn_DangKy;
    EditText edt_matKhau1, edt_matKhau2, edt_email, edt_diaChi;
    TextView tv_sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky_taikhoan);

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

        btn_DangKy = findViewById(R.id.btn_dangKy_taiKhoan);
        tv_sdt = findViewById(R.id.editText_damgKy_taiKhoan_sdt);
        edt_diaChi = findViewById(R.id.editText_damgKy_taiKhoan_diaChi);
        edt_email = findViewById(R.id.editText_damgKy_taiKhoan_email);
        edt_matKhau1 = findViewById(R.id.editText_damgKy_taiKhoan_matKhau1);
        edt_matKhau2 = findViewById(R.id.editText_damgKy_taiKhoan_matKhau2);

        String sdt = getIntent().getStringExtra("phone");
        tv_sdt.setText(sdt);
    }

    private void SetEvent() {
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_matKhau1.getText().toString().isEmpty()){
                    Toast.makeText(DangKy.this, "Nh???p m???t kh???u!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edt_matKhau1.getText().toString().length() < 8){
                    Toast.makeText(DangKy.this, "M???t kh???u ph???i c?? 8 k?? t??? tr??? l??n!",Toast.LENGTH_SHORT).show();
                    return;
                }
                String matKhau1 = edt_matKhau1.getText().toString();
                String matKhau2 = edt_matKhau2.getText().toString();
                if(matKhau1.equals(matKhau2) == false){
                    Toast.makeText(DangKy.this, "M???t kh???u kh??ng ch??nh x??c!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edt_email.getText().toString().trim().isEmpty()){
                    Toast.makeText(DangKy.this, "Nh???p Email!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edt_diaChi.getText().toString().trim().isEmpty()){
                    Toast.makeText(DangKy.this, "Nh???p ?????a ch???!",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "????ng k?? th??nh c??ng!",Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("RestrictedApi")
    private void ActionBar() {
        list_loaiSP.add(new LoaiSP("Trang Ch???", R.drawable.home));
        list_loaiSP.add(new LoaiSP("?????ng H??? Ch??nh H??ng", R.drawable.dongho));
        list_loaiSP.add(new LoaiSP("M???t K??nh Th???i Trang", R.drawable.matkinh));
        list_loaiSP.add(new LoaiSP("Ph??? Ki???n ?????ng H???", R.drawable.phukien));
        list_loaiSP.add(new LoaiSP("T??i Kho???n", R.drawable.taikhoan));
        list_loaiSP.add(new LoaiSP("Li??n h???", R.drawable.lienhe));
        list_loaiSP.add(new LoaiSP("Tho??t", R.drawable.thoat));
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
                        Intent intent = new Intent(DangKy.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(DangKy.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(DangKy.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(DangKy.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(DangKy.this, DangNhap.class);
                        startActivity(intent4);
                        break;

                    case 5:
                        Intent intent5 = new Intent(DangKy.this, LienHe.class);
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
