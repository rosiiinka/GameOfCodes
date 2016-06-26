package states;

import java.awt.*;

public class ErrorMessageState extends State{
    private String message;

    public ErrorMessageState(String message) {
        this.message = message;
    }

    @Override
    public void draw(Graphics graphics) {
        int messagePositionX = 650;
        int messagePositionY = 250;

        Font font = new Font("Arial", Font.PLAIN, 35);
        graphics.setFont(font);
        graphics.setColor(Color.red);
        graphics.drawString(this.message, messagePositionX, messagePositionY);
    }

    @Override
    public void update() {

    }
}
