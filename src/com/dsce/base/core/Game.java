package com.dsce.base.core;

import com.dsce.base.core.graphics.Button;
import com.dsce.base.core.graphics.Shutter;
import com.dsce.base.sys.mouse.Click;
import com.dsce.base.sys.mouse.IClickEvent;
import com.dsce.base.utils.RenderU;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Game implements IClickEvent {

    Map<String, Button> buttonMap = new LinkedHashMap<>();

    String buttonsKeys[] = {"newProject","projectManagement","docs","staff","breakroom","community"};
    String buttonLabels[] = {"New Project","Management","Docs","Staff","Break room","Community"};

    GameState.state state = GameState.state.night;

    final Window window = new Window();
    final Shutter shutter = new Shutter(this);

    public Game() {
        Click.g().registerClickEventObject(this::clickEvent);
        buttonMap.put("commit",new Button(1920-310,1080-90,300,80));
        for (int i = 0; i < buttonsKeys.length; i++) {
            buttonMap.put(buttonsKeys[i],new Button(10+(i*260),1010,250,60));
        }
    }

    public void update(double deltaTime) {
        shutter.update();
    }

    @Override
    public void clickEvent() {
        for (int i = 0; i < buttonsKeys.length; i++) {
            if (buttonMap.get(buttonsKeys[i]).isOnMouse()) {
                window.windowTabIndex = i;
            }
        }
        if (state == GameState.state.day) {
            if (buttonMap.get("commit").isOnMouse()) {
                shutter.changScreen(GameState.state.night);
            }
        } else if (state == GameState.state.night) {
            if (buttonMap.get("commit").isOnMouse()) {
                shutter.changScreen(GameState.state.day);
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color darkBlue = new Color(5,80,125);
        //backGround
        g.setColor(Color.black);
        g.fillRect(-5000,-5000,100000,1000000);
        if (state == GameState.state.night) {
            //interface
            g.setColor(darkBlue);
            g.fillRect(0,0,1920,100);
            g.fillRect(0,980,1920,100);
            //commit button
            g2.setStroke(new BasicStroke(2f));
            Button commitButton = buttonMap.get("commit");
            g.setColor(commitButton.isOnMouse()? Color.green : Color.gray);
            g.fillRect(commitButton.getX(),commitButton.getY(),commitButton.getW(),commitButton.getH());
            g.setColor(Color.white);
            g.drawRect(commitButton.getX(),commitButton.getY(),commitButton.getW()-2,commitButton.getH()-2);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
            g.setColor(Color.black);
            RenderU.drawStringCenter(g,"Next Day",commitButton.getX()+(commitButton.getW()/2),commitButton.getY()+32);

        } else if (state == GameState.state.day) {
            //interface
            g.setColor(darkBlue);
            g.fillRect(0,0,1920,100);
            g.fillRect(0,980,1920,100);
            //commit button
            g2.setStroke(new BasicStroke(2f));
            Button commitButton = buttonMap.get("commit");
            g.setColor(commitButton.isOnMouse()? Color.green : Color.gray);
            g.fillRect(commitButton.getX(),commitButton.getY(),commitButton.getW(),commitButton.getH());
            g.setColor(Color.white);
            g.drawRect(commitButton.getX(),commitButton.getY(),commitButton.getW()-2,commitButton.getH()-2);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 64));
            g.setColor(Color.black);
            RenderU.drawStringCenter(g,"Commit",commitButton.getX()+(commitButton.getW()/2),commitButton.getY()+32);
            //buttons
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
            for (int i = 0; i < buttonsKeys.length; i++) {
                Button currentButton = buttonMap.get(buttonsKeys[i]);
                g.setColor(currentButton.isOnMouse()? Color.green : Color.gray);
                g.fillRect(currentButton.getX(),currentButton.getY(),currentButton.getW(),currentButton.getH());
                g.setColor(Color.white);
                g.drawRect(currentButton.getX(),currentButton.getY(),currentButton.getW()-2,currentButton.getH()-2);
                g.setColor(Color.black);
                RenderU.drawStringCenter(g,buttonLabels[i],currentButton.getX()+(currentButton.getW()/2),currentButton.getY()+26);
            }
            //top bar
            g.setColor(Color.white);
            g.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 24));
            g.drawString(buttonLabels[window.windowTabIndex]+ " Tab", 10,90);


            //end
            g.setColor(Color.white);
            g2.setStroke(new BasicStroke(2f));
            g.drawRect(0,0,1918,1078);
        }
        shutter.render(g);
    }
    public void setState(GameState.state state) {
        this.state = state;
    }
}