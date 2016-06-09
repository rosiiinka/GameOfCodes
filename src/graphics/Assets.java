package graphics;

import enums.ImageAlbum;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage wall;
    public static BufferedImage floor;

    public static SpriteSheet nerdLady;
    public static SpriteSheet badBoy;
    public static SpriteSheet hotChik;
    public static SpriteSheet nerdBoy;
    public static BufferedImage buttonPlay;
    public static BufferedImage buttonScore;
    public static BufferedImage buttonExit;

    public static void init() {
        wall = ImageLoader.loadingImage(ImageAlbum.Wall.getPath());
        floor = ImageLoader.loadingImage(ImageAlbum.FLoor.getPath());

        nerdLady = new SpriteSheet(ImageLoader.loadingImage(ImageAlbum.NerdLady.getPath()));
        badBoy=new SpriteSheet(ImageLoader.loadingImage(ImageAlbum.BadBoy.getPath()));
        hotChik=new SpriteSheet(ImageLoader.loadingImage(ImageAlbum.HotChick.getPath()));
        nerdBoy=new SpriteSheet(ImageLoader.loadingImage(ImageAlbum.NerdBoy.getPath()));
        buttonPlay=(ImageLoader.loadingImage(ImageAlbum.Button_Play.getPath()));
        buttonScore=ImageLoader.loadingImage(ImageAlbum.Button_Score.getPath());
        buttonExit=ImageLoader.loadingImage(ImageAlbum.Button_Exit.getPath());

    }
}
