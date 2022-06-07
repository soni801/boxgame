/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main;

import java.awt.*;

/**
 * @author Soni
 */
public class Achievements
{
    public static boolean ACHIEVEMENT_1 = false; // Touch red block 5 times in the same level
    public static boolean ACHIEVEMENT_2 = false; // Do level 1 in 15 seconds
    public static boolean ACHIEVEMENT_3 = false; // Do all levels without touching the wall once

    public static int ACHIEVEMENT_1_PROGRESS;
    public static boolean ACHIEVEMENT_3_STATUS = true;

    public int mouseOver;

    private static Game game;

    public Achievements(Game game)
    {
        Achievements.game = game;
    }

    public static void load()
    {
        ACHIEVEMENT_1 = Boolean.parseBoolean(Game.getProperty(game.achievementsFile, "achievement-1", "false"));
        ACHIEVEMENT_2 = Boolean.parseBoolean(Game.getProperty(game.achievementsFile, "achievement-2", "false"));
        ACHIEVEMENT_3 = Boolean.parseBoolean(Game.getProperty(game.achievementsFile, "achievement-3", "false"));
    }

    public static void save()
    {
        Game.setProperty(game.achievementsFile, "achievement-1", String.valueOf(ACHIEVEMENT_1));
        Game.setProperty(game.achievementsFile, "achievement-2", String.valueOf(ACHIEVEMENT_2));
        Game.setProperty(game.achievementsFile, "achievement-3", String.valueOf(ACHIEVEMENT_3));
    }

    public void tick() { }

    public void render(Graphics g)
    {
        g.setColor(new Color(0f, 0f, 0f, .25f));

        if (mouseOver == 1)
            g.fillRect(0, 520, Game.WIDTH, 30);

        g.drawImage(game.achievements_menu, 0, -45, null);

        g.setFont(new Font("arial", Font.BOLD, 30));
        FontMetrics metrics = g.getFontMetrics(new Font("arial", Font.BOLD, 30));

        g.setColor(ACHIEVEMENT_1 ? Color.GREEN : Color.RED);
        g.drawString("Touch red block 5 times in the same level", Game.WIDTH / 2 - metrics.stringWidth("Touch red block 5 times in the same level") / 2, 200);

        g.setColor(ACHIEVEMENT_2 ? Color.GREEN : Color.RED);
        g.drawString("Do level 1 in 15 seconds", Game.WIDTH / 2 - metrics.stringWidth("Do level 1 in 15 seconds") / 2, 300);

        g.setColor(ACHIEVEMENT_3 ? Color.GREEN : Color.RED);
        g.drawString("Do all levels without touching the wall once", Game.WIDTH / 2 - metrics.stringWidth("Do all levels without touching the wall once") / 2, 400);
    }
}
