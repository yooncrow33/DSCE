package com.dsce.base.core;

import com.dsce.base.core.contents.project.Project;
import com.dsce.base.core.contents.staff.Staff;
import com.dsce.base.core.contents.team.Team;
import com.dsce.base.core.graphics.Button;
import com.dsce.base.core.graphics.Shutter;
import com.dsce.base.core.graphics.overlay.StaffListOverly;
import com.dsce.base.core.graphics.overlay.internal.OverlayManager;
import com.dsce.base.core.popup.CommitPopup;
import com.dsce.base.core.popup.internal.PopupManager;
import com.dsce.base.core.window.Window;
import com.dsce.base.sys.Console;
import com.dsce.base.sys.file.FileManager;
import com.dsce.base.sys.input.InputHandler;
import com.dsce.base.sys.input.MouseListener;
import com.dsce.base.sys.mouse.Click;
import com.dsce.base.sys.mouse.IClickEvent;
import com.dsce.base.utils.RenderU;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Game implements IClickEvent {
    public static Map<String, Button> buttonMap = new LinkedHashMap<>();

    String barButtonsKeys[] = {"newProject","projectManagement","team","staff","breakroom","community"};
    String barButtonLabels[] = {"New Project","Management","Team","Staff","Break room","Community"};

    GameState.state state = GameState.state.night;

    public static final com.dsce.base.core.window.Window window = new Window();
    public final Shutter shutter = new Shutter(this);

    public GameInput gameInput = new GameInput();
    public GameConsole gameConsole = new GameConsole(this);

    public static ArrayList<Project> projects = new ArrayList<>();
    public static ArrayList<Staff> staffs = new ArrayList<>();
    public static ArrayList<Team> teams = new ArrayList<>();

    public static int ap = 99990;
    public static int money = 99990;

    public Game() {
        Click.g().registerClickEventObject(this::clickEvent);
        buttonMap.put("commit",new Button(1920-310,1080-90,300,80));
        buttonMap.put("projectCreateNext",new Button(1920-260,1080-70-100,250,60));
        buttonMap.put("projectCreateBack",new Button(1920-260-260,1080-70-100,250,60));
        for (int i = 0; i < barButtonsKeys.length; i++) {
            buttonMap.put(barButtonsKeys[i],new Button(10+(i*260),1010,250,60));
        }

        new CommitPopup(this,"Go to Next Day.","nightnp", GameState.state.day);
        new CommitPopup(this,"Commit?", "daynp", GameState.state.night);

        //end constructor
        FileManager.load();

        new StaffListOverly("staff");
    }

    public void update(double deltaTime) {
        shutter.update();
    }

    public void registerStaffForTeam(Staff staff) {
        for (Team t:teams) {
            if (t.getName().equals(staff.getTeam())) {
                t.staffs.add(staff);
            }
        }
    }

    public void removeStaffForTeam(Staff staff) {
        for (Team t:teams) {
            for (int i = 0; i<t.staffs.size(); i++) {
                if (t.staffs.get(i)==staff) {
                    t.staffs.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public void clickEvent() {
        InputHandler.registerInputExecutor(gameInput);
        MouseListener.registerInputExecutor(gameInput);
        Console.registerConsoleExecutor(gameConsole);
        for (int i = 0; i < barButtonsKeys.length; i++) {
            if (buttonMap.get(barButtonsKeys[i]).isOnMouse()) {
                window.windowTabIndex = i;
                window.init();
            }
        }
        if (state == GameState.state.day) {
            OverlayManager.clickEvent();
            if (buttonMap.get("commit").isOnMouse()) {
                PopupManager.enablePopup("daynp");
            }
            if (buttonMap.get("team").isOnMouse()) {
                OverlayManager.enableListOverlay("staff");
            }
            if (window.windowTabIndex == 0) {
                window.projectCreateTab.clickEvent();
            }
            if (window.windowTabIndex == 1) {
                window.managementTab.clickEvent();
            }
            if (window.windowTabIndex == 3) {
                window.staffsTab.clickEvent();
            }
        } else if (state == GameState.state.night) {
            if (buttonMap.get("commit").isOnMouse()) {
                PopupManager.enablePopup("nightnp");
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

            //window
            window.render(g);

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
            for (int i = 0; i < barButtonsKeys.length; i++) {
                Button currentButton = buttonMap.get(barButtonsKeys[i]);
                g.setColor(currentButton.isOnMouse()? Color.green : Color.gray);
                g.fillRect(currentButton.getX(),currentButton.getY(),currentButton.getW(),currentButton.getH());
                g.setColor(Color.white);
                g.drawRect(currentButton.getX(),currentButton.getY(),currentButton.getW()-2,currentButton.getH()-2);
                g.setColor(Color.black);
                RenderU.drawStringCenter(g, barButtonLabels[i],currentButton.getX()+(currentButton.getW()/2),currentButton.getY()+26);
            }
            //top bar
            g.setColor(Color.white);
            g.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 24));
            g.drawString(barButtonLabels[window.windowTabIndex]+ " Tab", 10,90);

            //popup (temp)


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