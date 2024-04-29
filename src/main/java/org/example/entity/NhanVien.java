package org.example.entity;

public class NhanVien extends Employee {
    private String manv;
    private int soGioLenLich;

    public NhanVien(String name, String sodt, String manv, int nhaHangId) {
        super(name, sodt, nhaHangId);
        this.manv = manv;
    }

    public NhanVien(String name, String sodt, String manv) {
        super(name, sodt);
        this.manv = manv;
    }

    public int getSoGioLenLich() {
        return soGioLenLich;
    }

    public void setSoGioLenLich(int soGioLenLich) {
        this.soGioLenLich = soGioLenLich;
    }

    public String getManv() {
        return manv;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public String toString() {
        return "Name: " + getName() + ", Phone: " + getSodt() + ", Employee ID: " + getManv();
    }
}
