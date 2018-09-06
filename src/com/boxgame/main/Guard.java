package com.boxgame.main;

/*
 * Author: soni801
 */

import java.awt.*;

public class Guard extends GameObject
{
    private Game game;

    public Guard(int x, int y, ID id, Game game)
    {
        super(x, y, id);
        this.game = game;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.drawImage(game.guard, x + 2, y + 2, 60, 60, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 64, 64);
    }
}