package models.students;

import graphics.Assets;

public class BadBoy extends Student {
    private static int imageWidth=39;
    private static int imageHeidth=40;

    public BadBoy(int x, int y, String name) {
        super(x, y, Assets.badboyplayer, imageWidth, imageHeidth, name, 60, 60, 130);
    }
}
