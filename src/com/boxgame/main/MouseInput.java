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

    public MouseInput(Game game, Handler handler, KeyInput keyInput, Settings settings, Menu menu)
    {
        this.game = game;
        this.handler = handler;
        this.keyInput = keyInput;
        this.settings = settings;
        this.menu = menu;
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
                game.gameState = STATE.Game;
                game.level = 1;
                game.loadLevel();
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
            if (mouseOver(mx, my, Game.WIDTH / 2 - 125, 400, 250, 50))
            {
                game.gameState = STATE.Menu;
            }
        }
        else if (game.gameState == STATE.Settings)
        {
            if (!settings.renderDropDownMenu)
            {
                if (mouseOver(mx, my, Game.WIDTH / 2 - 50, 200, 100, 50))
                {
                    keyInput.editing = 1;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 50, 260, 100, 50))
                {
                    keyInput.editing = 2;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 50 - 100 - 10, 260, 100, 50))
                {
                    keyInput.editing = 3;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 50 + 100 + 10, 260, 100, 50))
                {
                    keyInput.editing = 4;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 50 - 100 - 10 - 100 - 10, 200, 100, 50))
                {
                    keyInput.editing = 5;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 350, 200, 50))
                {
                    settings.renderDropDownMenu = true;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 420, 200, 50))
                {
                    if (!game.inGame) game.gameState = STATE.Menu;
                    else game.gameState = STATE.Paused;
                }
            }
            else
            {
                if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 350 - 70 - 35, 300, 35))
                {
                    settings.playerImage = 1;
                    settings.renderDropDownMenu = false;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 350 - 35 - 35, 300, 35))
                {
                    settings.playerImage = 2;
                    settings.renderDropDownMenu = false;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 3 - 150, 350 - 35, 300, 35))
                {
                    settings.playerImage = 3;
                    settings.renderDropDownMenu = false;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 350 + 35 - 35, 300, 35))
                {
                    settings.playerImage = 4;
                    settings.renderDropDownMenu = false;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 350 + 70 - 35, 300, 35))
                {
                    settings.playerImage = 5;
                    settings.renderDropDownMenu = false;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 350 + 105 - 35, 300, 35))
                {
                    settings.playerImage = 6;
                    settings.renderDropDownMenu = false;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 350 + 140 - 35, 300, 35))
                {
                    settings.playerImage = 7;
                    settings.renderDropDownMenu = false;
                }
                else if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 350 + 175 - 35, 300, 35))
                {
                    settings.playerImage = 8;
                    settings.renderDropDownMenu = false;
                }
                else
                {
                    settings.renderDropDownMenu = false;
                }
            }
        }
        else if (game.gameState == STATE.Paused)
        {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 200, 200, 50))
            {
                game.gameState = STATE.Game;
            }
            else if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 260, 200, 50))
            {
                game.gameState = STATE.Help;
            }
            else if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 320, 200, 50))
            {
                game.gameState = STATE.Settings;
            }
            else if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 380, 200, 50))
            {
                System.exit(1);
            }
        }
        else if (game.gameState == STATE.Help)
        {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 420, 200, 50))
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
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
        return mx > x && mx < x + width && my > y && my < y + height;
    }
}