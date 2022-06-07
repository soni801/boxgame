/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main;

import java.awt.*;

/**
 * @author Soni
 */
public abstract class GameObject
{
    public int x, y;
    public int velX, velY;

    public GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
}
