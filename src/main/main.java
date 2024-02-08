package main;

import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        boolean done = false;

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Test Game");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
