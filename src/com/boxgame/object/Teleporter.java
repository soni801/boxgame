/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.object;

import com.boxgame.main.Game;
import com.boxgame.main.GameObject;
import com.boxgame.main.types.ID;

import java.awt.*;

/**
 * @author Soni
 */
public class Teleporter extends GameObject
{
    private final Game game;

    public Teleporter(int x, int y, ID id, Game game)
    {
        super(x, y, id);
        this.game = game;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.drawImage(game.teleporter, x, y, 64, 64, null);
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 64, 64);
    }
}
