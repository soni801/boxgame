/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.object;

import com.boxgame.main.Game;
import com.boxgame.main.GameObject;
import com.boxgame.main.Handler;
import com.boxgame.main.util.SpriteSheet;
import com.boxgame.state.Achievements;
import com.boxgame.state.Settings;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Soni
 */
public class Player extends GameObject
{
    public boolean up, down, left, right;

    private boolean loaded;
    private int timer;
    private int time = 0;
    private int timeTimer = 0;

    private BufferedImage player_image;

    private final Handler handler;
    private final Game game;
    private final Settings settings;

    public Player(int x, int y, Handler handler, Game game, Settings settings)
    {
        super(x, y);
        this.handler = handler;
        this.game = game;
        this.settings = settings;

        loaded = false;
        timer = 0;
    }

    public void tick()
    {
        // Handle movement
        if (up && down) vel[1] = 0;
        else if (up) vel[1] = -5;
        else if (down) vel[1] = 5;
        else vel[1] = 0;

        if (left && right) vel[0] = 0;
        else if (left) vel[0] = -5;
        else if (right) vel[0] = 5;
        else vel[0] = 0;

        // Handle collision
        // This is suboptimal, as collision is also handled for objects where a collision is impossible
        for (GameObject o : handler.object)
        {
            if (this.getNextBounds().intersects(o.getBounds()))
            {
                // Execute action based on type of object
                if (o instanceof Block)
                {
                    if (o.pos[0] + 64 <= pos[0] + 5 || o.pos[0] >= pos[0] + 32 - 5) vel[0] = 0; // Horizontal collision
                    if (o.pos[1] + 64 <= pos[1] + 5 || o.pos[1] >= pos[1] + 32 - 5) vel[1] = 0; // Vertical collision
                    Achievements.ACHIEVEMENT_3_STATUS = false;
                }
                else if (o instanceof Finish)
                {
                    if (game.level == 1 && time <= 15) Achievements.ACHIEVEMENT_2 = true;
                    if (!loaded)
                    {
                        game.level++;
                        game.loadLevel();
                        Achievements.ACHIEVEMENT_1_PROGRESS = 0;
                        loaded = true;
                        timer = 100;
                    }
                }
                else if (o instanceof Teleporter)
                {
                    switch (game.level)
                    {
                        case 9 ->
                        {
                            pos[0] = 140;
                            pos[1] = 1300;
                        }
                        case 10 ->
                        {
                            pos[0] = 2450;
                            pos[1] = 2440;
                        }
                        case 11 ->
                        {
                            pos[0] = 2580;
                            pos[1] = 1040;
                        }
                    }
                }
                else if (o instanceof Backer)
                {
                    game.loadLevel();
                    Achievements.ACHIEVEMENT_1_PROGRESS++;
                }
            }
        }

        // Update player position
        pos[0] += vel[0];
        pos[1] += vel[1];

        if (timer > 0) timer--;
        if (timer == 0) loaded = false;

        SpriteSheet ss = new SpriteSheet(game.sprite_sheet);
        switch (settings.playerImage)
        {
            case 1 -> player_image = ss.grabImage(1, 1, 32, 32);
            case 2 -> player_image = ss.grabImage(1, 2, 32, 32);
            case 3 -> player_image = ss.grabImage(1, 3, 32, 32);
            case 4 -> player_image = ss.grabImage(1, 4, 32, 32);
            case 5 -> player_image = ss.grabImage(2, 1, 32, 32);
            case 6 -> player_image = ss.grabImage(2, 2, 32, 32);
            case 7 -> player_image = ss.grabImage(2, 3, 32, 32);
            case 8 -> player_image = ss.grabImage(2, 4, 32, 32);
            default -> player_image = null;
        }

        if (game.level == 1)
        {
            if (timeTimer >= 60)
            {
                time++;
                timeTimer = 0;
            }
            else timeTimer++;
        }

        if (Achievements.ACHIEVEMENT_1_PROGRESS == 5) Achievements.ACHIEVEMENT_1 = true;
    }

    public void render(Graphics g)
    {
        g.drawImage(player_image, pos[0], pos[1], 32, 32, null);
    }

    public Rectangle getBounds()
    {
        return new Rectangle(pos[0], pos[1], 32, 32);
    }
    private Rectangle getNextBounds()
    {
        return new Rectangle(pos[0] + vel[0], pos[1] + vel[1], 32, 32);
    }
}
