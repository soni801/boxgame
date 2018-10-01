package com.boxgame.main;

import java.awt.*;

public class End
{
    public int mouseOver;

    private Game game;

    public End(Game game)
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

        if (mouseOver == 1) g.fillRect(0, 520, Game.WIDTH, 30);

        g.drawImage(game.end_menu, 0, -45, null);
    }
}