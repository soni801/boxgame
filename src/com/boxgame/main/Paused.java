package com.boxgame.main;

import java.awt.*;

public class Paused
{
    public void tick()
    {

    }

    public void render(Graphics g)
    {
        Font title = new Font("arial", Font.BOLD, 70);
        Font text = new Font("arial", Font.BOLD, 30);
        Font button = new Font("arial", Font.BOLD, 25);

        g.setColor(Color.BLACK);
        g.setFont(title);
        g.drawString("Paused", 275, 100);

        g.setFont(button);
        g.drawRoundRect(Game.WIDTH / 2 - 100, 200, 200, 50, 50, 50);
        g.drawString("CONTINUE", Game.WIDTH / 2 - 100 + 35, 235);

        g.drawRoundRect(Game.WIDTH / 2 - 100, 260, 200, 50, 50, 50);
        g.drawString("HELP", Game.WIDTH / 2 - 100 + 66, 295);

        g.drawRoundRect(Game.WIDTH / 2 - 100, 320, 200, 50, 50, 50);
        g.drawString("SETTINGS", Game.WIDTH / 2 - 100 + 36, 355);

        g.drawRoundRect(Game.WIDTH / 2 - 100, 380, 200, 50, 50, 50);
        g.drawString("QUIT", Game.WIDTH / 2 - 100 + 69, 415);
    }
}