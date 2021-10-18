package shao;

import javax.swing.*;

public class GluttonousSnake {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 900, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnackPanel panel = new SnackPanel();
        frame.add(panel);
        frame.setVisible(true);
    }


}
