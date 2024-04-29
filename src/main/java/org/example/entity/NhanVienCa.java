package org.example.entity;

public class NhanVienCa {
    private int id;
    private NhanVien nhanVien;
    private Ca ca;

    public NhanVienCa(NhanVien nhanVien, Ca ca) {
        this.nhanVien = nhanVien;
        this.ca = ca;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public Ca getCa() {
        return ca;
    }

    public String toString() {
        return "Employee: " + nhanVien.getName() + ", Date: " + ca.getNgay();
    }

    public int getNhanVienId(){
        return nhanVien.getId();
    }
}
