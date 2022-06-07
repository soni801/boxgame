/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main;

import java.awt.*;

/**
 * @author Soni
 */
public class Paused
{
    public int mouseOver;

    private final Game game;
    private final HUD hud;

    public Paused(Game game, HUD hud)
    {
        this.game = game;
        this.hud = hud;

        mouseOver = 0;
    }

    public void tick()
    {
        long secondsSpent = (System.currentTimeMillis() - hud.startTime) / 1000;
        while (secondsSpent >= 60) secondsSpent -= 60;

        hud.timeSpent[0] = game.ensureLength(String.valueOf(System.currentTimeMillis() - hud.startTime), 3);
        hud.timeSpent[1] = game.ensureLength(String.valueOf(secondsSpent), 2);
        hud.timeSpent[2] = game.ensureLength(String.valueOf((System.currentTimeMillis() - hud.startTime) / 1000 / 60), 2);
    }

    public void render(Graphics g)
    {
        g.setColor(new Color(0f, 0f, 0f, .25f));

        switch (mouseOver)
        {
            case 1 -> g.fillRect(0, 400, Game.WIDTH, 30);
            case 2 -> g.fillRect(0, 430, Game.WIDTH, 30);
            case 3 -> g.fillRect(0, 460, Game.WIDTH, 30);
            case 4 -> g.fillRect(0, 490, Game.WIDTH, 30);
            case 5 -> g.fillRect(0, 520, Game.WIDTH, 30);
        }

        g.drawImage(game.pause_menu, 0, -45, null);

        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.PLAIN, 30));
        g.drawString(String.format("%s:%s.%s", hud.timeSpent[2], hud.timeSpent[1], hud.timeSpent[0]), 10, 30);
    }
}
