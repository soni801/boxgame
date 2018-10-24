package com.boxgame.main;

public class Camera
{
    private float x, y;

    private Game game;
    private Settings settings;

    public Camera(float x, float y, Game game, Settings settings)
    {
        this.x = x;
        this.y = y;
        this.game = game;
        this.settings = settings;
    }

    public void tick(GameObject object)
    {
        if (settings.smoothCamera)
        {
            x += ((object.getX() - x)-Game.WIDTH / 2) * 0.05f;
            y += ((object.getY() - y)-Game.HEIGHT / 2) * 0.05f;
        }
        else
        {
            x += ((object.getX() - x)-Game.WIDTH / 2);
            y += ((object.getY() - y)-Game.HEIGHT / 2);
        }

        if (x <= 0) x = 0;
        if (y <= 0) y = 0;

        switch (game.level)
        {
            case 1 :
                if (x >= 294) x = 294;
                if (y >= 523) y = 523;
                break;
            case 2 :
                if (x >= 678) x = 678;
                if (y >= 907) y = 907;
                break;
            case 3 :
                if (x >= 934) x = 934;
                if (y >= 1163) y = 1163;
                break;
            case 4 :
                if (x >= 1190) x = 1190;
                if (y >= 1419) y = 1419;
                break;
            case 5 :
                if (x >= 1446) x = 1446;
                if (y >= 1675) y = 1675;
                break;
            case 6 :
                if (x >= 1702) x = 1702;
                if (y >= 1931) y = 1931;
                break;
            case 7 :
                if (x >= 1830) x = 1830;
                if (y >= 2059) y = 2059;
                break;
            case 8 :
                if (x >= 1830) x = 1830;
                if (y >= 2059) y = 2059;
                break;
            case 9 :
                if (x >= 1830) x = 1830;
                if (y >= 2059) y = 2059;
                break;
            case 10 :
                if (x >= 1830) x = 1830;
                if (y >= 2059) y = 2059;
                break;
            case 11 :
                if (x >= 1958) x = 1958;
                if (y >= 2187) y = 2187;
        }
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }
}