package com.boxgame.main;

import java.awt.*;

public class End
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
        g.drawString("The End.", 260, 140);

        g.setFont(text);
        g.drawString("You've done all the levels.", 220, 170);
        g.drawString("Congratulations!", 280, 200);
        g.drawString("Thanks for playing!", 260, 270);
        g.drawString("Hope you enjoyed.", 268, 300);

        g.setFont(button);
        g.drawRoundRect(Game.WIDTH / 2 - 125, 400, 250, 50, 50, 50);
        g.drawString("BACK TO MENU", Game.WIDTH / 2 - 125 + 25, 435);
    }
}