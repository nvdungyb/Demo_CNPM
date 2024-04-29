package org.example.controller;

import org.example.frm.LenLichLamFrm;
import org.example.frm.ThemNhanVienCaFrm;

public class StaticResouce {
    public static LenLichCtr ctr = new LenLichCtr();

    public StaticResouce() {
        ctr.listNhanVienDangKi();
    }

}
