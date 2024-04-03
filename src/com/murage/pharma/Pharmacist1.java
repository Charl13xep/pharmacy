package com.murage.pharma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pharmacist1 extends JFrame{
    private JPanel panel1;
    private JButton updateDrugsButton;
    private JButton sellDrugsButton;


    public Pharmacist1(){

        setContentPane(panel1);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        updateDrugsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Pharmacist();
            }
        });
        sellDrugsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PSell();
            }
        });
    }

    public static void main(String[] args) {
        Pharmacist1 pharmacist1= new Pharmacist1();
    }
}
