/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.object;

import com.boxgame.main.Game;
import com.boxgame.main.GameObject;
import com.boxgame.main.types.ID;

import java.awt.*;

/**
 * @author Soni
 */
public class Block extends GameObject
{
    private final Game game;

    public Block(int x, int y, ID id, Game game)
    {
        super(x, y, id);
        this.game = game;
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {g.drawImage(game.block, x, y, 64,64, null);

    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 64, 64);
    }
}
