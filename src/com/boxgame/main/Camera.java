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
                if (x >= 304) x = 304;
                if (y >= 533) y = 533;
                break;
            case 2 :
                if (x >= 688) x = 688;
                if (y >= 917) y = 917;
                break;
            case 3 :
                if (x >= 944) x = 944;
                if (y >= 1173) y = 1173;
                break;
            case 4 :
                if (x >= 1200) x = 1200;
                if (y >= 1429) y = 1429;
                break;
            case 5 :
                if (x >= 1456) x = 1456;
                if (y >= 1685) y = 1685;
                break;
            case 6 :
                if (x >= 1712) x = 1712;
                if (y >= 1941) y = 1941;
                break;
            default :
                if (x >= 1840) x = 1840;
                if (y >= 2069) y = 2069;
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