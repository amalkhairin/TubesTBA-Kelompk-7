package controller;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Driver {

    public static void main(String[] args) {
        //set theme
        try {
            for (UIManager.LookAndFeelInfo i : UIManager.getInstalledLookAndFeels()) {
//                System.out.println(i.getName());
                if ("Windows".equals(i.getName())) {
                    UIManager.setLookAndFeel(i.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("error" + e.getMessage());
        }

        //display GUI
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainController();
            }
        });
    }
}
