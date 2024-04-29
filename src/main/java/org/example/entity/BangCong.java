package org.example.entity;

public class BangCong {
    private int id;
    private NhanVien nhanVien;
    private int tongGioLamTrongCa;
    private int tongGioLamThem;
    private int tongGioDiMuonVeSom;

    public BangCong(int id, NhanVien nhanVien, int tongGioLamTrongCa, int tongGioLamThem, int tongGioDiMuonVeSom) {
        this.id = id;
        this.nhanVien = nhanVien;
        this.tongGioLamTrongCa = tongGioLamTrongCa;
        this.tongGioLamThem = tongGioLamThem;
        this.tongGioDiMuonVeSom = tongGioDiMuonVeSom;
    }

    public int getId() {
        return id;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public int getTongGioLamTrongCa() {
        return tongGioLamTrongCa;
    }

    public int getTongGioLamThem() {
        return tongGioLamThem;
    }

    public int getTongGioDiMuonVeSom() {
        return tongGioDiMuonVeSom;
    }
}
