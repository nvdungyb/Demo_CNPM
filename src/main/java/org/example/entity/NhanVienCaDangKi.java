package org.example.entity;

public class NhanVienCaDangKi {
    private int id;
    private NhanVien nhanVien;
    private Ca ca;

    public NhanVienCaDangKi(int id, NhanVien nhanVien, Ca ca) {
        this.id = id;
        this.nhanVien = nhanVien;
        this.ca = ca;
    }

    public int getId() {
        return id;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public Ca getCa() {
        return ca;
    }

}
