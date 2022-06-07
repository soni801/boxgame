/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.state;

import com.boxgame.main.Game;

import java.awt.*;

/**
 * @author Soni
 */
public class Credits
{
    public int mouseOver;

    private final Game game;

    public Credits(Game game)
    {
        this.game = game;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.setColor(new Color(0f, 0f, 0f, .25f));

        if (mouseOver == 1)
            g.fillRect(0, 520, Game.WIDTH, 30);

        g.drawImage(game.creditsMenu, 0, -45, null);
    }
}
