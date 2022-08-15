package com.example.smartwatch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwatch.Activity.ChiTietSanPhamActivity;
import com.example.smartwatch.Object.SanPham;
import com.example.smartwatch.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterSanPhamMoiNhat extends RecyclerView.Adapter<AdapterSanPhamMoiNhat.ItemHoldle> {

    Context context;
    ArrayList<SanPham> list_sanPham;

    public AdapterSanPhamMoiNhat(Context context, ArrayList<SanPham> list_sanPham) {
        this.context = context;
        this.list_sanPham = list_sanPham;
    }

    @Override
    public ItemHoldle onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_sanpham_moinhat,null);
        ItemHoldle itemHodle = new ItemHoldle(v);
        return itemHodle;
    }

    @Override
    public void onBindViewHolder(ItemHoldle holder, int position) {
        SanPham sanPham = list_sanPham.get(position);
        holder.txt_tenSanPham.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txt_giaSanPham.setText("Giá: " + decimalFormat.format(sanPham.getGiaSP()) + " Đ");
        Picasso.get().load(sanPham.getHinhSP()).placeholder(R.drawable.noimage).error(R.drawable.error).into(holder.hinhSanPham);
    }

    @Override
    public int getItemCount() {
        return list_sanPham.size();
    }

    public class ItemHoldle extends RecyclerView.ViewHolder{
        public ImageView hinhSanPham;
        public TextView txt_tenSanPham, txt_giaSanPham;
       public ItemHoldle(View itemView) {
           super(itemView);
           hinhSanPham = itemView.findViewById(R.id.img_SanPhamMoiNhat_hinhSP);
           txt_tenSanPham = itemView.findViewById(R.id.txt_SanPhamMoiNhat_tenSP);
           txt_giaSanPham = itemView.findViewById(R.id.txt_SanPhamMoiNhat_giaSP);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                   intent.putExtra("sanpham",list_sanPham.get(getPosition()));
                   context.startActivity(intent);
               }
           });
       }
   }
}
