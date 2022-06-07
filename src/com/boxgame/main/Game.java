/*
 * Copyright (c) 2022. Samuel "Soni" Aasen
 * Some rights reserved.
 */

package com.boxgame.main;

import com.boxgame.input.KeyInput;
import com.boxgame.input.MouseInput;
import com.boxgame.main.types.ID;
import com.boxgame.main.types.State;
import com.boxgame.main.util.BufferedImageLoader;
import com.boxgame.main.util.Window;
import com.boxgame.object.*;
import com.boxgame.state.Menu;
import com.boxgame.state.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

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

    private final BufferedImage level1;
    private final BufferedImage level2;
    private final BufferedImage level3;
    private final BufferedImage level4;
    private final BufferedImage level5;
    private final BufferedImage level6;
    private final BufferedImage level7;
    private final BufferedImage level8;
    private final BufferedImage level9;
    private final BufferedImage level10;
    private final BufferedImage level11;

    public File achievementsFile;

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
        // TODO: Fix the achievement code
        //new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\soni801 games").mkdirs();
        //new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\soni801 games\\boxgame").mkdirs();
        //new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\soni801 games\\boxgame\\achievements.ini");
        //achievementsFile = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\soni801 games\\boxgame\\achievements.ini");

        BufferedImageLoader loader = new BufferedImageLoader();

        level1 = loader.loadImage("/levels/level1.png");
        level2 = loader.loadImage("/levels/level2.png");
        level3 = loader.loadImage("/levels/level3.png");
        level4 = loader.loadImage("/levels/level4.png");
        level5 = loader.loadImage("/levels/level5.png");
        level6 = loader.loadImage("/levels/level6.png");
        level7 = loader.loadImage("/levels/level7.png");
        level8 = loader.loadImage("/levels/level8.png");
        level9 = loader.loadImage("/levels/level9.png");
        level10 = loader.loadImage("/levels/level10.png");
        level11 = loader.loadImage("/levels/level11.png");

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

        Achievements.load();

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
                // System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick()
    {
        try { for (GameObject o : handler.object) if (o instanceof Player) camera.tick(o); }
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

        g2d.translate(-camera.getX(), -camera.getY());

        for (int xx = 0; xx < 30 * 100; xx += 32)
        {
            for (int yy = 0; yy < 30 * 100; yy += 32)
            {
                g.drawImage(floor, xx, yy, 32, 32, null);
            }
        }

        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());

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

    public static String getProperty(File file, String key, String defaultAnswer)
    {
        boolean done = false;
        String output = "";
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (!done)
            {
                output = reader.readLine();
                if (output.startsWith(key))
                {
                    done = true;
                }
            }
            return output.substring(key.length() + 1);
        }
        catch (Exception e)
        {
            System.out.println("Could not find property.");
            System.out.println("Returning default answer. (" + defaultAnswer + ")");
            return defaultAnswer;
        }
    }

    public static void setProperty(File file, String key, String newProperty)
    {
        boolean done = false;
        String input = "";
        int line = 0;
        List<String> lines;
        if (file.exists())
        {
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while (!done)
                {
                    input = reader.readLine();
                    if (input.startsWith(key))
                    {
                        done = true;
                    }
                    line++;
                }
                lines = Files.readAllLines(file.toPath());
                lines.set(line - 1, input.substring(0, key.length() + 1) + newProperty);
                Files.write(file.toPath(), lines);
            }
            catch (Exception e)
            {
                try
                {
                    lines = Files.readAllLines(file.toPath());
                    lines.set(line, key + "=" + newProperty + "\n");
                    Files.write(file.toPath(), lines);
                }
                catch (IOException e1)
                {
                    System.out.println("Could not set property.");
                }
            }
        }
        else
        {
            try
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("\n");
                writer.close();

                lines = Files.readAllLines(file.toPath());
                lines.set(0, key + "=" + newProperty + "\n");
                Files.write(file.toPath(), lines);
            }
            catch (Exception e)
            {
                System.out.println("Could not set property.");
            }

        }
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

        switch (level)
        {
            case 1 -> image = level1;
            case 2 -> image = level2;
            case 3 -> image = level3;
            case 4 -> image = level4;
            case 5 -> image = level5;
            case 6 -> image = level6;
            case 7 -> image = level7;
            case 8 -> image = level8;
            case 9 -> image = level9;
            case 10 -> image = level10;
            case 11 -> image = level11;
            default ->
            {
                image = null;
                load = false;
            }
        }

        if (load)
        {
            int w = image.getWidth();
            int h = image.getHeight();

            for (int x = 0; x < w; x++)
            {
                for (int y = 0; y < h; y++)
                {
                    int pixel = image.getRGB(x, y);

                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >> 8) & 0xff;
                    int b = (pixel) & 0xff;

                    if (r == 255 && b == 0 && g == 0) handler.addObject(new Block(x * 64, y * 64, ID.Block, this));
                    if (b == 255 && r == 0 && g == 0) handler.addObject(new Player(x * 64, y * 64, ID.Player, handler, this, settings));
                    if (g == 255 && r == 0 && b == 0) handler.addObject(new Finish(x * 64, y * 64, ID.Finish, this));
                    if (r == 255 && g == 255 && b == 0) handler.addObject(new Teleporter(x * 64, y * 64, ID.Teleporter, this));
                    if (r == 255 && g == 0 && b == 255) handler.addObject(new Backer(x * 64, y * 64, ID.Backer, this));
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
            // This uses StringBuilder because that is apparently better than +=
            StringBuilder inputBuilder = new StringBuilder(input);
            for (int i = length - inputBuilder.length(); i > 0; i--) inputBuilder.insert(0, "0");
            input = inputBuilder.toString();
        }
        return input;
    }

    public static void main(String[] args)
    {
        new Game();
        Runtime.getRuntime().addShutdownHook(new Thread(Achievements::save, "Shutdown-thread"));
    }
}
