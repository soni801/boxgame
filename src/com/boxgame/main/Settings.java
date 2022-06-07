/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main;

import java.awt.*;

/**
 * @author Soni
 */
public class Settings
{
    private final KeyInput keyInput;
    private final Game game;

    public int playerImage;
    public boolean smoothCamera;

    public int mouseOver;
    public int page;

    public Settings(KeyInput keyInput, Game game)
    {
        this.keyInput = keyInput;
        this.game = game;

        playerImage = 1;
        smoothCamera = true;

        mouseOver = 0;
        page = 0;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        Font title = new Font("arial", Font.BOLD, 70);
        Font text = new Font("arial", Font.BOLD, 30);
        Font button = new Font("arial", Font.BOLD, 25);

        switch (page)
        {
            case 0 ->
            {
                g.setColor(new Color(0f, 0f, 0f, .25f));
                switch (mouseOver)
                {
                    case 1 -> g.fillRect(0, 430, Game.WIDTH, 30);
                    case 2 -> g.fillRect(0, 460, Game.WIDTH, 30);
                    case 3 -> g.fillRect(0, 490, Game.WIDTH, 30);
                    case 4 -> g.fillRect(0, 520, Game.WIDTH, 30);
                }
                g.drawImage(game.settings_menu, 0, -45, null);
            }
            case 1 ->
            {
                g.setColor(new Color(0f, 0f, 0f, .25f));
                switch (mouseOver)
                {
                    case 1 -> g.fillRect(0, 490, Game.WIDTH, 30);
                    case 2 -> g.fillRect(0, 520, Game.WIDTH, 30);
                }
                if (smoothCamera) g.drawImage(game.on, 185, 474, null);
                else g.drawImage(game.off, 185, 474, null);
                g.drawImage(game.settings_camera_settings_menu, 0, -45, null);
            }
            case 2 ->
            {
                g.setFont(title);
                g.drawString("Controls", 255, 140);
                if (keyInput.editing == 1) g.setColor(Color.ORANGE);
                else g.setColor(Color.BLACK);
                g.drawRoundRect(Game.WIDTH / 2 - 50, 240, 100, 50, 50, 50);
                if (keyInput.editing == 2) g.setColor(Color.ORANGE);
                else g.setColor(Color.BLACK);
                g.drawRoundRect(Game.WIDTH / 2 - 50, 300, 100, 50, 50, 50);
                if (keyInput.editing == 3) g.setColor(Color.ORANGE);
                else g.setColor(Color.BLACK);
                g.drawRoundRect(Game.WIDTH / 2 - 50 - 100 - 10, 300, 100, 50, 50, 50);
                if (keyInput.editing == 4) g.setColor(Color.ORANGE);
                else g.setColor(Color.BLACK);
                g.drawRoundRect(Game.WIDTH / 2 - 50 + 100 + 10, 300, 100, 50, 50, 50);
                if (keyInput.editing == 5) g.setColor(Color.ORANGE);
                else g.setColor(Color.BLACK);
                g.drawRoundRect(Game.WIDTH / 2 - 50 - 100 - 10 - 100 - 10, 240, 100, 50, 50, 50);
                g.setFont(button);
                g.setColor(Color.BLACK);
                g.drawString("Pause", Game.WIDTH / 2 - 50 - 100 - 10 - 100 - 10 + 10, 312);
                g.setFont(text);
                g.drawString(Character.toString(keyInput.upKey), Game.WIDTH / 2 - 50 + 40, 275);
                g.drawString(Character.toString(keyInput.downKey), Game.WIDTH / 2 - 50 + 40, 335);
                g.drawString(Character.toString(keyInput.leftKey), Game.WIDTH / 2 - 50 - 100 - 10 + 40, 335);
                g.drawString(Character.toString(keyInput.rightKey), Game.WIDTH / 2 - 50 + 100 + 10 + 40, 335);
                g.drawString(Character.toString(keyInput.pauseKey), Game.WIDTH / 2 - 50 - 100 - 10 - 100 - 10 + 40, 275);
                g.setFont(button);
                g.drawRoundRect(Game.WIDTH / 2 - 100, 420, 200, 50, 50, 50);
                g.drawString("BACK", Game.WIDTH / 2 - 100 + 65, 455);
            }
            case 3 ->
            {
                g.setColor(new Color(0f, 0f, 0f, .25f));
                switch (mouseOver)
                {
                    case 1 -> g.fillRect(0, 310, Game.WIDTH, 30);
                    case 2 -> g.fillRect(0, 340, Game.WIDTH, 30);
                    case 3 -> g.fillRect(0, 370, Game.WIDTH, 30);
                    case 4 -> g.fillRect(0, 400, Game.WIDTH, 30);
                    case 5 -> g.fillRect(0, 430, Game.WIDTH, 30);
                    case 6 -> g.fillRect(0, 460, Game.WIDTH, 30);
                    case 7 -> g.fillRect(0, 490, Game.WIDTH, 30);
                    case 8 -> g.fillRect(0, 520, Game.WIDTH, 30);
                }
                g.drawImage(game.settings_change_skin_menu, 0, -45, null);
            }
        }
    }
}
