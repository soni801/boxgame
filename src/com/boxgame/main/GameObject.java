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
    public int[] pos = new int[2];
    public int[] vel = new int[2];

    public GameObject(int x, int y)
    {
        pos[0] = x;
        pos[1] = y;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
}
