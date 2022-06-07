/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main.util;

import java.awt.image.BufferedImage;

/**
 * @author Soni
 */
public class TextureAtlas
{
    private final BufferedImage atlas;
    private final int tileSize;

    public TextureAtlas(BufferedImage atlas, int tileSize)
    {
        this.atlas = atlas;
        this.tileSize = tileSize;
    }

    public BufferedImage grabImage(int col, int row)
    {
        return atlas.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
    }
}
