package com.boxgame.main;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author Soni
 */
public class Handler
{
    LinkedList<GameObject> object = new LinkedList<>();

    private boolean up, down, left, right;

    public void tick()
    {
        for (GameObject tempObject : object) tempObject.tick();
    }

    public void render(Graphics g)
    {
        for (GameObject tempObject : object) tempObject.render(g);
    }

    public void addObject(GameObject object) { this.object.add(object); }

    public void removeObject(GameObject object) { this.object.remove(object); }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}