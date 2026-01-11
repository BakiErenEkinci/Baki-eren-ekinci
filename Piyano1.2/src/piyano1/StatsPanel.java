package piyano1;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
    private JTextArea area; 

    public StatsPanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 25));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        
        JLabel title = new JLabel("OYUNCU PERFORMANSI", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.CYAN);
        title.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
        
        JButton btnReset = new JButton("İstatistikleri Sıfırla");
        btnReset.setBackground(new Color(150, 0, 0));
        btnReset.setForeground(Color.WHITE);
        btnReset.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Tüm verileriniz kalıcı olarak silinecek. Emin misiniz?", 
                "Sıfırlama Onayı", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                DatabaseManager.resetStats();
                refreshStats(); 
            }
        });

        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(btnReset, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        area = new JTextArea();
        area.setBackground(new Color(30, 30, 30));
        area.setForeground(Color.WHITE);
        area.setFont(new Font("Monospaced", Font.BOLD, 18));
        area.setEditable(false);
        area.setMargin(new Insets(20, 20, 20, 20));
        
        add(new JScrollPane(area), BorderLayout.CENTER);
        refreshStats(); 

        JButton btnBack = new JButton("ANA MENÜYE DÖN");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnBack.setPreferredSize(new Dimension(0, 50));
        btnBack.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(new MainMenu(frame));
            frame.revalidate();
            frame.repaint();
        });
        add(btnBack, BorderLayout.SOUTH);
    }

    private void refreshStats() {
        String text = "--- GENEL İSTATİSTİKLER ---\n" + DatabaseManager.getGeneralStats() +
                     "\n\n--- NOTA BAZLI HATALAR ---\n" + DatabaseManager.getNoteStats();
        area.setText(text);
    }
}