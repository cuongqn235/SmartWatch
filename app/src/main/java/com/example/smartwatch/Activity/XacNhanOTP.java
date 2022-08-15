package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class XacNhanOTP extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView_trangChinh;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    Button btn_xacNhanOTP;
    TextView nhanSDT;
    EditText maOTP1, maOTP2, maOTP3, maOTP4, maOTP5, maOTP6;
    String verificationId, sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhan_otp);

        Controller();
        ActionBar();
        SetEvent();
        nextTo();
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

        btn_xacNhanOTP = findViewById(R.id.btn_dangKy_xacNhanOTP);
        nhanSDT = findViewById(R.id.textView_dangKy_nhanSDT);
        maOTP1 = findViewById(R.id.editText_dangKy_OTP1);
        maOTP2 = findViewById(R.id.editText_dangKy_OTP2);
        maOTP3 = findViewById(R.id.editText_dangKy_OTP3);
        maOTP4 = findViewById(R.id.editText_dangKy_OTP4);
        maOTP5 = findViewById(R.id.editText_dangKy_OTP5);
        maOTP6 = findViewById(R.id.editText_dangKy_OTP6);

    }




    private void SetEvent() {
        verificationId = getIntent().getStringExtra("verificationId");
        sdt = getIntent().getStringExtra("phone");

        nhanSDT.setText("(+84)  " + sdt);
        btn_xacNhanOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maOTP1.getText().toString().trim().isEmpty()
                        || maOTP2.getText().toString().trim().isEmpty()
                        || maOTP3.getText().toString().trim().isEmpty()
                        || maOTP4.getText().toString().trim().isEmpty()
                        || maOTP5.getText().toString().trim().isEmpty()
                        || maOTP6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(XacNhanOTP.this, "Nhập đủ mã OTP", Toast.LENGTH_SHORT).show();
                    return;
                }
                String otp = "" + maOTP1.getText().toString().trim() + maOTP2.getText().toString().trim() + maOTP3.getText().toString().trim() + maOTP4.getText().toString().trim() + maOTP5.getText().toString().trim() + maOTP6.getText().toString().trim();
                System.out.println("=========================" + otp);
                System.out.println("==============================================" + verificationId);

                /*if (verificationId != null) {
                    btn_xacNhanOTP.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, otp);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            btn_xacNhanOTP.setVisibility(View.INVISIBLE);
                            if (task.isSuccessful()) {
                                Toast.makeText(XacNhanOTP.this, "Xác nhận thành công!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(XacNhanOTP.this, DangKy.class);
                                intent.putExtra("phone", sdt);
                                startActivity(intent);
                            } else {
                                Toast.makeText(XacNhanOTP.this, "OTP không đúng! Yêu cầu kiểm tra lại!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }*/

                Intent intent = new Intent(XacNhanOTP.this, DangKy.class);
                intent.putExtra("phone", sdt);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Xác nhận thành công!", Toast.LENGTH_LONG).show();

            }
        });
    }


    private void nextTo() {
        maOTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (maOTP1.getText().toString().length() == 1) {
                    maOTP2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        maOTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (maOTP2.getText().toString().length() == 1) {
                    maOTP3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        maOTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (maOTP3.getText().toString().length() == 1) {
                    maOTP4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        maOTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (maOTP4.getText().toString().length() == 1) {
                    maOTP5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        maOTP5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (maOTP5.getText().toString().length() == 1) {
                    maOTP6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                        Intent intent = new Intent(XacNhanOTP.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(XacNhanOTP.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(XacNhanOTP.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(XacNhanOTP.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(XacNhanOTP.this, DangNhap.class);
                        startActivity(intent4);
                        break;

                    case 5:
                        Intent intent5 = new Intent(XacNhanOTP.this, LienHe.class);
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
