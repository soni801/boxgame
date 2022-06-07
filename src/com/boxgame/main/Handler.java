/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

/**
 * @author Soni
 */
public class Handler
{
    LinkedList<GameObject> object = new LinkedList<>();

    public void tick()
    {
        try { for (GameObject o : object) o.tick(); }
        catch (ConcurrentModificationException ignored) { }
    }

    public void render(Graphics g)
    {
        try { for (GameObject o : object) o.render(g); }
        catch (ConcurrentModificationException ignored) { }
    }

    public void addObject(GameObject object) { this.object.add(object); }
    public void removeObject(GameObject object) { this.object.remove(object); }
}
