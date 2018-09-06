package com.boxgame.main;

/*
 * Author: soni801
 */

import java.awt.*;

public class Help
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
        g.drawString("Help", 320, 100);

        g.setFont(text);
        g.drawString("Try to find the exit.", 265, 140);
        g.drawString("You can not go through walls.", 185, 180);
        g.drawString("Blue blocks teleport you", 222, 220);
        g.drawString("to another place on the level.", 190, 260);
        g.drawString("The green block is the goal.", 200, 300);
        g.drawString("You can find guards around the level.", 135, 340);
        g.drawString("If you touch a guard, the level resets.", 140, 380);

        g.setFont(button);
        g.drawRoundRect(Game.WIDTH / 2 - 100, 420, 200, 50, 50, 50);
        g.drawString("BACK", Game.WIDTH / 2 - 100 + 65, 455);
    }
}