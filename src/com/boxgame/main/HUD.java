package com.boxgame.main;

import java.awt.*;

/**
 * @author Soni
 */
public class HUD
{
    public long startTime;
    public String[] timeSpent = new String[3];

    private final Game game;

    public HUD(Game game)
    {
        this.game = game;
    }

    public void tick()
    {
        long secondsSpent = (System.currentTimeMillis() - startTime) / 1000;
        while (secondsSpent >= 60) secondsSpent -= 60;

        timeSpent[0] = ensureLength(String.valueOf(System.currentTimeMillis() - startTime), 3);
        timeSpent[1] = ensureLength(String.valueOf(secondsSpent), 2);
        timeSpent[2] = ensureLength(String.valueOf((System.currentTimeMillis() - startTime) / 1000 / 60), 2);
    }

    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.PLAIN, 30));
        g.drawString(String.format("%s:%s.%s", timeSpent[2], timeSpent[1], timeSpent[0]), 10, 30); // TODO: Add option to leave as ms
        g.drawString("Level " + game.level, 10, Game.HEIGHT - 50);
        if (game.level == 11)
        {
            g.setFont(new Font("arial", Font.PLAIN, 20));
            g.drawString("LAST LEVEL", 10, Game.HEIGHT - 50 - 30);
        }
    }

    private String ensureLength(String input, int length)
    {
        if (input.length() > length) input = input.substring(input.length() - length);
        else if (input.length() < length)
        {
            // This uses StringBuilder because that is apparently better than +=
            StringBuilder inputBuilder = new StringBuilder(input);
            for (int i = length - inputBuilder.length(); i > 0; i--) inputBuilder.insert(0, "0");
            input = inputBuilder.toString();
        }
        return input;
    }
}