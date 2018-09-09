package com.boxgame.main;

import java.awt.*;

public class Paused
{
    public int mouseOver;

    private Game game;

    public Paused(Game game)
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
            case 1 : g.fillRect(0, 400, Game.WIDTH, 30); break;
            case 2 : g.fillRect(0, 430, Game.WIDTH, 30); break;
            case 3 : g.fillRect(0, 460, Game.WIDTH, 30); break;
            case 4 : g.fillRect(0, 490, Game.WIDTH, 30); break;
            case 5 : g.fillRect(0, 520, Game.WIDTH, 30); break;
        }

        g.drawImage(game.pause_menu, 0, -45, null);

        //g.drawRect(0, 400, Game.WIDTH, 30);
        //g.drawRect(0, 430, Game.WIDTH, 30);
        //g.drawRect(0, 460, Game.WIDTH, 30);
        //g.drawRect(0, 490, Game.WIDTH, 30);
        //g.drawRect(0, 520, Game.WIDTH, 30);
    }
}