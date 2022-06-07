/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.object;

import com.boxgame.main.Game;
import com.boxgame.main.GameObject;

import java.awt.*;

/**
 * @author Soni
 */
public class Backer extends GameObject
{
    private final Game game;

    public Backer(int x, int y, Game game)
    {
        super(x, y);
        this.game = game;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.drawImage(game.backerTexture, pos[0], pos[1], 64, 64, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(pos[0], pos[1], 64, 64);
    }
}
