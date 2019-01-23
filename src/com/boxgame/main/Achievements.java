package com.boxgame.main;

/*
 * Author: soni801
 */

import java.awt.*;

public class Achievements
{
    public static boolean TOUCH_BACKER_5_TIMES_IN_THE_SAME_LEVEL = false;
    public static boolean DO_ALL_LEVELS_WITHOUT_HITTING_WALL = false;
    public static boolean DO_LEVEL_1_IN_15_SEC = false;

    public int mouseOver;

    private static Game game;

    public Achievements(Game game)
    {
        this.game = game;
    }

    public static void load()
    {
        TOUCH_BACKER_5_TIMES_IN_THE_SAME_LEVEL = Boolean.getBoolean(Game.getProperty(game.achievementsFile, "touchBacker5TimesInTheSameLevel", "false"));
        DO_ALL_LEVELS_WITHOUT_HITTING_WALL = Boolean.getBoolean(Game.getProperty(game.achievementsFile, "doAllLevelsWithoutHittingWall", "false"));
        DO_LEVEL_1_IN_15_SEC = Boolean.getBoolean(Game.getProperty(game.achievementsFile, "doLevel1In15Sec", "false"));
    }

    public static void save()
    {
        Game.setProperty(game.achievementsFile, "touchBacker5TimesInTheSameLevel", String.valueOf(TOUCH_BACKER_5_TIMES_IN_THE_SAME_LEVEL));
        Game.setProperty(game.achievementsFile, "doAllLevelsWithoutHittingWall", String.valueOf(DO_ALL_LEVELS_WITHOUT_HITTING_WALL));
        Game.setProperty(game.achievementsFile, "doLevel1In15Sec", String.valueOf(DO_LEVEL_1_IN_15_SEC));
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.setColor(new Color(0f, 0f, 0f, .25f));

        if (mouseOver == 1)
            g.fillRect(0, 520, Game.WIDTH, 30);

        g.drawImage(game.achievements_menu, 0, -45, null);
    }
}