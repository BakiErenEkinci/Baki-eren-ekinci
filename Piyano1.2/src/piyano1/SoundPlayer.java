/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piyano1;

/**
 *
 * @author erene
 */
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

    public static void play(String path) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    SoundPlayer.class.getResource(path)
            );

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {
            System.out.println("Ses çalınamadı: " + e.getMessage());
        }
    }
}
