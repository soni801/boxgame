package com.boxgame.main;

import java.awt.*;

public class Settings
{
    private KeyInput keyInput;
    private Game game;

    public boolean renderDropDownMenu;
    public int playerImage;
    private String playerImageString;

    public Settings(KeyInput keyInput, Game game)
    {
        this.keyInput = keyInput;
        this.game = game;

        renderDropDownMenu = false;
        playerImage = 1;
        playerImageString = "Diamond";
    }

    public void tick()
    {
        switch (playerImage)
        {
            case 1  : playerImageString = "Diamond";          break;
            case 2  : playerImageString = "Golden Apple";     break;
            case 3  : playerImageString = "Golden Carrot";    break;
            case 4  : playerImageString = "Gunpowder";        break;
            case 5  : playerImageString = "Glinsering Melon"; break;
            case 6  : playerImageString = "Coal";             break;
            case 7  : playerImageString = "Lapis Lazuli";     break;
            case 8  : playerImageString = "Redstone Dust";    break;
            default : playerImageString = null;
        }
    }

    public void render(Graphics g)
    {
        Font title = new Font("arial", Font.BOLD, 70);
        Font text = new Font("arial", Font.BOLD, 30);
        Font button = new Font("arial", Font.BOLD, 25);

        g.setColor(Color.BLACK);
        g.setFont(title);
        g.drawString("Settings", 260, 100);

        if (renderDropDownMenu)
        {
            g.setFont(button);
            g.drawRoundRect(Game.WIDTH / 2 - 150, 350 - 105, 300, 10 + 35 + 35 + 35 + 35 + 35 + 35 +35 + 35 + 10, 50, 50);
            g.drawString("Diamond", Game.WIDTH / 2 - 150 + 10, 350 - 70);
            g.drawString("Golden Apple", Game.WIDTH / 2 - 150 + 10, 350 - 35);
            g.drawString("Golden Carrot", Game.WIDTH / 2 - 150 + 10, 350);
            g.drawString("Gunpowder", Game.WIDTH / 2 - 150 + 10, 350 + 35);
            g.drawString("Glinsering Melon", Game.WIDTH / 2 - 150 + 10, 350 + 70);
            g.drawString("Coal", Game.WIDTH / 2 - 150 + 10, 350 + 105);
            g.drawString("Lapis Lazuli", Game.WIDTH / 2 - 150 + 10, 350 + 140);
            g.drawString("Redstone Dust", Game.WIDTH / 2 - 150 + 10, 350 + 175);
        }
        else
        {
            g.setFont(text);
            g.drawString("Controls", 337, 180);

            if (keyInput.editing == 1) g.setColor(Color.ORANGE); else g.setColor(Color.BLACK); g.drawRoundRect(Game.WIDTH / 2 - 50, 200, 100, 50, 50, 50);
            if (keyInput.editing == 2) g.setColor(Color.ORANGE); else g.setColor(Color.BLACK); g.drawRoundRect(Game.WIDTH / 2 - 50, 260, 100, 50, 50, 50);
            if (keyInput.editing == 3) g.setColor(Color.ORANGE); else g.setColor(Color.BLACK); g.drawRoundRect(Game.WIDTH / 2 - 50 - 100 - 10, 260, 100, 50, 50, 50);
            if (keyInput.editing == 4) g.setColor(Color.ORANGE); else g.setColor(Color.BLACK); g.drawRoundRect(Game.WIDTH / 2 - 50 + 100 + 10, 260, 100, 50, 50, 50);
            if (keyInput.editing == 5) g.setColor(Color.ORANGE); else g.setColor(Color.BLACK); g.drawRoundRect(Game.WIDTH / 2 - 50 - 100 - 10 - 100 - 10, 200, 100, 50, 50, 50);

            g.setFont(button);
            g.setColor(Color.BLACK);
            g.drawString("Pause", Game.WIDTH / 2 - 50 - 100 - 10 - 100 - 10 + 10, 272);

            g.setFont(text);
            g.drawString(Character.toString(keyInput.upKey), Game.WIDTH / 2 - 50 + 40, 235);
            g.drawString(Character.toString(keyInput.downKey), Game.WIDTH / 2 - 50 + 40, 295);
            g.drawString(Character.toString(keyInput.leftKey), Game.WIDTH / 2 - 50 - 100 - 10 + 40, 295);
            g.drawString(Character.toString(keyInput.rightKey), Game.WIDTH / 2 - 50 + 100 + 10 + 40, 295);
            g.drawString(Character.toString(keyInput.pauseKey), Game.WIDTH / 2 - 50 - 100 - 10 - 100 - 10 + 40, 235);

            g.drawString("Skin", 370, 345);

            g.setFont(button);
            g.drawRoundRect(Game.WIDTH / 2 - 100, 350, 200, 50, 50, 50);
            g.drawString(playerImageString, Game.WIDTH / 2 - 100 + 10, 350 + 35);

            g.drawRoundRect(Game.WIDTH / 2 - 100, 420, 200, 50, 50, 50);
            g.drawString("BACK", Game.WIDTH / 2 - 100 + 65, 455);
        }
    }
}