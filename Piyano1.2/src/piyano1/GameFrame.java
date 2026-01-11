package piyano1;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Piyano Ritim Oyunu");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

     
        add(new MainMenu(this)); 

        setVisible(true);
    }
}