package com.example.smartwatch.Object;

import android.os.Parcel;
import android.os.Parcelable;

public class SanPham implements Parcelable {
    public int idSP;
    public String tenSP;
    public int giaSP;
    public String motaSP;
    public int idLoaiSP;
    public String loaiSP;
    public int idkieuSP;
    public String kieuSP;
    public String hinhSP;
    public int soLuongSP;


    public SanPham(int idSP, String tenSP, int giaSP, String motaSP, int idLoaiSP, String loaiSP,int idkieuSP, String kieuSP, String hinhSP, int soLuongSP) {
        this.idSP = idSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.motaSP = motaSP;
        this.idLoaiSP = idLoaiSP;
        this.loaiSP = loaiSP;
        this.idkieuSP = idkieuSP;
        this.kieuSP = kieuSP;
        this.hinhSP = hinhSP;
        this.soLuongSP = soLuongSP;
    }

    protected SanPham(Parcel in) {
        idSP = in.readInt();
        tenSP = in.readString();
        giaSP = in.readInt();
        motaSP = in.readString();
        idLoaiSP = in.readInt();
        loaiSP = in.readString();
        idkieuSP = in.readInt();
        kieuSP = in.readString();
        hinhSP = in.readString();
        soLuongSP = in.readInt();
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };

    public int getIdkieuSP() {
        return idkieuSP;
    }

    public void setIdkieuSP(int idkieuSP) {
        this.idkieuSP = idkieuSP;
    }

    public String getKieuSP() {
        return kieuSP;
    }

    public void setKieuSP(String kieuSP) {
        this.kieuSP = kieuSP;
    }

    public int getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(int idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public String getMotaSP() {
        return motaSP;
    }

    public void setMotaSP(String motaSP) {
        this.motaSP = motaSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idSP);
        dest.writeString(tenSP);
        dest.writeInt(giaSP);
        dest.writeString(motaSP);
        dest.writeInt(idLoaiSP);
        dest.writeString(loaiSP);
        dest.writeInt(idkieuSP);
        dest.writeString(kieuSP);
        dest.writeString(hinhSP);
        dest.writeInt(soLuongSP);
    }
}
