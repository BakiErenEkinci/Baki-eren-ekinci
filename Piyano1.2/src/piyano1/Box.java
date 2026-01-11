package piyano1;

public class Box {
    int x, y, speed, length;
    char noteKey;
    boolean isLong;
    boolean isBeingHeld = false;

    public Box(int x, int y, int speed, char noteKey, int length) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.noteKey = noteKey;
        this.length = length;
        this.isLong = length > 50;
    }
}