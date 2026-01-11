package piyano1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

public class GamePanel extends JPanel implements KeyListener {
    private final Timer gameTimer;
    private ArrayList<Box> fallingBoxes = new ArrayList<>();
    private Queue<Box> spawningQueue = new LinkedList<>();
    private final HashMap<Character, Integer> keyToX = new HashMap<>();
    
    private int miss = 0;
    private int score = 0;
    private int tickCounter = 0;
    private boolean isPaused = false; 
    private boolean statsSaved = false;     
    private final int hitZoneY = 520;
    private final int noteSpeed = 8;
    private final int[] cols = {50, 130, 210, 290, 370, 450, 530};
    private final char[] keys = {'a', 's', 'd', 'f', 'g', 'h', 'j'};

    public GamePanel(int level) {
        setFocusable(true);
        addKeyListener(this);
        for (int i = 0; i < keys.length; i++) keyToX.put(keys[i], cols[i]);

        setupLevel(level);

        gameTimer = new Timer(16, e -> {
            if (!isPaused) {
                update();
            }
            repaint();
        });
        gameTimer.start();
    }

    private void setupLevel(int level) {
        char[] sequence;
        switch (level) {
            case 1 -> sequence = new char[]{'j', 'h', 'g', 'g', 'h', 'j', 'j', 'h', 'j','h','g','h','g','f','f','g','h','h','g','h','g','f'};
            case 2 -> sequence = new char[]{'a','a','g','g','h','h','g','f','f','d','d','s','s','a'};
            case 3 -> sequence = new char[]{'j','f','j','g','a','d','h','s','j','a','s','d','f','g','h','j'};
            default -> sequence = new char[]{'a'};
        }

        for (int i = 0; i < sequence.length; i++) {
            char c = sequence[i];
            int xPos = keyToX.get(c) - 15;
            
            if (i == sequence.length - 1) {
                spawningQueue.add(new Box(xPos, -150, noteSpeed, c, 150));
            } else {
                spawningQueue.add(new Box(xPos, -50, noteSpeed, c, 45));
            }
        }
    }

    private void update() {
        tickCounter++;
        if (tickCounter >= 30 && !spawningQueue.isEmpty()) {
            fallingBoxes.add(spawningQueue.poll());
            tickCounter = 0;
        }

        for (int i = 0; i < fallingBoxes.size(); i++) {
            Box b = fallingBoxes.get(i);
            
            if (b.isBeingHeld) {
                b.length -= b.speed;
                score += 2;
                if (b.length <= 0) { fallingBoxes.remove(i); i--; }
            } else {
                b.y += b.speed;
                
                if (b.y > hitZoneY + 50) {
                    if (!isPaused) { 
                        DatabaseManager.incrementNoteMiss(b.noteKey, b.isLong); 
                    }
                    isPaused = true; 
                    miss++;
                    score -= 20;
                }
            }
        }
        
        if (spawningQueue.isEmpty() && fallingBoxes.isEmpty() && !statsSaved) {
            DatabaseManager.updateWinLoss(score > 150);
            statsSaved = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < cols.length; i++) {
            g.setColor(new Color(50, 50, 50));
            g.drawLine(cols[i] + 10, 0, cols[i] + 10, hitZoneY);
            g.setColor(Color.WHITE);
            g.fillRoundRect(cols[i] - 15, hitZoneY, 50, 45, 10, 10);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(keys[i]).toUpperCase(), cols[i]+5, hitZoneY+30);
        }

        for (Box b : fallingBoxes) {
            g.setColor(b.isLong ? Color.YELLOW : Color.CYAN);
            g.fillRoundRect(b.x, b.y, 50, b.length, 10, 10);
        }

        if (isPaused) {
            g.setColor(Color.RED);
            g.setFont(new Font("Segoe UI", Font.BOLD, 20));
            g.drawString("KAÇIRILDI!", 230, 300);
            g.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            g.drawString("Devam etmek için doğru tuşa basın.", 180, 330);
        }

        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("SKOR: " + score, 20, 40);
        
        if (spawningQueue.isEmpty() && fallingBoxes.isEmpty()) {
            g.setColor(Color.RED);
            if (score > 150) {
                g.drawString("TEBRİKLER Bölümü Geçtiniz. Hata Sayısı: " + miss, 100, 400);
            } else {
                g.drawString("TEKRAR DENEYİN Hata Sayısı: " + miss, 150, 400);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());
        
        for (int i = 0; i < fallingBoxes.size(); i++) {
            Box b = fallingBoxes.get(i);
            if (b.noteKey == key && (b.y + b.length) >= hitZoneY - 20 && b.y <= hitZoneY + 60) {
                if (b.isLong) {
                    b.isBeingHeld = true;
                } else { 
                    score += 10;
                    fallingBoxes.remove(i); 
                }
                SoundPlayer.play("/sounds/" + getNoteName(key) + ".wav");
                isPaused = false; 
                break;
            }
        }
    }

    private String getNoteName(char k) {
        return switch(k) {
            case 'a' -> "do-s"; case 's' -> "re-s"; case 'd' -> "mi-s";
            case 'f' -> "fa-s"; case 'g' -> "sol-s"; case 'h' -> "la-s";
            case 'j' -> "si-s"; default -> "do-s";
        };
    }

    @Override public void keyReleased(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());
        for (Box b : fallingBoxes) if (b.noteKey == key) b.isBeingHeld = false;
    }
    @Override public void keyTyped(KeyEvent e) {}
}