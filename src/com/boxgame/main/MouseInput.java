package com.boxgame.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter
{
    private Game game;
    private Handler handler;
    private KeyInput keyInput;
    private Settings settings;
    private Menu menu;
    private Paused paused;
    private Help help;
    private End end;

    public MouseInput(Game game, Handler handler, KeyInput keyInput, Settings settings, Menu menu, Paused paused, Help help, End end)
    {
        this.game = game;
        this.handler = handler;
        this.keyInput = keyInput;
        this.settings = settings;
        this.menu = menu;
        this.paused = paused;
        this.help = help;
        this.end = end;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == STATE.Menu)
        {
            if (mouseOver(mx, my, 0, 430, Game.WIDTH, 30))
            {
                game.level = 1;
                game.loadLevel();
                game.gameState = STATE.Game;
                game.inGame = true;
            }
            else if (mouseOver(mx, my, 0, 460, Game.WIDTH, 30))
            {
                game.gameState = STATE.Help;
            }
            else if (mouseOver(mx, my, 0, 490, Game.WIDTH, 30))
            {
                game.gameState = STATE.Settings;
            }
            else if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
            {
                System.exit(1);
            }
        }
        else if (game.gameState == STATE.End)
        {
            if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
            {
                game.gameState = STATE.Menu;
            }
        }
        else if (game.gameState == STATE.Settings)
        {
            switch (settings.page)
            {
                case 0 :
                    if (mouseOver(mx, my, 0, 430, Game.WIDTH, 30))
                    {
                        settings.page = 1;
                        settings.mouseOver = 0;
                    }
                    else if (mouseOver(mx, my, 0, 460, Game.WIDTH, 30))
                    {
                        settings.page = 2;
                    }
                    else if (mouseOver(mx, my, 0, 490, Game.WIDTH, 30))
                    {
                        settings.page = 3;
                        settings.mouseOver = 0;
                    }
                    else if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
                    {
                        if (!game.inGame) game.gameState = STATE.Menu;
                        else game.gameState = STATE.Paused;
                    }
                    break;
                case 1 :
                    if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
                    {
                        settings.page = 0;
                    }
                    break;
                case 2 :
                    if (mouseOver(mx, my, Game.WIDTH / 2 - 50, 240, 100, 50))
                    {
                        keyInput.editing = 1;
                    }
                    else if (mouseOver(mx, my, Game.WIDTH / 2 - 50, 300, 100, 50))
                    {
                        keyInput.editing = 2;
                    }
                    else if (mouseOver(mx, my, Game.WIDTH / 2 - 50 - 100 - 10, 300, 100, 50))
                    {
                        keyInput.editing = 3;
                    }
                    else if (mouseOver(mx, my, Game.WIDTH / 2 - 50 + 100 + 10, 300, 100, 50))
                    {
                        keyInput.editing = 4;
                    }
                    else if (mouseOver(mx, my, Game.WIDTH / 2 - 50 - 100 - 10 - 100 - 10, 240, 100, 50))
                    {
                        keyInput.editing = 5;
                    }
                    else if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 420, 200, 50))
                    {
                        settings.page = 0;
                    }
                    break;
                case 3 :
                    if (mouseOver(mx, my, 0, 310, Game.WIDTH, 30))
                    {
                        settings.playerImage = 1;
                        settings.page = 0;
                    }
                    else if (mouseOver(mx, my, 0, 340, Game.WIDTH, 30))
                    {
                        settings.playerImage = 2;
                        settings.page = 0;
                    }
                    else if (mouseOver(mx, my, 0, 370, Game.WIDTH, 30))
                    {
                        settings.playerImage = 3;
                        settings.page = 0;
                    }
                    else if (mouseOver(mx, my, 0, 400, Game.WIDTH, 30))
                    {
                        settings.playerImage = 4;
                        settings.page = 0;
                    }
                    else if (mouseOver(mx, my, 0, 430, Game.WIDTH, 30))
                    {
                        settings.playerImage = 5;
                        settings.page = 0;
                    }
                    else if (mouseOver(mx, my, 0, 460, Game.WIDTH, 30))
                    {
                        settings.playerImage = 6;
                        settings.page = 0;
                    }
                    else if (mouseOver(mx, my, 0, 490, Game.WIDTH, 30))
                    {
                        settings.playerImage = 7;
                        settings.page = 0;
                    }
                    else if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
                    {
                        settings.playerImage = 8;
                        settings.page = 0;
                    }
            }
        }
        else if (game.gameState == STATE.Paused)
        {
            if (mouseOver(mx, my, 0, 400, Game.WIDTH, 30))
            {
                game.gameState = STATE.Game;
            }
            else if (mouseOver(mx, my, 0, 430, Game.WIDTH, 30))
            {
                game.gameState = STATE.Help;
            }
            else if (mouseOver(mx, my, 0, 460, Game.WIDTH, 30))
            {
                game.gameState = STATE.Settings;
            }
            else if (mouseOver(mx, my, 0, 490, Game.WIDTH, 30))
            {
                game.gameState = STATE.Menu;
                for (int i = handler.object.size(); handler.object.size() > 0; i--)
                {
                    GameObject tempObject = handler.object.get(i - 1);

                    handler.removeObject(tempObject);
                }
                game.inGame = false;
            }
            else if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
            {
                System.exit(1);
            }
        }
        else if (game.gameState == STATE.Help)
        {
            if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
            {
                if (!game.inGame) game.gameState = STATE.Menu;
                else game.gameState = STATE.Paused;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == STATE.Menu)
        {
            if (mouseOver(mx, my, 0, 430, Game.WIDTH, 30))
            {
                menu.mouseOver = 1;
            }
            else if (mouseOver(mx, my, 0, 460, Game.WIDTH, 30))
            {
                menu.mouseOver = 2;
            }
            else if (mouseOver(mx, my, 0, 490, Game.WIDTH, 30))
            {
                menu.mouseOver = 3;
            }
            else if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
            {
                menu.mouseOver = 4;
            }
            else menu.mouseOver = 0;
        }
        else if (game.gameState == STATE.Paused)
        {
            if (mouseOver(mx, my, 0, 400, Game.WIDTH, 30))
            {
                paused.mouseOver = 1;
            }
            else if (mouseOver(mx, my, 0, 430, Game.WIDTH, 30))
            {
                paused.mouseOver = 2;
            }
            else if (mouseOver(mx, my, 0, 460, Game.WIDTH, 30))
            {
                paused.mouseOver = 3;
            }
            else if (mouseOver(mx, my, 0, 490, Game.WIDTH, 30))
            {
                paused.mouseOver = 4;
            }
            else if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
            {
                paused.mouseOver = 5;
            }
            else paused.mouseOver = 0;
        }
        else if (game.gameState == STATE.Settings)
        {
            if (settings.page == 0)
            {
                if (mouseOver(mx, my, 0, 430, Game.WIDTH, 30))
                {
                    settings.mouseOver = 1;
                }
                else if (mouseOver(mx, my, 0, 460, Game.WIDTH, 30))
                {
                    settings.mouseOver = 2;
                }
                else if (mouseOver(mx, my, 0, 490, Game.WIDTH, 30))
                {
                    settings.mouseOver = 3;
                }
                else if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
                {
                    settings.mouseOver = 4;
                }
                else settings.mouseOver = 0;
            }
            else if (settings.page == 1)
            {
                if (mouseOver(mx, my, 0, 520, Game.WIDTH , 30))
                {
                    settings.mouseOver = 1;
                }
                else settings.mouseOver = 0;
            }
            else if (settings.page == 3)
            {
                if (mouseOver(mx, my, 0, 310, Game.WIDTH, 30))
                {
                    settings.mouseOver = 1;
                }
                else if (mouseOver(mx, my, 0, 340, Game.WIDTH, 30))
                {
                    settings.mouseOver = 2;
                }
                else if (mouseOver(mx, my, 0, 370, Game.WIDTH, 30))
                {
                    settings.mouseOver = 3;
                }
                else if (mouseOver(mx, my, 0, 400, Game.WIDTH, 30))
                {
                    settings.mouseOver = 4;
                }
                else if (mouseOver(mx, my, 0, 430, Game.WIDTH, 30))
                {
                    settings.mouseOver = 5;
                }
                else if (mouseOver(mx, my, 0, 460, Game.WIDTH, 30))
                {
                    settings.mouseOver = 6;
                }
                else if (mouseOver(mx, my, 0, 490, Game.WIDTH, 30))
                {
                    settings.mouseOver = 7;
                }
                else if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
                {
                    settings.mouseOver = 8;
                }
                else settings.mouseOver = 0;
            }
        }
        else if (game.gameState == STATE.Help)
        {
            if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
            {
                help.mouseOver = 1;
            }
            else help.mouseOver = 0;
        }
        else if (game.gameState == STATE.End)
        {
            if (mouseOver(mx, my, 0, 520, Game.WIDTH, 30))
            {
                end.mouseOver = 1;
            }
            else end.mouseOver = 0;
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
        return mx > x && mx < x + width && my > y && my < y + height;
    }
}