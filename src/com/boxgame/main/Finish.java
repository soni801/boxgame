/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main;

import java.awt.*;

/**
 * @author Soni
 */
public class Finish extends GameObject
{
    private final Game game;

    public Finish(int x, int y, ID id, Game game)
    {
        super(x, y, id);
        this.game = game;
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(game.finish, x, y, 64, 64, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 64, 64);
    }
}
