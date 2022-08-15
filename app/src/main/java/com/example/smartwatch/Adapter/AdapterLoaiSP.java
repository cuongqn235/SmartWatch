package com.example.smartwatch.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.R;

import java.util.ArrayList;

public class AdapterLoaiSP extends BaseAdapter {
    ArrayList<LoaiSP> arrayListLoaiSP;
    Context context;

    public AdapterLoaiSP(ArrayList<LoaiSP> arrayListLoaiSP, Context context) {
        this.arrayListLoaiSP = arrayListLoaiSP;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaiSP.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListLoaiSP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_row_loaisp, null);
        ImageView image_loaiSP = row.findViewById(R.id.image_loaiSP);
        TextView textViewLoaiSP = row.findViewById(R.id.textView_loaiSP);

        LoaiSP loaiSP = arrayListLoaiSP.get(position);
        textViewLoaiSP.setText(loaiSP.tenLoai);
        image_loaiSP.setImageResource(loaiSP.himh);

        return row;
    }
}
