package controller;

import view.MainScreen;
import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MainController extends MouseAdapter implements ActionListener {

    private MainScreen view;

    public MainController() {
        view = new MainScreen();
        view.addListener(this);
        view.addMouseAdapter(this);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getCekBtn())) {
            cekBtnPerformed();
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        Icon icon = new ImageIcon("./src/img/btn_hover.png");
        view.setCekBtn(icon);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        Icon icon = new ImageIcon("./src/img/btn.png");
        view.setCekBtn(icon);
    }

    public void cekBtnPerformed() {
        Stack stack = new Stack();
        ArrayList tokenList = new ArrayList();
        String str = view.getInputField().getText();
        String[] lexic = str.split(" ");
        int i = 0;

        LexicAnalyzer lc = new LexicAnalyzer();

        do {
            lc.state0(lexic[i]);
            stack = lc.getStack();
            i++;
        } while (stack.peek() != "error" && i < lexic.length);

        tokenList.addAll(stack);
        view.setTokenView(tokenList);
        System.out.println(tokenList);

        Validator validator = new Validator();

        boolean valid1 = validator.validatorCek(tokenList);
        boolean valid2 = true;

        for (Object s : tokenList) {
            if ("error".equals(s)) {
                valid2 = false;
            }
        }
        System.out.println(valid2);

        String result = valid1 == true && valid2 == true ? "VALID" : "TIDAK VALID";
        view.setKeterangan(result);
        System.out.println(result);
    }

}
