package com.boxgame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    public int editing;

    public char upKey, downKey, leftKey, rightKey, pauseKey;

    private Handler handler;
    private Game game;

    public KeyInput(Handler handler, Game game)
    {
        this.handler = handler;
        this.game = game;

        upKey = 'w';
        downKey = 's';
        leftKey = 'a';
        rightKey = 'd';
        pauseKey = 'p';

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
        char key = e.getKeyChar();

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
                            if (key == upKey) handler.setUp(true);
                            if (key == downKey) handler.setDown(true);
                            if (key == leftKey) handler.setLeft(true);
                            if (key == rightKey) handler.setRight(true);
                        }
                    }
                    if (key == pauseKey)
                    {
                        game.gameState = STATE.Paused;
                    }
                }
                break;
            case 1 :
                upKey = key;
                editing = 0;
                break;
            case 2 :
                downKey = key;
                editing = 0;
                break;
            case 3 :
                leftKey = key;
                editing = 0;
                break;
            case 4 :
                rightKey = key;
                editing = 0;
                break;
            case 5 :
                pauseKey = key;
                editing = 0;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        char key = e.getKeyChar();

        for (int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player)
            {
                if (key == upKey) handler.setUp(false);
                if (key == downKey) handler.setDown(false);
                if (key == leftKey) handler.setLeft(false);
                if (key == rightKey) handler.setRight(false);
            }
        }
    }
}