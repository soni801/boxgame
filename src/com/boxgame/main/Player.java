package com.boxgame.main;

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

    public Player(int x, int y, ID id, Handler handler, Game game, Settings settings)
    {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        this.settings = settings;

        loaded = false;
        timer = 0;
    }

    public void tick()
    {
        // Handle movement
        if (up && down) velY = 0;
        else if (up) velY = -5;
        else if (down) velY = 5;
        else velY = 0;

        if (left && right) velX = 0;
        else if (left) velX = -5;
        else if (right) velX = 5;
        else velX = 0;

        // Handle collision
        for (GameObject o : handler.object)
        {
            if (this.getNextBounds().intersects(o.getBounds()))
            {
                switch (o.getId())
                {
                    case Block:
                        if (o.x + 64 <= x + 5 || o.x >= x + 32 - 5) velX = 0; // Horizontal collision
                        if (o.y + 64 <= y + 5 || o.y >= y + 32 - 5) velY = 0; // Vertical collision
                        Achievements.ACHIEVEMENT_3_STATUS = false;
                        break;
                    case Finish:
                        if (game.level == 1 && time <= 15) Achievements.ACHIEVEMENT_2 = true;
                        if (!loaded)
                        {
                            game.level++;
                            game.loadLevel();
                            Achievements.ACHIEVEMENT_1_PROGRESS = 0;
                            loaded = true;
                            timer = 100;
                        }
                        break;
                    case Teleporter:
                        switch (game.level)
                        {
                            case 9 ->
                            {
                                x = 140;
                                y = 1300;
                            }
                            case 10 ->
                            {
                                x = 2450;
                                y = 2440;
                            }
                            case 11 ->
                            {
                                x = 2580;
                                y = 1040;
                            }
                        }
                        break;
                    case Backer:
                        game.loadLevel();
                        Achievements.ACHIEVEMENT_1_PROGRESS++;
                }
            }
        }

        // Update player position
        x += velX;
        y += velY;

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
        g.drawImage(player_image, x, y, 32, 32, null);
    }

    public Rectangle getBounds() { return new Rectangle(x, y, 32, 32); }
    private Rectangle getNextBounds() { return new Rectangle(x + velX, y + velY, 32, 32); }
}