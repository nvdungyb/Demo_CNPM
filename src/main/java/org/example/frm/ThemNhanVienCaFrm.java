package org.example.frm;

import org.example.entity.Ca;
import org.example.entity.NhanVien;
import org.example.entity.NhanVienCa;
import org.example.entity.NhanVienCaDangKi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static org.example.controller.StaticResouce.ctr;

public class ThemNhanVienCaFrm extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton selectButton;
    private int row, col;
    private Map<Integer, NhanVien> nhanVienMap;
    private Ca caMap;

    public ThemNhanVienCaFrm(int row, int col) {
        this.row = row;
        this.col = col;
        nhanVienMap = new HashMap<>();

        initComponents();
        initData();
        formClosing();
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        selectButton = new JButton("Chọn");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate monday = ctr.getFirstDayOfWeek().plusDays(row);

                int[] selectedRows = table1.getSelectedRows();
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
                setVisible(false);
                LenLichLamFrm lenLichLamFrm = new LenLichLamFrm();
                lenLichLamFrm.setVisible(true);
            }
        });

        setTitle("ThemNhanVienCaFrm");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            dialogPane.setLayout(new BorderLayout());
            {
                {
                    DefaultTableModel model = new DefaultTableModel(); // Tạo một model mới
                    table1.setModel(model);
                    model.addColumn("Ten nhan vien");
                    model.addColumn("So dien thoai");
                    model.addColumn("Tong gio len lich");

                    scrollPane1.setViewportView(table1);
                }

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(23, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selectButton)
                                        .addContainerGap())
                );
                contentPanelLayout.setVerticalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                        .addComponent(selectButton)
                                        .addContainerGap())
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void initData() {
        HashMap<LocalDate, ArrayList<NhanVienCaDangKi>> dsNhanVienCaDangKi = ctr.getDsNhanVienCaDangKi();
        DefaultTableModel model = (DefaultTableModel) table1.getModel();

        LocalDate dateAndCol = ctr.getFirstDayOfWeek().plusDays(this.row);
        ArrayList<NhanVienCaDangKi> dsNhanVienNgay = dsNhanVienCaDangKi.get(dateAndCol);

        ArrayList<NhanVienCaDangKi> result = new ArrayList<>();
        for (NhanVienCaDangKi nhanVienCaDangKi : dsNhanVienNgay) {
            if (nhanVienCaDangKi.getCa().getBuoi() == (this.col + 1)) {
                result.add(nhanVienCaDangKi);
            }
        }

        int rowIndex = 0;
        result.sort(Comparator.comparingInt(o -> o.getNhanVien().getSoGioLenLich()));
        for (NhanVienCaDangKi nhanVienCaDangKi : result) {
            NhanVien nhanVien = nhanVienCaDangKi.getNhanVien();
            nhanVienMap.put(rowIndex++, nhanVien);
            caMap = nhanVienCaDangKi.getCa();
            model.addRow(new Object[]{nhanVien.getName(), nhanVien.getSodt(), nhanVien.getSoGioLenLich()});
        }
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void formClosing() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LenLichLamFrm lenLichLamFrm = new LenLichLamFrm();
                lenLichLamFrm.setVisible(true);
            }
        });
    }

}