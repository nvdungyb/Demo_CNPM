package org.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LichLamViec {
    private int id;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int nhaHangId;
    private QuanLi quanLi;
    private HashMap<Date, List<NhanVienCa>> nhanvienCa = new HashMap<>();

    public LichLamViec(int id, Date ngayBatDau, Date ngayKetThuc, int nhaHangId, QuanLi quanLi) {
        this.id = id;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.nhaHangId = nhaHangId;
        this.quanLi = quanLi;
    }

    public int getId() {
        return id;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public int getNhaHangId() {
        return nhaHangId;
    }

    public QuanLi getQuanLiId() {
        return quanLi;
    }
}
