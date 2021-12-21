package com.boxgame.main;

import java.awt.*;

/**
 * @author Soni
 */
public class HUD
{
    private final Game game;

    public HUD(Game game)
    {
        this.game = game;
    }

    public void tick()
    {

    }
    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.PLAIN, 30));
        g.drawString("Level " + game.level, 10, Game.HEIGHT - 50);
        if (game.level == 11)
        {
            g.setFont(new Font("arial", Font.PLAIN, 20));
            g.drawString("LAST LEVEL", 10, Game.HEIGHT - 50 - 30);
        }
    }
}