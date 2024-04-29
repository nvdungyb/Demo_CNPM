package org.example.frm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TrangChuFrm extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JPanel buttonBar;

    public TrangChuFrm() {
        initComponents();
        exits();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Nguyễn Dũng
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        buttonBar = new JPanel();

        //======== this ========
        setTitle("TrangChuFrm");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            button5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LenLichLamFrm lenlichlamFrm = new LenLichLamFrm();
                    lenlichlamFrm.setVisible(true);

                    Frame frame = (Frame) SwingUtilities.getWindowAncestor(dialogPane);
                    frame.setVisible(false);
                }
            });

            //======== contentPanel ========
            {

                label1.setText("He thong quan li nhan vien Lotteria");

                button1.setText("Quan li nhan vien");

                button2.setText("Tinh cong cho nhan vien");

                button3.setText("Thong ke");

                button4.setText("Dang ki ca lam");

                button5.setText("Len lich lam viec");

                button6.setText("Checkin");

                button7.setText("Checkout");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(contentPanelLayout.createParallelGroup()
                                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                                                .addComponent(label1)
                                                                .addGap(327, 327, 327))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                                                .addComponent(button1)
                                                                .addGap(61, 61, 61)
                                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(70, 70, 70)
                                                                .addComponent(button3)
                                                                .addGap(64, 64, 64)
                                                                .addComponent(button4)
                                                                .addGap(61, 61, 61)
                                                                .addComponent(button5)
                                                                .addGap(34, 34, 34)))
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                        .addGap(12, 12, 12)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(button6, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(button7))
                                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                );
                contentPanelLayout.setVerticalGroup(
                        contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                        .addComponent(label1)
                                        .addGap(105, 105, 105)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(button1)
                                                .addComponent(button5)
                                                .addComponent(button4)
                                                .addComponent(button3)
                                                .addComponent(button2))
                                        .addGap(38, 38, 38)
                                        .addComponent(button7)
                                        .addGap(29, 29, 29)
                                        .addComponent(button6)
                                        .addContainerGap(192, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        setVisible(true);
    }

    public void exits() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }
}
