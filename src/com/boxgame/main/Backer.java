package com.boxgame.main;

import java.awt.*;

/**
 * @author Soni
 */
public class Backer extends GameObject
{
    private final Game game;

    public Backer(int x, int y, ID id, Game game)
    {
        super(x, y, id);
        this.game = game;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.drawImage(game.backer, x, y, 64, 64, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 64, 64);
    }
}