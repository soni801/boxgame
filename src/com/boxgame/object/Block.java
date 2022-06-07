/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.object;

import com.boxgame.main.Game;
import com.boxgame.main.GameObject;

import java.awt.*;

/**
 * @author Soni
 */
public class Block extends GameObject
{
    private final Game game;

    public Block(int x, int y, Game game)
    {
        super(x, y);
        this.game = game;
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {g.drawImage(game.blockTexture, pos[0], pos[1], 64,64, null);

    }

    public Rectangle getBounds()
    {
        return new Rectangle(pos[0], pos[1], 64, 64);
    }
}
