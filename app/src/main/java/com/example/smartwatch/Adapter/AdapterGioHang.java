package com.example.smartwatch.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartwatch.Activity.GioHangActivity;
import com.example.smartwatch.Object.GioHang;
import com.example.smartwatch.Object.UpdateGioHang;
import com.example.smartwatch.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterGioHang extends BaseAdapter {
    ArrayList<GioHang> arrayGioHang;
    Context context;

    public AdapterGioHang(ArrayList<GioHang> arrayGioHang, Context context) {
        this.arrayGioHang = arrayGioHang;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayGioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_row_giohang, null);
        ImageView image_sanPham = row.findViewById(R.id.image_gioHang_hinhSP);
        TextView textView_tenSP = row.findViewById(R.id.textView_gioHang_tenSP);
        TextView textView_giaSP = row.findViewById(R.id.textView_gioHang_giaSP);
        TextView textView_soLuongSP = row.findViewById(R.id.textView_gioHang_soLuongSP);
        Button button_cong = row.findViewById(R.id.button_gioHang_soLuongSP_cong);
        Button button_tru = row.findViewById(R.id.button_gioHang_soLuongSP_tru);
        Button button_delete = row.findViewById(R.id.button_gioHang_xoaSP);


        GioHang gioHang = arrayGioHang.get(position);
        textView_tenSP.setText(gioHang.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textView_giaSP.setText("Giá: " + decimalFormat.format(gioHang.getGiaSP()) + " Đ");
        Picasso.get().load(gioHang.getHinhSP()).placeholder(R.drawable.noimage).error(R.drawable.error).into(image_sanPham);
        textView_soLuongSP.setText(gioHang.getSoLuongSP() + "");

        if (gioHang.getSoLuongSP() >= 10) {
            button_cong.setVisibility(View.INVISIBLE);
        }
        if (gioHang.getSoLuongSP() <= 1) {
            button_tru.setVisibility(View.INVISIBLE);
        }


        button_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long giaMoi = (gioHang.getGiaSP()/gioHang.getSoLuongSP());
                gioHang.setSoLuongSP(gioHang.getSoLuongSP() + 1);
                if (gioHang.getSoLuongSP() == 10) {
                    button_cong.setVisibility(v.INVISIBLE);
                }
                if (gioHang.getSoLuongSP() > 1) {
                    button_tru.setVisibility(v.VISIBLE);
                }
                textView_soLuongSP.setText(gioHang.getSoLuongSP() + "");
                giaMoi *= gioHang.getSoLuongSP();
                gioHang.setGiaSP(giaMoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
                textView_giaSP.setText("Giá: " + decimalFormat.format(gioHang.getGiaSP()) + " Đ");
                UpdateGioHang.UpdateGioHang_giaSP(context,giaMoi,gioHang.idSP);
                UpdateGioHang.UpdateGioHang_SLSP(context,gioHang.getSoLuongSP(),gioHang.getIdSP());

                setEvent(v);
            }
        });


        button_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long giaMoi = (gioHang.getGiaSP()/gioHang.getSoLuongSP());
                gioHang.setSoLuongSP(gioHang.getSoLuongSP() - 1);
                if (gioHang.getSoLuongSP() < 10) {
                    button_cong.setVisibility(v.VISIBLE);
                }
                if (gioHang.getSoLuongSP() == 1) {
                    button_tru.setVisibility(v.INVISIBLE);
                }
                textView_soLuongSP.setText(gioHang.getSoLuongSP() + "");
                giaMoi *= gioHang.getSoLuongSP();
                gioHang.setGiaSP(giaMoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
                textView_giaSP.setText("Giá: " + decimalFormat.format(gioHang.getGiaSP()) + " Đ");
                UpdateGioHang.UpdateGioHang_giaSP(context,giaMoi,gioHang.idSP);
                UpdateGioHang.UpdateGioHang_SLSP(context,gioHang.getSoLuongSP(),gioHang.getIdSP());

                setEvent(v);
            }
        });


        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateGioHang.DeleteGioHang(context,gioHang.idSP);
                setEvent(v);
                notifyDataSetChanged();
            }
        });


        return row;
    }

    private void setEvent(View v){
        long tongTien = 0;
        if(GioHangActivity.list_gioHang.isEmpty() == false){
            for(int i = 0; i < GioHangActivity.list_gioHang.size(); i++){
                tongTien += GioHangActivity.list_gioHang.get(i).getGiaSP();
            }
            GioHangActivity.textView_gioTrong.setVisibility(v.INVISIBLE);
        }else{
            GioHangActivity.textView_gioTrong.setVisibility(v.VISIBLE);
        }


        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        GioHangActivity.textView_tongTien.setText(decimalFormat.format(tongTien) + " Đ");
    }
}
