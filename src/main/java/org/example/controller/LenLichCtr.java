package org.example.controller;

import org.example.entity.*;
import org.example.frm.LenLichLamFrm;
import org.example.frm.ThemNhanVienCaFrm;
import org.example.frm.ThongBaoFrm;
import org.example.frm.TrangChuFrm;

import javax.swing.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.example.controller.StaticResouce.ctr;

public class LenLichCtr {
    private static Connection connection;
    private HashMap<LocalDate, ArrayList<NhanVienCaDangKi>> dsNhanVienCaDangKi = new HashMap<>();
    private HashMap<String, NhanVien> dsNhanVien = new HashMap<>();
    private HashMap<LocalDate, ArrayList<NhanVienCa>> dsNhanVienCa = new HashMap<>();

    private LenLichLamFrm lenLichLamFrm;
    private ThemNhanVienCaFrm themNhanVienCaFrm;
    private ThongBaoFrm thongBaoFrm;
    private boolean isSaved;

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/demoBTL";
        String drive = "com.mysql.cj.jdbc.Driver";

        try {
//            Class.forName(drive);
            connection = DriverManager.getConnection(url, "root", "Dung3032003_135709");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public Employee adEmployee(String name, String sodt, String manv, int nhaHangId) {
        Employee employee = new Employee(name, sodt, nhaHangId);

        try {
            connection = getConnection();
            connection.setAutoCommit(false);    // Tắt auto-commit để bắt đầu giao dịch.

            String sql = "INSERT INTO demoBTL.employee (name, sodt, nhaHangId) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, sodt);
            preparedStatement.setInt(3, nhaHangId);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int employeeId = 0;
            if (generatedKeys.next()) {
                employeeId = generatedKeys.getInt(1);
            }

            System.out.println(employeeId);
            if (manv.substring(0, 2).equals("NV")) {
                sql = "INSERT INTO demoBTL.nhanvien(employeeid, manv)  VALUES (?, ?)";
            } else {
                sql = "INSERT INTO demoBTL.quanli(employeeid, maql)  VALUES (?, ?)";
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setString(2, manv);
            preparedStatement.executeUpdate();

            connection.commit();    // Commit vào cơ sở dữ liệu.
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null)
                try {
                    connection.rollback();         // Nếu có lỗi thì rollback lại giao dịch
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            return null;
        }

        System.out.println(employee);
        return employee;
    }

    public void dangKiCaLam(int nhanVienId, int caLamId, int quanLiId) {

        try {
            connection = getConnection();
            connection.setAutoCommit(false);    // Tắt auto-commit để bắt đầu giao dịch.

            String sql = "INSERT INTO demoBTL.nhanviencadangki (nhanvienid, caid, quanliid) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, nhanVienId);
            preparedStatement.setInt(2, caLamId);
            preparedStatement.setInt(3, quanLiId);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int id = 0;
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            System.out.println(id);

            connection.commit();    // Commit vào cơ sở dữ liệu.
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null)
                try {
                    connection.rollback();         // Nếu có lỗi thì rollback lại giao dịch
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
        }
    }

    public void getDanhSachNhanVienDangKi() {
        try {
            connection = getConnection();
            String sql = "SELECT nhanvienid, manv, name, sodt, caid, buoi, ngay " +
                    "FROM demoBTL.employee " +
                    "INNER JOIN nhanvien on employee.id = nhanvien.employeeid " +
                    "INNER JOIN demoBTL.nhanviencadangki dk on employee.id = dk.nhanvienid " +
                    "INNER JOIN demoBTL.ca on ca.id = dk.caid " +
                    "WHERE ngay >= '" + getFirstDayOfWeek() + "' AND ngay <= '" + getLastDayOfWeek() + "'";

            System.out.println(sql);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int nhanVienId = resultSet.getInt("nhanvienid");
                String name = resultSet.getString("name");
                String manv = resultSet.getString("manv");
                String sodt = resultSet.getString("sodt");

                NhanVien nhanVien = new NhanVien(name, sodt, manv);
                nhanVien.setId(nhanVienId);
                if (dsNhanVien.containsKey(manv)) {
                    nhanVien = dsNhanVien.get(manv);
                    nhanVien.setSoGioLenLich(nhanVien.getSoGioLenLich() + 8);
                    dsNhanVien.replace(manv, nhanVien);
                } else {
                    nhanVien.setSoGioLenLich(8);
                    dsNhanVien.put(manv, nhanVien);
                }

                int caId = resultSet.getInt("caid");
                int buoi = resultSet.getInt("buoi");
                LocalDate ngay = resultSet.getDate("ngay").toLocalDate();
                Ca ca = new Ca(caId, buoi, ngay);

                NhanVienCaDangKi nhanVienCaDangKi = new NhanVienCaDangKi(nhanVienId, dsNhanVien.get(manv), ca);
                if (dsNhanVienCaDangKi.containsKey(ngay)) {
                    dsNhanVienCaDangKi.get(ngay).add(nhanVienCaDangKi);
                } else {
                    ArrayList<NhanVienCaDangKi> list = new ArrayList<>();
                    list.add(nhanVienCaDangKi);
                    dsNhanVienCaDangKi.put(ngay, list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void themNhanVienCa(NhanVienCa nhanVienCa) {
        LocalDate date = nhanVienCa.getCa().getNgay();
        if (dsNhanVienCa.containsKey(date)) {
            dsNhanVienCa.get(date).add(nhanVienCa);
        } else {
            ArrayList<NhanVienCa> list = new ArrayList<>();
            list.add(nhanVienCa);
            dsNhanVienCa.put(date, list);
        }
    }

    public void updateDsNhanVienCaDangKi(Ca ca) {
        if (dsNhanVienCa.size() == 0 || ca == null) {
            return;
        }
        System.out.println("Update: " + ca.getNgay());

        // Lặp qua danh sách NhanVienCa trong ngày được chỉ định
        for (NhanVienCa nhanVienCa : dsNhanVienCa.get(ca.getNgay())) {
            // Lặp qua danh sách NhanVienCaDangKi trong ngày được chỉ định
            Iterator<NhanVienCaDangKi> iterator = dsNhanVienCaDangKi.get(ca.getNgay()).iterator();
            while (iterator.hasNext()) {
                NhanVienCaDangKi nhanVienCaDangKi = iterator.next();
                // Kiểm tra điều kiện loại bỏ
                if (nhanVienCa.getNhanVien().getManv().equals(nhanVienCaDangKi.getNhanVien().getManv()) && nhanVienCa.getCa().getBuoi() == nhanVienCaDangKi.getCa().getBuoi()) {
                    // Loại bỏ phần tử
                    iterator.remove();
                    System.out.println("removed " + nhanVienCaDangKi.getNhanVien().getManv() + " " + nhanVienCaDangKi.getCa().getBuoi());
                }
            }
        }

        System.out.println(dsNhanVienCaDangKi.get(ca.getNgay()).size());
    }

    public boolean taoLichLamViec() {
        LocalDate start = getFirstDayOfWeek();
        LocalDate end = getLastDayOfWeek().plusDays(1);
        while (start.isBefore(end)) {
            if (!dsNhanVienCa.containsKey(start)) {
                return false;
            }
            start = start.plusDays(1);
        }

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO demoBTL.lichlamviec (ngaybatdau, ngayketthuc, quanliid, nhahangid) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, Date.valueOf(getFirstDayOfWeek()));
            preparedStatement.setDate(2, Date.valueOf(end.minusDays(1)));
            preparedStatement.setInt(3, 24);
            preparedStatement.setInt(4, 1);

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int idLichLamViec = 0;
            if (generatedKeys.next()) {
                idLichLamViec = generatedKeys.getInt(1);
            }
            for (LocalDate date = getFirstDayOfWeek(); date.isBefore(getLastDayOfWeek().plusDays(1)); date = date.plusDays(1)) {
                for (NhanVienCa nhanVienCa : dsNhanVienCa.get(date)) {
                    sql = "INSERT INTO demoBTL.nhanvienca (nhanvienid, caid, lichlamviecid) VALUES (?, ?, ?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, nhanVienCa.getNhanVien().getId());
                    preparedStatement.setInt(2, nhanVienCa.getCa().getId());
                    preparedStatement.setInt(3, idLichLamViec);
                    preparedStatement.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

        return true;
    }

    public void showLenLichLamFrm() {
        lenLichLamFrm = new LenLichLamFrm();
        lenLichLamFrm.setVisible(true);
    }

    public void showThemNhanVienCaFrm(int row, int col) {
        this.lenLichLamFrm.setVisible(false);
        this.themNhanVienCaFrm = new ThemNhanVienCaFrm(row, col);
        this.themNhanVienCaFrm.setVisible(true);
    }

    public void chonNhanVien(int row, int[] selectedRows, Map<Integer, NhanVien> nhanVienMap, Ca caMap) {
        LocalDate monday = ctr.getFirstDayOfWeek().plusDays(row);

        if (selectedRows.length != 0) {
            for (int i : selectedRows) {
                NhanVien nhanVien = nhanVienMap.get(i);
                NhanVienCa nhanVienCa = new NhanVienCa(nhanVien, caMap);
                ctr.themNhanVienCa(nhanVienCa);
            }

            System.out.println(ctr.getDsNhanVienCa());
        }
        // Cập nhật lại danh sách nhân viên ca đăng kí.
        ctr.updateDsNhanVienCaDangKi(caMap);

        this.themNhanVienCaFrm.setVisible(false);
        lenLichLamFrm = new LenLichLamFrm();
        lenLichLamFrm.setVisible(true);
    }

    public void lenLichLam() {
        isSaved = ctr.taoLichLamViec();
        thongBaoFrm = new ThongBaoFrm();
        thongBaoFrm.setVisible(true);
        if (isSaved) {
            thongBaoFrm.setTextLabel("Tạo lịch làm việc thành công!");
        } else {
            thongBaoFrm.setTextLabel("Tạo lịch làm việc thất bại!");
        }
    }

    public void handleClicked() {
        thongBaoFrm.setVisible(false);

        if (isSaved) {
            lenLichLamFrm.setVisible(false);
            TrangChuFrm trangChuFrm = new TrangChuFrm();
            trangChuFrm.setVisible(true);
        }
    }

    public LocalDate getFirstDayOfWeek() {
        Calendar calendar = Calendar.getInstance();

        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // Tính toán để tìm ngày thứ 2 gần nhất
        while (currentDayOfWeek != Calendar.MONDAY) {
            // Di chuyển đến ngày tiếp theo
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        return LocalDate.of(year, month, dayOfMonth);
    }

    public LocalDate getLastDayOfWeek() {
        LocalDate date = getFirstDayOfWeek();
        return date.plusDays(6);
    }

    public HashMap<LocalDate, ArrayList<NhanVienCaDangKi>> getDsNhanVienCaDangKi() {
        return dsNhanVienCaDangKi;
    }

    public HashMap<LocalDate, ArrayList<NhanVienCa>> getDsNhanVienCa() {
        return dsNhanVienCa;
    }
}
