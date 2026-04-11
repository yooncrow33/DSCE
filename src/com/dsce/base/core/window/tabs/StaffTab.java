package com.dsce.base.core.window.tabs;

import com.dsce.base.core.Game;
import com.dsce.base.core.contents.project.Project;
import com.dsce.base.core.contents.staff.Staff;
import com.dsce.base.core.contents.staff.inetnal.RandomStaff;
import com.dsce.base.core.popup.internal.Popup;
import com.dsce.base.core.graphics.Button;
import com.dsce.base.sys.mouse.Mouse;
import com.dsce.base.utils.RenderU;
import com.dsce.base.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

public class StaffTab extends Tab {
    public enum state {
        company,
        laborMarket;
    }

    state tabState = state.company;

    private static ArrayList<RandomStaff> tempStaffs = new ArrayList<>();

    private static int listScrollY = 0;
    private static int maxListScrollY = 0;

    private static int panelScrollY = 0;
    private static final int maxPanelScrollY = 800;

    public static int selectedStaffIndex = 0;
    public static int selectedTempStaffIndex = 0;

    public static final int scanStaffMoneyCost[] = {10,30,60,100};
    public static final int scanStaffApCost[] = {3,5,8,12};

    private com.dsce.base.core.graphics.Button cb = new com.dsce.base.core.graphics.Button(0,920,1920/2,60);
    private com.dsce.base.core.graphics.Button lb = new com.dsce.base.core.graphics.Button(1920/2,920,1920/2,60);

    String s[] = {"Press S for Scan!", "Press D for Deal!", "Press F for Refresh"};

    Button t1 = new Button(20+(0*630), 120,620,770);
    Button t2 = new Button(20+(1*630), 120,620,770);
    Button t3 = new Button(20+(2*630), 120,620,770);

    public StaffTab() {
        tempStaffs.clear();
        for (int i = 0; i<3; i++) {
            tempStaffs.add(new RandomStaff());
        }
    }

    public void clickEvent() {
        for (int i = 0; i < Game.staffs.size(); i++) {
            if (100+ listScrollY +(i*50)<= Mouse.g().y()&&100+50+ listScrollY +(i*50)>=Mouse.g().y()) {
                //managementSelectedProject =  Game.staffs.get(i);
                selectedStaffIndex = i;
            }
        }
        if (cb.isOnMouse()) {
            tabState = state.company;
        }
        if (lb.isOnMouse()) {
            tabState = state.laborMarket;
        }
        if (t1.isOnMouse()) {
            selectedTempStaffIndex = 0;
        }
        if (t2.isOnMouse()) {
            selectedTempStaffIndex = 1;
        }
        if (t3.isOnMouse()) {
            selectedTempStaffIndex = 2;
        }

    }

    public void renderRight(Graphics g, int x) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(new Color(25, 115, 175));
        g.fillRect(x,100+panelScrollY,1220,880+800);
        g2.setStroke(new BasicStroke(2f));
        g.setColor(Color.white);
        g.drawRect(x,100+panelScrollY,1220,880+800);

