//Charles Murage,ICS B,165948, 18/11.2023
package com.murage.pharma;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Pharmacist extends JFrame {
    private JPanel panel1;
    private JTable tableDrug;
    private JTextField txtDname;
    private JTextField txtDdescription;
    private JTextField txtDquantity;
    private JButton logButton;
    private JTextField txtDexpiry;
    private JButton stockButton;
    private JButton searchButton;
    private JButton rButton;
    private JLabel dName;
    private JLabel dQuantity;
    private JLabel dDescription;
    private JLabel dExpiry;
    private JButton addButton;

    private  final  String link= "jdbc:mysql://localhost:3306/db_charles_murage_165948";
    private  final  String dUname="";
    private  final  String dPass = "";

    public Pharmacist(){
        setContentPane(panel1);
        setTitle("Welcome");
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tbl_drug ");
            ResultSet rs = stmt.executeQuery();
            tableDrug.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            stmt.close();
        } catch (SQLException e1) {
            // Handle database exceptions
            e1.printStackTrace();
        }







        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null,"You have been logged out");
                dispose();
                new Homepage();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtDname.getText();

                try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tbl_drug WHERE name_ = ?");
                    stmt.setString(1, name);
                    ResultSet rm = stmt.executeQuery();
                    tableDrug.setModel(DbUtils.resultSetToTableModel(rm));

                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null,"Input failed, Try again");
                    e1.printStackTrace();
                }

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtDname.getText();
                int quantity= Integer.parseInt(txtDquantity.getText());
                String description = txtDdescription.getText();
                String date = txtDexpiry.getText();

                try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
                    PreparedStatement stmt = connection.prepareStatement("insert into db_charles_murage_165948.tbl_drug(name_,quantity,description_,expiryDate)\n" +
                            "values(?,?,?,?);");
                    stmt.setString(1, name);
                    stmt.setString(2, String.valueOf(quantity));
                    stmt.setString(3, description);
                    stmt.setString(4, date);

                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(null,"You have added "+ name +" successfully ");
                    txtDname.setText("");
                    txtDquantity.setText("");
                    txtDdescription.setText("");
                    txtDexpiry.setText("");
                    PreparedStatement stt = connection.prepareStatement("SELECT * FROM tbl_drug ");
                    ResultSet rs = stt.executeQuery();
                    tableDrug.setModel(DbUtils.resultSetToTableModel(rs));



                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null,"Input failed, Try again");
                    e1.printStackTrace();
                }

            }
        });
        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtDname.getText();
                int quantity= Integer.parseInt(txtDquantity.getText());
                try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
                    PreparedStatement stmt = connection.prepareStatement("UPDATE `db_charles_murage_165948`.`tbl_drug` SET `quantity` = quantity+? WHERE (`name_` = ?);");
                    stmt.setString(2, name);
                    stmt.setString(1, String.valueOf(quantity));

                    JOptionPane.showMessageDialog(null,"You have added "+ quantity +" tablets of "+name+" to the stock");

                    stmt.executeUpdate();
                    txtDname.setText("");
                    txtDquantity.setText("");
                    PreparedStatement stt = connection.prepareStatement("SELECT * FROM tbl_drug ");
                    ResultSet rs = stt.executeQuery();
                    tableDrug.setModel(DbUtils.resultSetToTableModel(rs));



                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null,"Input failed, Try again");
                    e1.printStackTrace();
                }

            }
        });
        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = txtDname.getText();
                try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
                    PreparedStatement stmt = connection.prepareStatement("DELETE FROM `db_charles_murage_165948`.`tbl_drug` WHERE (`name_` = ?);");
                    stmt.setString(1, name);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null,"You have removed "+name);

                    txtDname.setText("");

                    PreparedStatement stt = connection.prepareStatement("SELECT * FROM tbl_drug ");
                    ResultSet rs = stt.executeQuery();
                    tableDrug.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null,"Input failed, Try again");
                    e1.printStackTrace();
                }
            }
        });
    }





    public static void main(String[] args) {

        Pharmacist pharmacist = new Pharmacist();
    }
}


