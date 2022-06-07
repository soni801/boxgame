/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Soni
 */
public class KeyInput extends KeyAdapter
{
    public int editing;

    public char upKey, downKey, leftKey, rightKey, pauseKey;

    public int up, down, left, right, pause;

    private final Handler handler;
    private final Game game;

    public KeyInput(Handler handler, Game game)
    {
        this.handler = handler;
        this.game = game;

        upKey = 'w';
        downKey = 's';
        leftKey = 'a';
        rightKey = 'd';
        pauseKey = '\u001B';

        up = 87;
        down = 83;
        left = 65;
        right = 68;
        pause = 27;

        editing = 0;

        /*
         * 0 = not editing
         * 1 = editing upKey
         * 2 = editing downKey
         * 3 = editing leftKey
         * 4 = editing rightKey
         */
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        char keyChar = e.getKeyChar();

        switch (editing)
        {
            case 0 :
                switch (game.gameState)
                {
                    case Game:
                        for (GameObject o : handler.object)
                        {
                            if (o instanceof Player)
                            {
                                if (key == up) ((Player) o).up = true;
                                if (key == down) ((Player) o).down = true;
                                if (key == left) ((Player) o).left = true;
                                if (key == right) ((Player) o).right = true;
                            }
                        }
                        if (key == pause) game.gameState = STATE.Paused;
                        break;
                    case Paused: if (key == pause) game.gameState = STATE.Game;
                }
                break;
            case 1 :
                up = key;
                upKey = keyChar;
                editing = 0;
                break;
            case 2 :
                down = key;
                downKey = keyChar;
                editing = 0;
                break;
            case 3 :
                left = key;
                leftKey = keyChar;
                editing = 0;
                break;
            case 4 :
                right = key;
                rightKey = keyChar;
                editing = 0;
                break;
            case 5 :
                pause= key;
                pauseKey = keyChar;
                editing = 0;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        for (GameObject o : handler.object)
        {
            if (o instanceof Player)
            {
                if (key == up) ((Player) o).up = false;
                if (key == down) ((Player) o).down = false;
                if (key == left) ((Player) o).left = false;
                if (key == right) ((Player) o).right = false;
            }
        }
    }
}
