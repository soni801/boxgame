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
                if (game.gameState == STATE.Game)
                {
                    for (int i = 0; i < handler.object.size(); i++)
                    {
                        GameObject tempObject = handler.object.get(i);

                        if (tempObject.getId() == ID.Player)
                        {
                            if (key == up) handler.setUp(true);
                            if (key == down) handler.setDown(true);
                            if (key == left) handler.setLeft(true);
                            if (key == right) handler.setRight(true);
                        }
                    }
                    if (key == pause)
                    {
                        game.gameState = STATE.Paused;
                    }
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

        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player)
            {
                if (key == up) handler.setUp(false);
                if (key == down) handler.setDown(false);
                if (key == left) handler.setLeft(false);
                if (key == right) handler.setRight(false);
            }
        }
    }
}