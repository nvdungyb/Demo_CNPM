package org.example.entity;

public class Employee {
    private int id;
    private String name;
    private String sodt;
    private int nhaHangid;

    public Employee(String name, String sodt, int nhaHangId) {
        this.name = name;
        this.sodt = sodt;
        this.nhaHangid = nhaHangId;
    }

    public Employee(String name, String sodt) {
        this.name = name;
        this.sodt = sodt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSodt() {
        return sodt;
    }

    public int getNhaHangId() {
        return nhaHangid;
    }

    public void setId(int id) {
        this.id = id;
    }
}
