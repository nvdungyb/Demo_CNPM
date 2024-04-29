package org.example.frm;

import org.example.entity.NhanVienCa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.example.controller.StaticResouce.ctr;

public class LenLichLamFrm extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable table2;
    private int row;
    private int column;
    private ThemNhanVienCaFrm themNhanVienCaFrm;

    public LenLichLamFrm() {
        initComponents();
        initData();
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        table2 = new JTable();

        // Thiết lập layout cho dialogPane là BorderLayout
        dialogPane.setLayout(new BorderLayout());

        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            {
                DefaultTableModel model = new DefaultTableModel(); // Tạo một model mới
                table2.setModel(model); // Đặt model cho bảng

                // Thêm cột vào model
                model.addColumn("Ca 1");
                model.addColumn("Ca 2");

                // Thêm dữ liệu vào model (7 dòng)
                for (int i = 0; i < 7; i++) {
                    model.addRow(new Object[]{"ca", "ca"});
                }

                // Đặt kích thước cố định cho cột (nếu cần)
                table2.getColumnModel().getColumn(0).setPreferredWidth(200);
                table2.getColumnModel().getColumn(1).setPreferredWidth(200);
                table2.setRowHeight(70);

                // Không cho người dùng chỉnh sửa dữ liệu trực tiếp trong bảng
                table2.setEnabled(false);

                // Đặt bảng vào scrollPane
                scrollPane1.setViewportView(table2);
            }

            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(scrollPane1, BorderLayout.CENTER);

            // Thêm contentPanel vào dialogPane ở vị trí CENTER
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        // Thêm dialogPane vào contentPane ở vị trí CENTER
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setTitle("LenLichLamFrm");

        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);

        setSize(800, 600);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                updateScrollPaneSize();
            }
        });

        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table2.rowAtPoint(e.getPoint());
                int column = table2.columnAtPoint(e.getPoint());
                if (row >= 0 && column >= 0) {
                    System.out.println("Click vào dòng " + row + " cột " + column);

                    themNhanVienCaFrm = new ThemNhanVienCaFrm(row, column);
                    themNhanVienCaFrm.setVisible(true);

                    JFrame thisFrame = (JFrame) SwingUtilities.getWindowAncestor(table2);
                    thisFrame.setVisible(false);
                }
            }
        });
    }

    private void initData() {
        HashMap<LocalDate, ArrayList<NhanVienCa>> dsNhanVienCa = ctr.getDsNhanVienCa();
        if (dsNhanVienCa == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table2.getModel();

        LocalDate start = ctr.getFirstDayOfWeek();
        int row = 0;
        for (LocalDate startDate = start; startDate.isBefore(start.plusDays(7)); startDate = startDate.plusDays(1)) {
            row++;
            ArrayList<NhanVienCa> list = dsNhanVienCa.get(startDate);
            System.out.println(list);
            if (list == null) {
                continue;
            }

            for (int buoi = 1; buoi <= 2; buoi++) {
                StringBuilder currentValueBuilder = new StringBuilder();
                for (NhanVienCa nhanVienCa : list) {
                    if (nhanVienCa.getCa().getBuoi() == buoi) {
                        currentValueBuilder.append(nhanVienCa.getNhanVien().getName()).append("<br>");
                    }
                }
                String currentValue = currentValueBuilder.toString();
                if (!currentValue.isEmpty()) {
                    currentValue = "<html>" + currentValue + "</html>";
                    model.setValueAt(currentValue, row - 1, buoi - 1);
                }

                System.out.println(row + " " + buoi);
            }
        }
    }

    private void updateScrollPaneSize() {
        int width = contentPanel.getWidth();
        int height = contentPanel.getHeight();
        contentPanel.setPreferredSize(new Dimension(width, height));
        dialogPane.revalidate();
        dialogPane.repaint();
    }

}
