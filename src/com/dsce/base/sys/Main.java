package com.dsce.base.sys;

import com.dsce.base.core.Game;
import com.dsce.base.sys.input.InputHandler;
import com.dsce.base.sys.view.IFrameSize;
import com.dsce.base.sys.view.IPause;
import com.dsce.base.sys.input.MouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Main extends JPanel implements IFrameSize, IPause {
    JFrame frame = new JFrame("dsce Pre-Alpha 0.0.0");

    private long lastTime;

    private boolean isResizing = false;

    private boolean pause = false;

    private Console console = new Console();

    private Game game = new Game();

    public Console getConsole() {
        return console;
    }

    private final ViewMetrics viewMetrics;

    public Main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        frame.setPreferredSize((new Dimension(1280,720)));
        setFocusable(true);

        viewMetrics = new ViewMetrics(this);
        MouseListener mouseListener = new MouseListener(viewMetrics);

        frame.add(this);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.pack();
        InputHandler inputHandler = new InputHandler(viewMetrics,this);

        setBackground(Color.BLACK);

        viewMetrics.calculateViewMetrics();

        this.addMouseListener(mouseListener);
        this.addMouseWheelListener(mouseListener);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                viewMetrics.updateVirtualMouse(e.getX(),e.getY());
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                viewMetrics.calculateViewMetrics();
            }
        });



        startGameLoop();

        frame.addKeyListener(inputHandler);
    }

    private void startGameLoop() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        lastTime = System.nanoTime();

        executor.scheduleAtFixedRate(() -> {
            try {
                long now = System.nanoTime();
                double deltaTime = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                update(deltaTime);
            } catch (Throwable t) {
                t.printStackTrace();
            }
            SwingUtilities.invokeLater(this::repaint);

        }, 0, 16, TimeUnit.MILLISECONDS);
    }

    private void update(double deltaTime) {
        game.update(deltaTime);
    }

    @Override public boolean isPause() {return pause;}
    @Override public void setPause(boolean b) {pause = b;}

    @Override public int getComponentWidth() { return this.getWidth(); }
    @Override public int getComponentHeight() { return this.getHeight(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D d2 = (Graphics2D) g;

        d2.translate(viewMetrics.getCurrentXOffset(), viewMetrics.getCurrentYOffset());
        d2.scale(viewMetrics.getCurrentScale(), viewMetrics.getCurrentScale());

        game.render(g);

        console.render(g);

    }

    public static void main(String[] args) {
        new Main();
    }
}