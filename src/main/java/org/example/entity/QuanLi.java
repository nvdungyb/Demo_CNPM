package org.example.entity;

public class QuanLi extends Employee {
    private String maql;

    public QuanLi(String name, String sodt, String maql, int nhaHangId) {
        super(name, sodt, nhaHangId);
        this.maql = maql;
    }

    public String getMaql() {
        return maql;
    }
}
