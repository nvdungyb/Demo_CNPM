package org.example.entity;

import java.util.Date;

public class Checkin {
    private int id;
    private int nhanVienId;
    private Date gioVaoLam;

    public Checkin(int id, int nhanVienId, Date gioVaoLam) {
        this.id = id;
        this.gioVaoLam = gioVaoLam;
        this.nhanVienId = nhanVienId;
    }

    public int getId() {
        return id;
    }

    public Date getGioVaoLam() {
        return gioVaoLam;
    }

    public int getNhanVienId() {
        return nhanVienId;
    }
}
