package states;

import authentication.AuthenticationProvider;
import graphics.Assets;
import models.Button;
import models.User;
import utils.Constants;
import utils.Utils;

import java.awt.*;



public class StudentProfileState extends State {

    public static StringBuilder firstName = new StringBuilder();
    public static StringBuilder lastName = new StringBuilder();
    public static StringBuilder password = new StringBuilder();

    private static User user = AuthenticationProvider.currentUser;

    public static Button backToMenuButton = new Button(410, 510, Assets.buttonBackToMenu);
    public static Button editFirstNameButton = new Button(700, 182,Assets.buttonEdit);
    public static Button editSurNameButton = new Button(700, 242,Assets.buttonEdit);
    public static Button editPasswordButton = new Button(700, 302,Assets.buttonEdit);

    public static Rectangle firstRect;
    public static Rectangle lastRect;
    public static Rectangle passRect;

    public static String fieldType = "first";


    @Override
    public void draw(Graphics graphics) {

        int fieldNameX = 220;
        int fieldNameY = 208;
        int rectBoxX = 390;
        int rectBoxY = 182;

        graphics.drawImage(Assets.wall, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, null);
        graphics.fillRect(100, 40, 824, 450);

        Font title = new Font("Arial", Font.PLAIN, 35);
        graphics.setFont(title);
        graphics.setColor(Color.white);
        String userProfile = user.getUsername();
        graphics.drawString("User: " + userProfile, 390, 90);

        Font fieldName = new Font("Arial", Font.PLAIN, 25);
        graphics.setFont(fieldName);
        graphics.drawString("First Name:", fieldNameX, fieldNameY);
        graphics.drawString("Last Name:", fieldNameX, fieldNameY + 60);
        graphics.drawString("Password:", fieldNameX, fieldNameY + 120);

        graphics.fillRect(rectBoxX, rectBoxY, 300, 40);
        firstRect = new Rectangle(rectBoxX, rectBoxY, 300, 40);
        graphics.fillRect(rectBoxX, rectBoxY + 60, 300, 40);
        lastRect = new Rectangle(rectBoxX, rectBoxY + 60, 300, 40);
        graphics.fillRect(rectBoxX, rectBoxY + 120, 300, 40);
        passRect = new Rectangle(rectBoxX, rectBoxY + 120, 300, 40);


        graphics.setColor(Color.gray);

        switch (fieldType) {
            case "first":
                graphics.drawRect((int)firstRect.getX() - 3, (int)firstRect.getY() - 3, 306, 46);
                break;
            case "last":
                graphics.drawRect((int)lastRect.getX() - 3, (int)lastRect.getY() - 3, 306, 46);
                break;
            case "pass":
                graphics.drawRect((int)passRect.getX() - 3, (int)passRect.getY() - 3, 306, 46);
                break;
        }

        Font inputText = new Font("Arial", Font.BOLD, 20);
        graphics.setFont(inputText);
        graphics.setColor(Color.black);
        graphics.drawString(firstName.toString(), rectBoxX + 30, rectBoxY + 85);
        graphics.drawString(lastName.toString(), rectBoxX + 30, rectBoxY + 145);
        graphics.drawString(Utils.hidePassword(password.length()), rectBoxX + 30, rectBoxY + 210);

        backToMenuButton.draw(graphics);
        editFirstNameButton.draw(graphics);
        editSurNameButton.draw(graphics);
        editPasswordButton.draw(graphics);
        System.out.println(firstName.toString());
    }

    @Override
    public void update() {
    }
}
