package org.example.entity;

import java.time.LocalDate;

enum Buoi {
    SANG(1), TOI(2);
    public int value;

    Buoi(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public class Ca {
    private int id;
    private Buoi buoi;
    private LocalDate ngay;

    public Ca(int id, int ca, LocalDate ngay) {
        this.id = id;
        this.buoi = (ca == 1 ? Buoi.SANG : Buoi.TOI);
        this.ngay = ngay;
    }

    public int getId() {
        return id;
    }

    public int getBuoi() {
        return buoi.getValue();
    }

    public LocalDate getNgay() {
        return ngay;
    }
}
