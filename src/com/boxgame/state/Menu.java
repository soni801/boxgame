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
public class Menu
{
    public int mouseOver;

    private final Game game;

    public Menu(Game game)
    {
        this.game = game;

        mouseOver = 0;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.setColor(new Color(0f, 0f, 0f, .25f));

        switch (mouseOver)
        {
            case 1 -> g.fillRect(0, 370, Game.WIDTH, 30);
            case 2 -> g.fillRect(0, 400, Game.WIDTH, 30);
            case 3 -> g.fillRect(0, 430, Game.WIDTH, 30);
            case 4 -> g.fillRect(0, 460, Game.WIDTH, 30);
            case 5 -> g.fillRect(0, 490, Game.WIDTH, 30);
            case 6 -> g.fillRect(0, 520, Game.WIDTH, 30);
        }

        g.drawImage(game.main_menu, 0, -45, null);
    }
}
