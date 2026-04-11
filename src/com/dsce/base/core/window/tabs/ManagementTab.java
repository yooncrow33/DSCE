package com.dsce.base.core.window.tabs;

import com.dsce.base.core.Game;
import com.dsce.base.core.contents.project.Project;
import com.dsce.base.sys.mouse.Mouse;
import com.dsce.base.utils.RenderU;

import java.awt.*;

public class ManagementTab extends Tab {

    private static int listScrollY = 0;
    private static int maxListScrollY = 0;

    private static int panelScrollY = 0;
    private static final int maxPanelScrollY = 800;

    //public static Project selectedProject = Game.projects.get(0);
    public static int managementSelectedProjectIndex = 0;

    public void clickEvent() {
        for (int i = 0; i < Game.projects.size(); i++) {
            if (100+ listScrollY +(i*50)<= Mouse.g().y()&&100+50+ listScrollY +(i*50)>=Mouse.g().y()) {
                //managementSelectedProject =  Game.projects.get(i);
                managementSelectedProjectIndex = i;
            }
        }
    }

    public void renderRight(Graphics g, int x) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(new Color(25, 115, 175));
        g.fillRect(x,100+panelScrollY,1220,880+800);
        g2.setStroke(new BasicStroke(2f));
        g.setColor(Color.white);
        g.drawRect(x,100+panelScrollY,1220,880+800);

        Project selectedProject = Game.projects.get(managementSelectedProjectIndex);

        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,92));
        RenderU.drawStringCenter(g,selectedProject.getName(),x+(1220/2),180+panelScrollY);

        g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,48));
        RenderU.drawStringCenter(g,selectedProject.getProjectType().toString()+", "+selectedProject.getProjectEngineType()+", "+
                selectedProject.getProjectLangType().toString()+", "+selectedProject.getProjectGraphicsType(),x+(1220/2),290+panelScrollY);




    }

    @Override
    public void render(Graphics g) {
        if (Game.projects.isEmpty()) {
            g.setColor(new Color(25, 115, 175));
            g.fillRect(0,100,1920,880);
            g.setColor(Color.white);
            g.setFont(new Font(Font.MONOSPACED,Font.BOLD,140));
            RenderU.drawStringCenter(g,"No Project!",(1920/2),350);
            return;
        }
        for (int i = 0; i < Game.projects.size(); i++) {
            if (i==managementSelectedProjectIndex) {
                g.setColor(Color.white);
                g.fillRect(0, 100 + listScrollY + (i * 50), 1920, 50);
                g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
                g.setColor(Color.blue);
                g.drawString(Game.projects.get(i).getProjectType().toString(), 10, 100 + listScrollY + (i * 50) + 35);
                g.setColor(Color.black);
                g.drawString("| " + Game.projects.get(i).getName(), 200, 100 + listScrollY + (i * 50) + 35);
            } else {
                if ((i & 1) == 0) {
                    g.setColor(new Color(5, 100, 135));
                } else {
                    g.setColor(new Color(25, 130, 185));
                }
                g.fillRect(0, 100 + listScrollY + (i * 50), 1920, 50);
                g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
            /*if (Game.projects.get(i).getProjectEngineType().toString() == null) {
                System.err.println("project name is null");
                System.out.println(Game.projects.size());
                System.exit(0);
            }

             */
                g.setColor(Color.green);
                g.drawString(Game.projects.get(i).getProjectType().toString(), 10, 100 + listScrollY + (i * 50) + 35);
                g.setColor(Color.white);
                g.drawString("| " + Game.projects.get(i).getName(), 200, 100 + listScrollY + (i * 50) + 35);
            }
        }
        renderRight(g,700);
        //update
        if (Game.projects.size() >= 17) {
            int i = Game.projects.size() - 17;
            maxListScrollY = i*50;
        }
    }

    public static void scrollUp() {
        if (Game.window.windowTabIndex != 1) return;
        if (Mouse.g().x()<=700) {
            listScrollY += 25;
            if (listScrollY > 0) listScrollY = 0;
        } else {
            panelScrollY += 25;
            if (panelScrollY > 0) panelScrollY = 0;
        }
    }

    public static void scrollUDown() {
        if (Game.window.windowTabIndex != 1) return;

        if (Mouse.g().x()<=700) {
            listScrollY -= 25;
            if (listScrollY < -maxListScrollY) listScrollY = -maxListScrollY;
        } else {
            panelScrollY -= 25;
            if (panelScrollY < -maxPanelScrollY) panelScrollY = -maxPanelScrollY;
        }
    }
}
