//Charles Murage,ICS B,165948, 18/11.2023
package com.murage.pharma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PLogin extends JFrame{
    private JPanel panel1;
    private JButton btnLogin;
    private JLabel lbl2Pass;
    private JLabel lblUser;
    private JLabel lbl2;
    private JTextField txtUname;
    private JPasswordField txtPass;

    private  final  String link= "jdbc:mysql://localhost:3306/db_charles_murage_165948";
    private  final  String dUname="";
    private  final  String dPass = "";


    public PLogin(){
        setContentPane(panel1);
        //setTitle("Welcome");

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = txtUname.getText();
                String pass = txtPass.getText();

                try (Connection connection = DriverManager.getConnection(link, dUname, dPass)) {
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tbl_pharmacist WHERE username = ? AND password_ = ?");
                    stmt.setString(1, uname);
                    stmt.setString(2, pass);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {

                        dispose();
                        new Pharmacist1();
                    } else {
                        JOptionPane.showMessageDialog(null,"Wrong Username or Password");
                    }

                    rs.close();
                    stmt.close();
                } catch (SQLException e1) {
                    // Handle database exceptions
                    e1.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {

        PLogin pLogin = new PLogin();

    }
}
