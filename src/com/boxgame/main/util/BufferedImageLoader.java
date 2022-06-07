/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Soni
 */
public class BufferedImageLoader
{
    BufferedImage image;

    public BufferedImage loadImage(String path)
    {
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;
    }
}
