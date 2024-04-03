package com.murage.pharma;

import net.proteanit.sql.DbUtils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.sql.*;

public class PSell extends JFrame {
    private JPanel panel1;
    private JTextField txtName;
    private JButton sellButton;

    private  final  String link= "jdbc:mysql://localhost:3306/db_charles_murage_165948";
    private  final  String dUname="";
    private  final  String dPass = "";


    public  PSell(){

        setContentPane(panel1);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);







        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
                    String name = txtName.getText();


                    String deleteQuery = "DELETE FROM tbl_prescription WHERE descNo = ?";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                        preparedStatement.setString(1, name);

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Drug Sold Successfully!");
                            JOptionPane.showMessageDialog(null, "Drug sold Successfully", "Database", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            System.out.println("No Prescription Found");
                            JOptionPane.showMessageDialog(null, "No Matching Prescription Found", "Database", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                } catch (SQLException ex) {
                    System.err.println("Database error: " + ex.getMessage());
                }


            }


        });
    }

    public static void main(String[] args) {
        PSell pSell = new PSell();
    }
}
