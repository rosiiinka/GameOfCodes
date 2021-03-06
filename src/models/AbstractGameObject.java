package models;

import interfaces.GameObject;

import java.awt.*;

public abstract class AbstractGameObject implements GameObject {

    private int x;
    private int y;

    public AbstractGameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }
    
    @Override
    public void setX(int x) {
        this.x = x;
    }
    
    @Override
    public int getY() {
        return this.y;
    }
    
    @Override
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public abstract void draw(Graphics graphics);
    
    @Override
    public abstract void update();

}
