//Charles Murage,ICS B,165948, 18/11.2023
package com.murage.pharma;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {
    private JPanel panel1;
    private JLabel lblLog;
    private JButton btnDoc;
    private JButton btnPharm;

    public Homepage(){

        setContentPane(panel1);
        setTitle("Welcome");
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        btnDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();

            }
        });


        btnPharm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PLogin();
            }
        });
    }

    public static void main(String[] args) {
        Homepage homepage = new Homepage();
    }
}
