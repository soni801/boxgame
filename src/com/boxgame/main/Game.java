/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main;

import com.boxgame.input.KeyInput;
import com.boxgame.input.MouseInput;
import com.boxgame.main.util.BufferedImageLoader;
import com.boxgame.main.util.Window;
import com.boxgame.object.*;
import com.boxgame.state.Menu;
import com.boxgame.state.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * @author Soni
 */
public class Game extends Canvas implements Runnable
{
    public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
    public static final String VERSION = "v2.1";
    public int level;
    public boolean inGame;

    private final Handler handler;
    private Thread thread;
    private boolean running = false;

    private final Menu menu;
    private final Camera camera;
    private final End end;
    private final HUD hud;
    private final Settings settings;
    private final Paused paused;
    private final Help help;
    private final Credits credits;
    private final Achievements achievements;

    private final BufferedImage[] levels;

    public BufferedImage main_menu;
    public BufferedImage pause_menu;
    public BufferedImage settings_menu;
    public BufferedImage settings_change_skin_menu;
    public BufferedImage settings_camera_settings_menu;
    public BufferedImage help_menu;
    public BufferedImage end_menu;
    public BufferedImage credits_menu;
    public BufferedImage achievements_menu;

    public BufferedImage block;
    public BufferedImage floor;
    public BufferedImage finish;
    public BufferedImage teleporter;
    public BufferedImage backer;

    public BufferedImage logo;
    public BufferedImage sprite_sheet;
    public BufferedImage on;
    public BufferedImage off;

    public State gameState = State.Menu;

    public Game()
    {
        BufferedImageLoader loader = new BufferedImageLoader();

        levels = new BufferedImage[11];
        levels[0] = loader.loadImage("/levels/level1.png");
        levels[1] = loader.loadImage("/levels/level2.png");
        levels[2] = loader.loadImage("/levels/level3.png");
        levels[3] = loader.loadImage("/levels/level4.png");
        levels[4] = loader.loadImage("/levels/level5.png");
        levels[5] = loader.loadImage("/levels/level6.png");
        levels[6] = loader.loadImage("/levels/level7.png");
        levels[7] = loader.loadImage("/levels/level8.png");
        levels[8] = loader.loadImage("/levels/level9.png");
        levels[9] = loader.loadImage("/levels/level10.png");
        levels[10] = loader.loadImage("/levels/level11.png");

        main_menu = loader.loadImage("/menus/main_menu.png");
        pause_menu = loader.loadImage("/menus/pause_menu.png");
        settings_menu = loader.loadImage("/menus/settings_menu.png");
        settings_change_skin_menu = loader.loadImage("/menus/settings_change_skin_menu.png");
        settings_camera_settings_menu = loader.loadImage("/menus/settings_camera_settings_menu.png");
        help_menu = loader.loadImage("/menus/help_menu.png");
        end_menu = loader.loadImage("/menus/end_menu.png");
        credits_menu = loader.loadImage("/menus/credits_menu.png");
        achievements_menu = loader.loadImage("/menus/achievements_menu.png");

        block = loader.loadImage("/textures/block.png");
        floor = loader.loadImage("/textures/floor.png");
        finish = loader.loadImage("/textures/finish.png");
        teleporter = loader.loadImage("/textures/teleporter.png");
        backer = loader.loadImage("/textures/backer.png");

        logo = loader.loadImage("/logo.png");
        sprite_sheet = loader.loadImage("/sprite_sheet.png");
        on = loader.loadImage("/on.png");
        off = loader.loadImage("/off.png");

        KeyInput keyInput;
        MouseInput mouseInput;

        handler = new Handler();
        menu = new Menu(this);
        hud = new HUD(this);
        end = new End(this, hud);
        paused = new Paused(this, hud);
        help = new Help(this);
        credits = new Credits(this);
        achievements = new Achievements(this);
        keyInput = new KeyInput(handler, this);
        settings = new Settings(keyInput, this);
        camera = new Camera(0, 0, this, settings);
        mouseInput = new MouseInput(this, handler, keyInput, settings, menu, paused, help, end, credits, achievements, hud);

        this.addKeyListener(keyInput);
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);

        level = 0;
        inGame = false;

        new Window(WIDTH, HEIGHT, "The Box Game", this);
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1)
            {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick()
    {
        try
        {
            for (GameObject o : handler.object) if (o instanceof Player) camera.tick(o);
        }
        catch (Exception ignored) { }

        handler.tick();

        switch (gameState)
        {
            case Menu -> menu.tick();
            case Game -> hud.tick();
            case End -> end.tick();
            case Settings -> settings.tick();
            case Paused -> paused.tick();
            case Help -> help.tick();
            case Credits -> credits.tick();
            case Achievements -> achievements.tick();
        }
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("arial", Font.PLAIN, 15);
        FontMetrics metrics = g.getFontMetrics(font);

        g2d.translate(-camera.x, -camera.y);

        for (int xx = 0; xx < 30 * 100; xx += 32)
        {
            for (int yy = 0; yy < 30 * 100; yy += 32)
            {
                g.drawImage(floor, xx, yy, 32, 32, null);
            }
        }

        handler.render(g);

        g2d.translate(camera.x, camera.y);

        switch (gameState)
        {
            case Menu -> menu.render(g);
            case Game -> hud.render(g);
            case End -> end.render(g);
            case Settings -> settings.render(g);
            case Paused -> paused.render(g);
            case Help -> help.render(g);
            case Credits -> credits.render(g);
            case Achievements -> achievements.render(g);
        }

        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(VERSION, Game.WIDTH - 20 - metrics.stringWidth(VERSION), Game.HEIGHT - 45);

        g.dispose();
        bs.show();
    }

    public void loadLevel()
    {
        for (int i = handler.object.size(); handler.object.size() > 0; i--)
        {
            GameObject tempObject = handler.object.get(i - 1);

            handler.removeObject(tempObject);
        }

        BufferedImage image;
        boolean load = true;

        if (level > 0 && level < 12) image = levels[level - 1]; // TODO: Start level count at 0
        else
        {
            image = null;
            load = false;
        }

        if (load)
        {
            final int w = image.getWidth();
            final int h = image.getHeight();

            for (int x = 0; x < w; x++)
            {
                for (int y = 0; y < h; y++)
                {
                    Color pixel = new Color(image.getRGB(x, y));

                    if (pixel.equals(Color.RED)) handler.addObject(new Block(x * 64, y * 64, this));
                    if (pixel.equals(Color.BLUE)) handler.addObject(new Player(x * 64, y * 64, handler, this, settings));
                    if (pixel.equals(Color.GREEN)) handler.addObject(new Finish(x * 64, y * 64, this));
                    if (pixel.equals(Color.YELLOW)) handler.addObject(new Teleporter(x * 64, y * 64, this));
                    if (pixel.equals(Color.MAGENTA)) handler.addObject(new Backer(x * 64, y * 64, this));
                }
            }
        }
        else
        {
            gameState = State.End;
            inGame = false;
        }
    }

    public String ensureLength(String input, int length)
    {
        if (input.length() > length) input = input.substring(input.length() - length);
        else if (input.length() < length)
        {
            StringBuilder inputBuilder = new StringBuilder(input);
            for (int i = length - inputBuilder.length(); i > 0; i--) inputBuilder.insert(0, "0");
            input = inputBuilder.toString();
        }
        return input;
    }

    public static void main(String[] args)
    {
        new Game();
    }
}
