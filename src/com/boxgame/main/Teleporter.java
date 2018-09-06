package com.boxgame.main;

/*
 * Author: soni801
 */

import java.awt.*;

public class Teleporter extends GameObject
{
    private Game game;

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