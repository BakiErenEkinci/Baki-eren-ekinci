package piyano1;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private JFrame parentFrame;

    public MainMenu(JFrame frame) {
        this.parentFrame = frame;
        setLayout(new GridBagLayout());
        setBackground(new Color(20, 20, 20));

        JLabel title = new JLabel("PİYANO RİTİM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 48));
        title.setForeground(Color.WHITE);

        JButton btnLvl1 = createStyledButton("LEVEL 1: KOLAY ");
        JButton btnLvl2 = createStyledButton("LEVEL 2: ORTA");
        JButton btnLvl3 = createStyledButton("LEVEL 3: ZOR");
        JButton btnStats = createStyledButton("İSTATİSTİKLER");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;

        gbc.gridy = 0; add(title, gbc);
        gbc.gridy = 1; add(btnLvl1, gbc);
        gbc.gridy = 2; add(btnLvl2, gbc);
        gbc.gridy = 3; add(btnLvl3, gbc);
        gbc.gridy = 4; add(btnStats, gbc);

        btnLvl1.addActionListener(e -> startGame(1));
        btnLvl2.addActionListener(e -> startGame(2));
        btnLvl3.addActionListener(e -> startGame(3));
        btnStats.addActionListener(e -> showStats());
        
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(300, 60));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(45, 45, 45));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        return btn;
    }

    private void startGame(int level) {
        parentFrame.getContentPane().removeAll();
        GamePanel game = new GamePanel(level);
        parentFrame.add(game);
        game.requestFocusInWindow();
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void showStats() {
        parentFrame.getContentPane().removeAll();
        parentFrame.add(new StatsPanel(parentFrame));
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}