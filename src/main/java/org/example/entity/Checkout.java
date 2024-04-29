package org.example.entity;

import java.util.Date;

public class Checkout {
    private int id;
    private int nhanvienId;
    private Date gioRaVe;

    public Checkout(int id, int nhanvienId, Date gioRaVe) {
        this.id = id;
        this.gioRaVe = gioRaVe;
        this.nhanvienId = nhanvienId;
    }

    public int getId() {
        return id;
    }

    public Date getGioRaVe() {
        return gioRaVe;
    }

    public int getNhanvienId() {
        return nhanvienId;
    }
}

