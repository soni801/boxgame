package com.boxgame.main;

/*
 * Author: soni801
 */

import java.awt.*;

public class Achievements
{
    public int mouseOver;

    private Game game;

    public Achievements(Game game)
    {
        this.game = game;
    }

    public void load()
    {

    }

    public void save()
    {

    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.setColor(new Color(0f, 0f, 0f, .25f));

        if (mouseOver == 1)
            g.fillRect(0, 520, Game.WIDTH, 30);

        g.drawImage(game.achievements_menu, 0, -45, null);
    }
}