        Staff selectedProject = Game.staffs.get(selectedStaffIndex);

        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,92));
        RenderU.drawStringCenter(g,selectedProject.getName(),x+(1220/2),180+panelScrollY);

        g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,48));
        //RenderU.drawStringCenter(g,selectedProject.getProjectType().toString()+", "+selectedProject.getProjectEngineType()+", "+
          //      selectedProject.getProjectLangType().toString()+", "+selectedProject.getProjectGraphicsType(),x+(1220/2),290+panelScrollY);

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(new Color(25, 115, 175));
        g.fillRect(0, 100, 1920, 880);

        if (tabState == state.company) {
            if (Game.staffs.isEmpty()) {
                g.setColor(Color.white);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 140));
                RenderU.drawStringCenter(g, "No Staff!", (1920 / 2), 350);
            } else {
                for (int i = 0; i < Game.staffs.size(); i++) {
                    if (i == selectedStaffIndex) {
                        g.setColor(Color.white);
                        g.fillRect(0, 100 + listScrollY + (i * 50), 1920, 50);
                        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
                        g.setColor(Color.blue);
                        //g.drawString(String.valueOf(Game.staffs.get(i).getTeam()), 10, 100 + listScrollY + (i * 50) + 35);
                        g.setColor(Color.black);
                        g.drawString("| " + Game.staffs.get(i).getName(), 200, 100 + listScrollY + (i * 50) + 35);
                    } else {
                        if ((i & 1) == 0) {
                            g.setColor(new Color(5, 100, 135));
                        } else {
                            g.setColor(new Color(25, 130, 185));
                        }
                        g.fillRect(0, 100 + listScrollY + (i * 50), 1920, 50);
                        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
                /*if (Game.staffs.get(i).getProjectEngineType().toString() == null) {
                    System.err.println("project name is null");
                    System.out.println(Game.staffs.size());
                    System.exit(0);
                }

                 */
                        g.setColor(Color.green);
                        g.drawString(String.valueOf(Game.staffs.get(i).getXp()), 10, 100 + listScrollY + (i * 50) + 35);
                        g.setColor(Color.white);
                        g.drawString("| " + Game.staffs.get(i).getName(), 200, 100 + listScrollY + (i * 50) + 35);
                    }
                }
                renderRight(g, 700);
            }
        } else if (tabState == state.laborMarket) {
            //labor market
            for (int i = 0; i<tempStaffs.size(); i++) {
                g.setColor(i == selectedTempStaffIndex? Color.white : new Color(2, 140, 175));
                g.fillRect(20+(i*630), 120,620,770);
                g2.setStroke(new BasicStroke(6f));
                g.setColor(i == selectedTempStaffIndex? Color.black : Color.white);
                g.drawRect(20+(i*630), 120,620,770);
                g.setColor(i == selectedTempStaffIndex? Color.blue : Color.white);
                int c = 20+(i*630)+300;
                RandomStaff s = tempStaffs.get(i);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
                RenderU.drawStringCenter(g,this.s[i],c,150);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 92));
                RenderU.drawStringCenter(g,s.getName(),c,210);
                g.setFont(new Font(Font.MONOSPACED,Font.PLAIN,54));
                RenderU.drawStringCenter(g,"Stacks :",c,300);

                if (s.scan == 0) {
                    RenderU.drawStringCenter(g,"?",c,430-70);
                    RenderU.drawStringCenter(g,"?",c,490-70);
                    RenderU.drawStringCenter(g,"?",c,550-70);

                    g.setFont(new Font(Font.MONOSPACED,Font.BOLD,40));
                    RenderU.drawStringCenter(g,"Asking Price :" + s.getLaborCost(),c,530); // hope labor cost
                    RenderU.drawStringCenter(g,"?",c,580); // temp slot
                    RenderU.drawStringCenter(g,"?",c,630); // Trait y/n
                    RenderU.drawStringCenter(g,"?",c,680); // Trait
                } else if (s.scan == 1) {
                    RenderU.drawStringCenter(g,s.getEngineStack().toString(),c,430-70);
                    RenderU.drawStringCenter(g,s.getLangStack().toString(),c,490-70);
                    RenderU.drawStringCenter(g,s.getGraphicsStack().toString(),c,550-70);

                    g.setFont(new Font(Font.MONOSPACED,Font.BOLD,40));
                    RenderU.drawStringCenter(g,"Asking Price :" + s.getLaborCost(),c,530); // hope labor cost
                    RenderU.drawStringCenter(g,"?",c,580); // temp slot
                    RenderU.drawStringCenter(g,"?",c,630); // Trait y/n
                    RenderU.drawStringCenter(g,"?",c,680); // Trait
                } else if (s.scan == 2) {
                    RenderU.drawStringCenter(g,s.getEngineStack().toString(),c,430-70);
                    RenderU.drawStringCenter(g,s.getLangStack().toString(),c,490-70);
                    RenderU.drawStringCenter(g,s.getGraphicsStack().toString(),c,550-70);

                    g.setFont(new Font(Font.MONOSPACED,Font.BOLD,40));
                    RenderU.drawStringCenter(g,"Asking Price :" + s.getLaborCost(),c,530); // hope labor cost
                    RenderU.drawStringCenter(g,"?",c,580); // temp slot
                    RenderU.drawStringCenter(g,"Have a Trait : "+ s.isGetTrait,c,630); // Trait y/n
                    RenderU.drawStringCenter(g,"?",c,680); // Trait
                } else if (s.scan == 3) {
                    RenderU.drawStringCenter(g,s.getEngineStack().toString(),c,430-70);
                    RenderU.drawStringCenter(g,s.getLangStack().toString(),c,490-70);
                    RenderU.drawStringCenter(g,s.getGraphicsStack().toString(),c,550-70);

                    g.setFont(new Font(Font.MONOSPACED,Font.BOLD,40));
                    RenderU.drawStringCenter(g,"Asking Price :" + s.getLaborCost(),c,530); // hope labor cost
                    RenderU.drawStringCenter(g,"?",c,580); // temp slot
                    RenderU.drawStringCenter(g,"Have a Trait : "+ s.isGetTrait,c,630); // Trait y/n
                    for (int i2 = 0; i2<s.getTraits().size(); i2++) {
                        RenderU.drawStringCenter(g,"Trait : "+s.getTraits().get(i2).getName(),c,680+(i2*50)); // Trait
                    }
                } else {
                    System.err.println("Labor Market Staff Scan Index Error! " + Utils.getReportMessage());
                }
            }
        }

        if (tabState == state.company) {
            g.setColor(new Color(5, 100, 135));
        } else {
            g.setColor(new Color(25, 130, 185));
        }
        g.fillRect(0,920,1920/2,60);
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(2f));
        g.drawRect(0,920,1920/2,60);
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));
        RenderU.drawStringCenter(g,"Company", 1920/2/2,940);

        if (tabState == state.laborMarket) {
            g.setColor(new Color(5, 100, 135));
        } else {
            g.setColor(new Color(25, 130, 185));
        }
        g.fillRect(1920/2,920,1920/2,60);
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(2f));
        g.drawRect(1920/2,920,1920/2,60);
        RenderU.drawStringCenter(g,"Labor Market", 1920/2+(1920/2)/2,940);

        //update
        if (Game.staffs.size() >= 17) {
            int i = Game.staffs.size() - 17;
            maxListScrollY = i*50;
        }
    }

    public static void refreshLaborMarket() {
        if (Game.window.windowTabIndex != 3) return;
        tempStaffs.clear();
        for (int i = 0; i<3; i++) {
            tempStaffs.add(new RandomStaff());
        }
    }

    public static void scrollUp() {
        if (Game.window.windowTabIndex != 3) return;
        if (Mouse.g().x()<=700) {
            listScrollY += 25;
            if (listScrollY > 0) listScrollY = 0;
        } else {
            panelScrollY += 25;
            if (panelScrollY > 0) panelScrollY = 0;
        }
    }

    public static void scan() {
        if (Game.window.windowTabIndex != 3) return;
        if (tempStaffs.get(selectedTempStaffIndex).scan >= 3) {
            tempStaffs.get(selectedTempStaffIndex).scan = 3;
            return;
        }
        if (Game.ap >= scanStaffApCost[selectedTempStaffIndex] && Game.money >= scanStaffMoneyCost[selectedTempStaffIndex]) {
            tempStaffs.get(selectedTempStaffIndex).scan++;
            Game.ap -= scanStaffApCost[selectedTempStaffIndex];
            Game.money -= scanStaffMoneyCost[selectedTempStaffIndex];
        }

    }

    public static void deal() {
        if (Game.window.windowTabIndex != 3) return;
        Game.staffs.add((Staff)tempStaffs.get(selectedTempStaffIndex));
        tempStaffs.remove(selectedTempStaffIndex);
        tempStaffs.add(new RandomStaff());
    }

    public static void scrollUDown() {
        if (Game.window.windowTabIndex != 3) return;

        if (Mouse.g().x()<=700) {
            listScrollY -= 25;
            if (listScrollY < -maxListScrollY) listScrollY = -maxListScrollY;
        } else {
            panelScrollY -= 25;
            if (panelScrollY < -maxPanelScrollY) panelScrollY = -maxPanelScrollY;
        }
    }
}
