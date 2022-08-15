package com.example.smartwatch.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartwatch.Object.SanPham;
import com.example.smartwatch.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterSanPham extends BaseAdapter {
    ArrayList<SanPham> arrayListSP;
    Context context;

    public AdapterSanPham(ArrayList<SanPham> arrayListSP, Context context) {
        this.arrayListSP= arrayListSP;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListSP.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListSP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_row_sanpham, null);
        ImageView image_sanPham = row.findViewById(R.id.image_sanPham_hinhSP);
        TextView textView_tenSP = row.findViewById(R.id.textView_sanPham_tenSP);
        TextView textView_giaSP = row.findViewById(R.id.textView_sanPham_giaSP);

        SanPham sanPham = arrayListSP.get(position);
        textView_tenSP.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textView_giaSP.setText("Giá: " + decimalFormat.format(sanPham.getGiaSP()) + " Đ");
        Picasso.get().load(sanPham.getHinhSP()).placeholder(R.drawable.noimage).error(R.drawable.error).into(image_sanPham);

        return row;
    }
}
