package com.boxgame.main;

/*
 * Author: son801
 */

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1550691097823471818L;

    public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
    public static String VERSION = "beta0.1.8";
    public int level;
    public boolean inGame;

    private Handler handler;
    private Thread thread;
    private boolean running = false;

    private Menu menu;
    private Camera camera;
    private End end;
    private HUD hud;
    private Settings settings;
    private KeyInput keyInput;
    private Paused paused;
    private Help help;

    BufferedImage logo;
    BufferedImage block;
    BufferedImage floor;
    BufferedImage finish;
    BufferedImage teleporter;
    BufferedImage sprite_sheet;
    BufferedImage guard;

    private BufferedImage level1;
    private BufferedImage level2;
    private BufferedImage level3;
    private BufferedImage level4;
    private BufferedImage level5;
    private BufferedImage level6;
    private BufferedImage level7;
    private BufferedImage level8;
    private BufferedImage level9;
    private BufferedImage level10;

    public STATE gameState = STATE.Menu;

    public Game()
    {
        BufferedImageLoader loader = new BufferedImageLoader();
        logo = loader.loadImage("/logo.png");
        block = loader.loadImage("/block.png");
        floor = loader.loadImage("/floor.png");
        finish = loader.loadImage("/finish.png");
        teleporter = loader.loadImage("/teleporter.png");
        sprite_sheet = loader.loadImage("/sprite_sheet.png");
        guard = loader.loadImage("/guard.png");
        level1 = loader.loadImage("/level1.png");
        level2 = loader.loadImage("/level2.png");
        level3 = loader.loadImage("/level3.png");
        level4 = loader.loadImage("/level4.png");
        level5 = loader.loadImage("/level5.png");
        level6 = loader.loadImage("/level6.png");
        level7 = loader.loadImage("/level7.png");
        level8 = loader.loadImage("/level8.png");
        level9 = loader.loadImage("/level9.png");
        level10 = loader.loadImage("/level10.png");

        handler = new Handler();
        menu = new Menu(this);
        camera = new Camera(0, 0, this);
        end = new End();
        hud = new HUD(this);
        keyInput = new KeyInput(handler, this);
        settings = new Settings(keyInput, this);
        paused = new Paused();
        help = new Help();

        this.addKeyListener(keyInput);
        this.addMouseListener(new MouseInput(this, handler, keyInput, settings));

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
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick()
    {
        for (int i = 0; i < handler.object.size(); i++)
        {
            if (handler.object.get(i).getId() == ID.Player)
            {
                camera.tick(handler.object.get(i));
            }
        }

        handler.tick();

        switch (gameState)
        {
            case Menu     : menu.tick();     break;
            case Game     : hud.tick();      break;
            case End      : end.tick();      break;
            case Settings : settings.tick(); break;
            case Paused   : paused.tick();   break;
            case Help     : help.tick();     break;
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

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

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
            case Menu     : menu.render(g);     break;
            case Game     : hud.render(g);      break;
            case End      : end.render(g);      break;
            case Settings : settings.render(g); break;
            case Paused   : paused.render(g);   break;
            case Help     : help.render(g);     break;
        }

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

        switch (level)
        {
            case 1  : image = level1;  break;
            case 2  : image = level2;  break;
            case 3  : image = level3;  break;
            case 4  : image = level4;  break;
            case 5  : image = level5;  break;
            case 6  : image = level6;  break;
            case 7  : image = level7;  break;
            case 8  : image = level8;  break;
            case 9  : image = level9;  break;
            case 10 : image = level10; break;
            default : image = null; load = false;
        }

        if (load)
        {
            int w = image.getWidth();
            int h = image.getHeight();

            for (int xx = 0; xx < w; xx++)
            {
                for (int yy = 0; yy < h; yy++)
                {
                    int pixel = image.getRGB(xx, yy);
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = (pixel) & 0xff;

                    if (red == 255 && blue == 0 && green == 0)
                        handler.addObject(new Block(xx * 64, yy * 64, ID.Block, this));

                    if (blue == 255 && red == 0 && green == 0)
                        handler.addObject(new Player(xx * 64, yy * 64, ID.Player, handler, this, settings));

                    if (green == 255 && red == 0 && blue == 0)
                        handler.addObject(new Finish(xx * 64, yy * 64, ID.Finish, this));

                    if (red == 255 && green == 255 && blue == 0)
                        handler.addObject(new Teleporter(xx * 64, yy * 64, ID.Teleporter, this));

                    if (red == 255 && green == 0 && blue == 255)
                        handler.addObject(new Guard(xx * 64, yy * 64, ID.Guard, this));
                }
            }
        }
        else
        {
            gameState = STATE.End;
            inGame = false;
        }
    }

    public static void main(String[] args)
    {
        new Game();
    }
}