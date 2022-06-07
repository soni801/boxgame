/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.state;

import com.boxgame.main.Game;

import java.awt.*;

/**
 * @author Soni
 */
public class End
{
    public int mouseOver;

    private final Game game;
    private final HUD hud;

    public End(Game game, HUD hud)
    {
        this.game = game;
        this.hud = hud;

        mouseOver = 0;
    }

    public void tick()
    {
        if (Achievements.ACHIEVEMENT_3_STATUS) Achievements.ACHIEVEMENT_3 = true;
    }

    public void render(Graphics g)
    {
        g.setColor(new Color(0f, 0f, 0f, .25f));

        if (mouseOver == 1) g.fillRect(0, 520, Game.WIDTH, 30);

        g.drawImage(game.endMenu, 0, -45, null);

        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.PLAIN, 30));
        g.drawString(String.format("Final time: %s:%s.%s", hud.timeSpent[2], hud.timeSpent[1], hud.timeSpent[0]), 10, 30);
    }
}
