package com.boxgame.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject
{
    private boolean collision;
    private boolean loaded;
    private int timer;
    private int time = 0;
    private int timeTimer = 0;

    private BufferedImage player_image;

    private Handler handler;
    private Game game;
    private Settings settings;

    public Player(int x, int y, ID id, Handler handler, Game game, Settings settings)
    {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        this.settings = settings;

        collision = false;
        loaded = false;
        timer = 0;
    }

    public void tick()
    {
        if (!collision)
        {
            x += velX;
            y += velY;
        }
        collision = false;

        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (getBounds().intersects(tempObject.getBounds()))
            {
                switch (tempObject.getId())
                {
                    case Block :
                        x += velX * -1;
                        y += velY * -1;
                        collision = true;
                        Achievements.ACHIEVEMENT_3_STATUS = false;
                        break;
                    case Finish :
                        if (game.level == 1)
                        {
                            if (time <= 15) Achievements.ACHIEVEMENT_2 = true;
                        }
                        if (!loaded)
                        {
                            game.level++;
                            game.loadLevel();
                            Achievements.ACHIEVEMENT_1_PROGRESS = 0;
                            loaded = true;
                            timer = 100;
                        }
                        break;
                    case Teleporter :
                        switch (game.level)
                        {
                            case 9  : x = 140;  y = 1300; break;
                            case 10 : x = 2450; y = 2440; break;
                            case 11 : x = 2580; y = 1040; break;
                        }
                        break;
                    case Backer :
                        game.loadLevel();
                        Achievements.ACHIEVEMENT_1_PROGRESS++;
                        break;
                }
            }
        }

        //System.out.println(x + ", " + y);

        if (timer > 0) timer--;
        if (timer == 0) loaded = false;

        if (handler.isUp()) velY = -5;
        else if (handler.isDown()) velY = 5;
        else velY = 0;

        if (handler.isLeft()) velX = -5;
        else if (handler.isRight()) velX = 5;
        else velX = 0;

        SpriteSheet ss = new SpriteSheet(game.sprite_sheet);
        switch (settings.playerImage)
        {
            case 1  : player_image = ss.grabImage(1, 1, 32, 32); break;
            case 2  : player_image = ss.grabImage(1, 2, 32, 32); break;
            case 3  : player_image = ss.grabImage(1, 3, 32, 32); break;
            case 4  : player_image = ss.grabImage(1, 4, 32, 32); break;
            case 5  : player_image = ss.grabImage(2, 1, 32, 32); break;
            case 6  : player_image = ss.grabImage(2, 2, 32, 32); break;
            case 7  : player_image = ss.grabImage(2, 3, 32, 32); break;
            case 8  : player_image = ss.grabImage(2, 4, 32, 32); break;
            default : player_image = null;
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

    public Rectangle getBounds()
    {
        return new Rectangle(x + 5, y + 4, 23, 26);
    }
}