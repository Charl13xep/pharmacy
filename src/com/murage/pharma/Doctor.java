//Charles Murage,ICS B,165948, 18/11.2023
package com.murage.pharma;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Doctor extends JFrame{
    private JPanel panel1;
    private JTable tblDrug;
    private JTextField txtName;
    private JTextField txtPresc;
    private JButton logBtn;
    private JButton searchBtn;
    private JButton prescribeButton;
    private JLabel lblName;
    private JLabel lblPresc;
    private  final  String link= "jdbc:mysql://localhost:3306/db_charles_murage_165948";
    private  final  String dUname="";
    private  final  String dPass = "";

    public Doctor(){
        setContentPane(panel1);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tbl_drug ");
            ResultSet rs = stmt.executeQuery();
            tblDrug.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            stmt.close();
        } catch (SQLException e1) {
            // Handle database exceptions
            e1.printStackTrace();
        }


        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();

                try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tbl_drug WHERE name_ = ?");
                    stmt.setString(1, name);
                    ResultSet rm = stmt.executeQuery();
                    tblDrug.setModel(DbUtils.resultSetToTableModel(rm));

                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null,"Input failed, Try again");
                    e1.printStackTrace();
                }

            }
        });


        prescribeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                int quantity= Integer.parseInt(txtPresc.getText());
                try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
                    PreparedStatement stmt = connection.prepareStatement("UPDATE `db_charles_murage_165948`.`tbl_drug` SET `quantity` = quantity-? WHERE (`name_` = ?);");
                    stmt.setString(2, name);
                    stmt.setString(1, String.valueOf(quantity));

                    stmt.executeUpdate();
                    PreparedStatement stt = connection.prepareStatement("SELECT * FROM tbl_drug ");
                    ResultSet rs = stt.executeQuery();
                    tblDrug.setModel(DbUtils.resultSetToTableModel(rs));

                    JOptionPane.showMessageDialog(null,"You have prescribed "+ quantity +" tablets of "+name);

                    txtName.setText("");
                    txtPresc.setText("");

                    PreparedStatement stmt2 = connection.prepareStatement("insert into db_charles_murage_165948.tbl_prescription(dName,dQuantity)\n" +
                            "values(?,?);");
                    stmt2.setString(1, name);
                    stmt2.setString(2, String.valueOf(quantity));
                    stmt2.executeUpdate();






                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null,"Input failed, Try again");
                    e1.printStackTrace();
                }
            }
        });
        logBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Homepage();
            }
        });
    }

    public static void main(String[] args) {
        Doctor doctor = new Doctor();
    }
}
