package com.boxgame.main;

public class Camera
{
    private float x, y;

    private Game game;

    public Camera(float x, float y, Game game)
    {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    public void tick(GameObject object)
    {
        x += ((object.getX()-x)-Game.WIDTH/2)*0.05f;
        y += ((object.getY()-y)-Game.HEIGHT/2)*0.05f;

        if (x <= 0) x = 0;
        if (y <= 0) y = 0;

        switch (game.level)
        {
            case 1 :
                if (x >= 290) x = 290;
                if (y >= 520) y = 520;
                break;
            case 2 :
                if (x >= 680) x = 680;
                if (y >= 900) y = 900;
                break;
            case 3 :
                if (x >= 935) x = 935;
                if (y >= 1160) y = 1160;
                break;
            case 4 :
                if (x >= 1190) x = 1190;
                if (y >= 1415) y = 1415;
                break;
            case 5 :
                if (x >= 1450) x = 1450;
                if (y >= 1670) y = 1670;
                break;
            case 6 :
                if (x >= 1700) x = 1700;
                if (y >= 1925) y = 1925;
                break;
            default :
                if (x >= 1830) x = 1830;
                if (y >= 2055) y = 2055;
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