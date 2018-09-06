package com.boxgame.main;

import java.awt.*;

public class Menu
{
    private Game game;

    public Menu(Game game)
    {
        this.game = game;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        Font title = new Font("arial", Font.BOLD, 70);
        Font text = new Font("arial", Font.BOLD, 30);
        Font version = new Font("arial", Font.PLAIN, 20);
        Font button = new Font("arial", Font.BOLD, 25);

        g.setColor(Color.BLACK);
        g.setFont(title);
        g.drawString("The Box Game", 145, 100);

        g.setFont(text);
        g.drawString("By soni801", 320, 140);

        g.setFont(version);
        g.drawString(Game.VERSION, 358, 220);

        g.setFont(button);
        g.drawRoundRect(Game.WIDTH / 2 - 100, 240, 200, 50, 50, 50);
        g.drawString("PLAY", Game.WIDTH / 2 - 100 + 65, 275);

        g.drawRoundRect(Game.WIDTH / 2 - 100, 300, 200, 50, 50, 50);
        g.drawString("HELP", Game.WIDTH / 2 - 100 + 65, 335);

        g.drawRoundRect(Game.WIDTH / 2 - 100, 360, 200, 50, 50, 50);
        g.drawString("SETTINGS", Game.WIDTH / 2 - 100 + 35, 395);

        g.drawRoundRect(Game.WIDTH / 2 - 100, 420, 200, 50, 50, 50);
        g.drawString("QUIT", Game.WIDTH / 2 - 100 + 67, 455);
    }
}