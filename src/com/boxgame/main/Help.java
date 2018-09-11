package com.boxgame.main;

/*
 * Author: soni801
 */

import java.awt.*;

public class Help
{
    public int mouseOver;

    private Game game;

    public Help(Game game)
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

        g.drawImage(game.help_menu, 0, -45, null);
        g.drawImage(game.finish, 509, 130, 16, 16, null);
    }
}