package com.dsce.base.core.window.tabs;

import com.dsce.base.core.Game;
import com.dsce.base.core.contents.staff.Staff;
import com.dsce.base.core.contents.staff.inetnal.RandomStaff;
import com.dsce.base.core.contents.team.Team;
import com.dsce.base.core.graphics.Button;
import com.dsce.base.core.graphics.ScrollButton;
import com.dsce.base.core.graphics.overlay.internal.OverlayManager;
import com.dsce.base.core.graphics.popup.internal.Popup;
import com.dsce.base.core.graphics.popup.internal.PopupManager;
import com.dsce.base.core.text.InputText;
import com.dsce.base.sys.input.InputHandler;
import com.dsce.base.sys.mouse.Mouse;
import com.dsce.base.utils.RawPointer;
import com.dsce.base.utils.RenderU;
import com.dsce.base.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

public class StaffTab extends Tab {
    public enum state {
        company,
        team,
        laborMarket;
    }

    private static state tabState = state.company;

    private static ArrayList<RandomStaff> tempStaffs = new ArrayList<>();

    private static int staffListScrollY = 0;
    private static int maxStaffListScrollY = 0;

    private static int teamListScrollY = 0;
    private static int maxTeamListScrollY = 0;

    private static int staffAtTeamPanelListScrollY = 0;
    private static int maxStaffAtTeamPanelListScrollY = 0;

    private final int scrollXInt = 0;
    private static int panelScrollY = 0;
    private static final int maxPanelScrollY = 800;

    public static int selectedStaffIndex = 0;
    public static int selectedStaffAtTeam = 0;
    public static int selectedTeamIndex = 0;
    public static int selectedTempStaffIndex = 0;

    public static final int scanStaffMoneyCost[] = {10,30,60,100};
    public static final int scanStaffApCost[] = {3,5,8,12};

    private com.dsce.base.core.graphics.Button cb = new com.dsce.base.core.graphics.Button(0,920,1920/3,60);
    private com.dsce.base.core.graphics.Button tb = new com.dsce.base.core.graphics.Button(1920/3,920,1920/3,60);
    private com.dsce.base.core.graphics.Button lb = new com.dsce.base.core.graphics.Button((1920/3)*2,920,1920/3,60);

    String s[] = {"Press S for Scan!", "Press D for Deal!", "Press F for Refresh"};

    Button t1 = new Button(20+(0*630), 120,620,770);
    Button t2 = new Button(20+(1*630), 120,620,770);
    Button t3 = new Button(20+(2*630), 120,620,770);

    Button re = new Button(700+20,100+730,1180-420,60);

    Button de = new Button(20,100+730,660,60);

    Button createTeam = new Button(1920-420,100+730,400,60);

    RawPointer scrollX = new RawPointer(this,"scrollXInt");
    RawPointer scrollY = new RawPointer(StaffTab.class,"panelScrollY");

    ScrollButton dismissButton = new ScrollButton(720,400,280,60,scrollX,scrollY);
    ScrollButton teamChange = new ScrollButton(720+300,400,280,60,scrollX,scrollY);
    ScrollButton learn = new ScrollButton(720+(2*300),400,280,60,scrollX,scrollY);
    ScrollButton numm = new ScrollButton(720+(3*300),400,280,60,scrollX,scrollY);

    ScrollButton panelButtons[] = {dismissButton,teamChange,learn,numm};
    String panelButtonLabels[] = {"Dismiss", "Team Change", "Learn", "null"};

    public StaffTab() {
        tempStaffs.clear();
        for (int i = 0; i<3; i++) {
            tempStaffs.add(new RandomStaff());
        }
    }

    public void clickEvent() {
        if (cb.isOnMouse()) {
            tabState = state.company;
        }
        if (tb.isOnMouse()) {
            tabState = state.team;
        }
        if (lb.isOnMouse()) {
            tabState = state.laborMarket;
        }
        if (tabState==state.company) {
            if (!(Mouse.g().x()>=700)) {
                for (int i = 0; i < Game.staffs.size(); i++) {
                    if (100+ staffListScrollY +(i*50)<= Mouse.g().y()&&100+50+ staffListScrollY +(i*50)>=Mouse.g().y()) {
                        //managementSelectedProject =  Game.staffs.get(i);
                        selectedStaffIndex = i;
                    }
                }
            }

            for (int i = 0; i<panelButtons.length; i++) {
                if (panelButtons[i].isOnMouse()) {
                    switch (i) {
                        case 0 :
                            PopupManager.enablePopup("dismissp");
                            break;
                        case 1 :
                            OverlayManager.enableListOverlay("team");
                            break;
                        case 2 :

                            break;
                        case 3 :

                            break;
                        default:
                            System.err.println("Panel button Index Error at Staff tab! "+Utils.getReportMessage());
                    }
                }
            }
        } else if (tabState==state.laborMarket) {
            if (t1.isOnMouse()) {
                selectedTempStaffIndex = 0;
            }
            if (t2.isOnMouse()) {
                selectedTempStaffIndex = 1;
            }
            if (t3.isOnMouse()) {
                selectedTempStaffIndex = 2;
            }
        } else if (tabState==state.team) {
            if (!(Mouse.g().x()>=700)) {
                for (int i = 0; i < Game.teams.size(); i++) {
                    if (100+ teamListScrollY +(i*50)<= Mouse.g().y()&&100+50+ teamListScrollY +(i*50)>=Mouse.g().y()) {
                        selectedTeamIndex = i;
                        selectedStaffAtTeam = 0;
                    }
                }
            } else {
                for (int i = 0; i < Game.teams.get(selectedTeamIndex).staffs.size(); i++) {
                    if (100+ staffAtTeamPanelListScrollY +(i*50)<= Mouse.g().y()&&100+50+ staffAtTeamPanelListScrollY +(i*50)>=Mouse.g().y()) {
                        selectedStaffAtTeam = i;
                    }

                    if (100+ staffAtTeamPanelListScrollY +(i*50)<= Mouse.g().y()&&100+50+ staffAtTeamPanelListScrollY +(i*50)>=Mouse.g().y() && Mouse.g().x()>=1700) {
                        OverlayManager.enableListOverlay("teamat");
                    } else if (100+ staffAtTeamPanelListScrollY +(i*50)<= Mouse.g().y()&&100+50+ staffAtTeamPanelListScrollY +(i*50)>=Mouse.g().y() && Mouse.g().x()>=1700-240&&Mouse.g().x()<=1700) {
                        for (int ii = 0; ii<Game.staffs.size(); ii++) {
                            if (Game.staffs.get(ii)==Game.teams.get(selectedTeamIndex).staffs.get(selectedStaffAtTeam)) {
                                selectedStaffIndex = ii;
                                tabState = state.company;
                            }
                        }
                    }
                }
            }
            if (createTeam.isOnMouse()) {
                if (InputText.lastInputWord.equals("null")) {
                    return;
                }
                for (Team tt: Game.teams) {
                    if (tt.getName().equals(InputText.lastInputWord)) {
                        return;
                    }
                }
                Team t = new Team();
                t.registerName(InputText.lastInputWord);
                Game.teams.add(t);
            }
            if (re.isOnMouse()) {
                InputHandler.setRequestInputTextForGameStringBuilder(true);
            }
            if (de.isOnMouse()) {
                deleteTeam();
            }
        }
    }

    public void renderStaffPanel(Graphics g, int x) {
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
        for (int i = 0; i<panelButtons.length; i++) {
            RenderU.drawScrollButton(g,panelButtons[i],Color.green,Color.gray,Color.white,Color.black,32,panelButtonLabels[i], 25);
        }
    }


    public void renderCreateTeamPanel(Graphics g, int x) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(new Color(25, 115, 175));
        g.fillRect(x,100+panelScrollY,1220,880+800);
        g2.setStroke(new BasicStroke(2f));
        g.setColor(Color.white);
        g.drawRect(x,100+panelScrollY,1220,880+800);

        if (Game.teams.get(selectedTeamIndex).staffs.isEmpty()) {
            g.setColor(Color.white);
            g.setFont(new Font(Font.MONOSPACED,Font.BOLD,92));
            RenderU.drawStringCenter(g,"Empty!",x+1220/2,300);
        } else {
            for (int i = 0; i < Game.teams.get(selectedTeamIndex).staffs.size(); i++) {
                if (i == selectedStaffAtTeam) {
                    g.setColor(Color.white);
                    g.fillRect(x+0, 100 + staffAtTeamPanelListScrollY + (i * 50), 1220, 50);
                    g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
                    g.setColor(Color.blue);
                    g.drawString(String.valueOf(Game.teams.get(selectedTeamIndex).staffs.get(i).getTeam()), x+10, 100 + staffAtTeamPanelListScrollY + (i * 50) + 35);
                    g.setColor(Color.black);
                    g.drawString("| " + Game.teams.get(selectedTeamIndex).staffs.get(i).getName(), x+200, 100 + staffAtTeamPanelListScrollY + (i * 50) + 35);
                    g.setColor(Color.red);
                    g.fillRect(x+1000, 105 + staffAtTeamPanelListScrollY + (i * 50), 220, 40);
                    g.setColor(Color.white);
                    g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,16));
                    RenderU.drawStringCenter(g,"Change Team",x+1000+110,120 + staffAtTeamPanelListScrollY + (i * 50));

                    g.setColor(Color.red);
                    g.fillRect(x+1000-240, 105 + staffAtTeamPanelListScrollY + (i * 50), 220, 40);
                    g.setColor(Color.white);
                    g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,16));
                    RenderU.drawStringCenter(g,"View in Staff Tab",x+1000-240+110,120 + staffAtTeamPanelListScrollY + (i * 50));
                } else {
                    if ((i & 1) == 0) {
                        g.setColor(new Color(5, 100, 135));
                    } else {
                        g.setColor(new Color(25, 130, 185));
                    }
                    g.fillRect(x+0, 100 + staffAtTeamPanelListScrollY + (i * 50), 1220, 50);
                    g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));

                    g.setColor(Color.green);
                    g.drawString(String.valueOf(Game.teams.get(selectedTeamIndex).staffs.get(i).getTeam()), x+10, 100 + staffAtTeamPanelListScrollY + (i * 50) + 35);
                    g.setColor(Color.white);
                    g.drawString("| " + Game.teams.get(selectedTeamIndex).staffs.get(i).getName(), x+200, 100 + staffAtTeamPanelListScrollY + (i * 50) + 35);
                    g.setColor(Color.yellow);
                    g.fillRect(x+1000, 105 + staffAtTeamPanelListScrollY + (i * 50), 220, 40);
                    g.setColor(Color.black);
                    g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,16));
                    RenderU.drawStringCenter(g,"Change Team",x+1000+110,120 + staffAtTeamPanelListScrollY + (i * 50));

                    g.setColor(Color.yellow);
                    g.fillRect(x+1000-240, 105 + staffAtTeamPanelListScrollY + (i * 50), 220, 40);
                    g.setColor(Color.black);
                    g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,16));
                    RenderU.drawStringCenter(g,"View in Staff Tab",x+1000-240+110,120 + staffAtTeamPanelListScrollY + (i * 50));
                }

            }
        }

        RenderU.drawButton(g,de,Color.green,Color.gray,Color.white,Color.black,32,"Delete Team",25);

        RenderU.drawButton(g,createTeam,Color.green,Color.gray,Color.white,Color.black,32,"Create New Team!",25);

        g.setColor(InputHandler.isRequestInputTextForGameStringBuilder()? Color.green : Color.white);
        g.fillRect(x+20,100+730,1180-420,60);
        g.setColor(Color.black);
        g.setFont(new Font(Font.MONOSPACED,Font.PLAIN,42));
        RenderU.drawStringCenter(g,InputText.currentWord,x+(1220-420)/2,860);
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
                        g.fillRect(0, 100 + staffListScrollY + (i * 50), 1920, 50);
                        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
                        g.setColor(Color.blue);
                        g.drawString(String.valueOf(Game.staffs.get(i).getTeam()), 10, 100 + staffListScrollY + (i * 50) + 35);
                        g.setColor(Color.black);
                        g.drawString("| " + Game.staffs.get(i).getName(), 200, 100 + staffListScrollY + (i * 50) + 35);
                    } else {
                        if ((i & 1) == 0) {
                            g.setColor(new Color(5, 100, 135));
                        } else {
                            g.setColor(new Color(25, 130, 185));
                        }
                        g.fillRect(0, 100 + staffListScrollY + (i * 50), 1920, 50);
                        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
                        g.setColor(Color.green);
                        g.drawString(String.valueOf(Game.staffs.get(i).getTeam()), 10, 100 + staffListScrollY + (i * 50) + 35);
                        g.setColor(Color.white);
                        g.drawString("| " + Game.staffs.get(i).getName(), 200, 100 + staffListScrollY + (i * 50) + 35);
                    }
                }
                renderStaffPanel(g, 700);
            }
        } else if (tabState == state.laborMarket) {
            //labor market
            for (int i = 0; i<tempStaffs.size(); i++) {
                g.setColor(i == selectedTempStaffIndex ? Color.white : new Color(2, 140, 175));
                g.fillRect(20 + (i * 630), 120, 620, 770);
                g2.setStroke(new BasicStroke(6f));
                g.setColor(i == selectedTempStaffIndex ? Color.black : Color.white);
                g.drawRect(20 + (i * 630), 120, 620, 770);
                g.setColor(i == selectedTempStaffIndex ? Color.blue : Color.white);
                int c = 20 + (i * 630) + 300;
                RandomStaff s = tempStaffs.get(i);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
                RenderU.drawStringCenter(g, this.s[i], c, 150);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 92));
                RenderU.drawStringCenter(g, s.getName(), c, 210);
                g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 54));
                if (!tempStaffs.get(i).isDummy()) {
                    if (s.scan == 0) {
                        RenderU.drawStringCenter(g, "?", c, 430 - 70);
                        RenderU.drawStringCenter(g, "?", c, 490 - 70);
                        RenderU.drawStringCenter(g, "?", c, 550 - 70);

                        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
                        RenderU.drawStringCenter(g, "Asking Price :" + s.getLaborCost(), c, 530); // hope labor cost
                        RenderU.drawStringCenter(g, "?", c, 580); // temp slot
                        RenderU.drawStringCenter(g, "?", c, 630); // Trait y/n
                        RenderU.drawStringCenter(g, "?", c, 680); // Trait
                    } else if (s.scan == 1) {
                        RenderU.drawStringCenter(g, s.getEngineStack().toString(), c, 430 - 70);
                        RenderU.drawStringCenter(g, s.getLangStack().toString(), c, 490 - 70);
                        RenderU.drawStringCenter(g, s.getGraphicsStack().toString(), c, 550 - 70);

                        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
                        RenderU.drawStringCenter(g, "Asking Price :" + s.getLaborCost(), c, 530); // hope labor cost
                        RenderU.drawStringCenter(g, "?", c, 580); // temp slot
                        RenderU.drawStringCenter(g, "?", c, 630); // Trait y/n
                        RenderU.drawStringCenter(g, "?", c, 680); // Trait
                    } else if (s.scan == 2) {
                        RenderU.drawStringCenter(g, s.getEngineStack().toString(), c, 430 - 70);
                        RenderU.drawStringCenter(g, s.getLangStack().toString(), c, 490 - 70);
                        RenderU.drawStringCenter(g, s.getGraphicsStack().toString(), c, 550 - 70);

                        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
                        RenderU.drawStringCenter(g, "Asking Price :" + s.getLaborCost(), c, 530); // hope labor cost
                        RenderU.drawStringCenter(g, "?", c, 580); // temp slot
                        RenderU.drawStringCenter(g, "Have a Trait : " + s.isGetTrait, c, 630); // Trait y/n
                        RenderU.drawStringCenter(g, "?", c, 680); // Trait
                    } else if (s.scan == 3) {
                        RenderU.drawStringCenter(g, s.getEngineStack().toString(), c, 430 - 70);
                        RenderU.drawStringCenter(g, s.getLangStack().toString(), c, 490 - 70);
                        RenderU.drawStringCenter(g, s.getGraphicsStack().toString(), c, 550 - 70);

                        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
                        RenderU.drawStringCenter(g, "Asking Price :" + s.getLaborCost(), c, 530); // hope labor cost
                        RenderU.drawStringCenter(g, "?", c, 580); // temp slot
                        RenderU.drawStringCenter(g, "Have a Trait : " + s.isGetTrait, c, 630); // Trait y/n
                        for (int i2 = 0; i2 < s.getTraits().size(); i2++) {
                            RenderU.drawStringCenter(g, "Trait : " + s.getTraits().get(i2).getName(), c, 680 + (i2 * 50)); // Trait
                        }
                    } else {
                        System.err.println("Labor Market Staff Scan Index Error! " + Utils.getReportMessage());
                    }
                }
                RenderU.drawStringCenter(g, "Stacks :", c, 300);
            }
        } else if (tabState==state.team) {
            for (int i = 0; i < Game.teams.size(); i++) {
                if (i == selectedTeamIndex) {
                    g.setColor(Color.white);
                    g.fillRect(0, 100 + teamListScrollY + (i * 50), 1920, 50);
                    g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
                    g.setColor(Color.blue);
                    g.drawString(Game.teams.get(i).getName(), 10, 100 + teamListScrollY + (i * 50) + 35);
                    g.setColor(Color.black);
                    g.drawString("| Scale : " + Game.teams.get(i).staffs.size(), 200, 100 + teamListScrollY + (i * 50) + 35);
                } else {
                    if ((i & 1) == 0) {
                        g.setColor(new Color(5, 100, 135));
                    } else {
                        g.setColor(new Color(25, 130, 185));
                    }
                    g.fillRect(0, 100 + teamListScrollY + (i * 50), 1920, 50);
                    g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));

                    g.setColor(Color.green);
                    g.drawString(Game.teams.get(i).getName(), 10, 100 + teamListScrollY + (i * 50) + 35);
                    g.setColor(Color.white);
                    g.drawString("| Scale : " + Game.teams.get(i).staffs.size(), 200, 100 + teamListScrollY + (i * 50) + 35);
                }
            }
            renderCreateTeamPanel(g,700);
        }

        if (tabState == state.company) {
            g.setColor(new Color(5, 100, 135));
        } else {
            g.setColor(new Color(25, 130, 185));
        }
        g.fillRect(0,920,1920/3,60);
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(2f));
        g.drawRect(0,920,1920/3,60);
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));
        RenderU.drawStringCenter(g,"Company", 1920/3/2,940);

        if (tabState == state.team) {
            g.setColor(new Color(5, 100, 135));
        } else {
            g.setColor(new Color(25, 130, 185));
        }
        g.fillRect(1920/3,920,1920/3,60);
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(2f));
        g.drawRect(1920/3,920,1920/3,60);
        RenderU.drawStringCenter(g,"Team", 1920/3+(1920/3)/2,940);

        if (tabState == state.laborMarket) {
            g.setColor(new Color(5, 100, 135));
        } else {
            g.setColor(new Color(25, 130, 185));
        }
        g.fillRect((1920/3)*2,920,1920/3,60);
        g.setColor(Color.white);
        g2.setStroke(new BasicStroke(2f));
        g.drawRect((1920/3)*2,920,1920/3,60);
        RenderU.drawStringCenter(g,"Labor Market", 1920/3+(1920/3)+(1920/3)/2,940);

        //update
        if (Game.staffs.size() >= 16) {
            int i = Game.staffs.size() - 16;
            maxStaffListScrollY = i*50;
        } else {
            maxStaffListScrollY = 0;
        }
        if (Game.teams.size() >= 14) {
            int i = Game.teams.size() - 14;
            maxTeamListScrollY = i*50;
        } else {
            maxTeamListScrollY = 0;
        }
        if (Game.teams.get(selectedTeamIndex).staffs.size() >= 14) {
            int i = Game.teams.get(selectedTeamIndex).staffs.size() - 14;
            maxStaffAtTeamPanelListScrollY = i*50;
        } else {
            maxStaffAtTeamPanelListScrollY = 0;
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
        if (tabState==state.company) {
            if (Mouse.g().x()<=700) {
                staffListScrollY += 25;
                if (staffListScrollY > 0) staffListScrollY = 0;
            } else {
                panelScrollY += 25;
                if (panelScrollY > 0) panelScrollY = 0;
            }
        } else if (tabState==state.team) {
            if (Mouse.g().x()<=700) {
                teamListScrollY += 25;
                if (teamListScrollY > 0) teamListScrollY = 0;
            } else {
                staffAtTeamPanelListScrollY += 25;
                if (staffAtTeamPanelListScrollY > 0) staffAtTeamPanelListScrollY = 0;
            }
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
        if (!(tabState==state.laborMarket)) return;
        if (tempStaffs.get(selectedTempStaffIndex).isDummy()) return;
        Game.staffs.add((Staff)tempStaffs.get(selectedTempStaffIndex));
        tempStaffs.get(selectedTempStaffIndex).loadTeam();
        tempStaffs.remove(selectedTempStaffIndex);
        tempStaffs.add(new RandomStaff(true));
    }

    public static void scrollUDown() {
        if (Game.window.windowTabIndex != 3) return;

        if (tabState==state.company) {
            if (Mouse.g().x()<=700) {
                staffListScrollY -= 25;
                if (staffListScrollY < -maxStaffListScrollY) staffListScrollY = -maxStaffListScrollY;
            } else {
                panelScrollY -= 25;
                if (panelScrollY < -maxPanelScrollY) panelScrollY = -maxPanelScrollY;
            }
        } else if (tabState==state.team) {
            if (Mouse.g().x()<=700) {
                teamListScrollY -= 25;
                if (teamListScrollY < -maxTeamListScrollY) teamListScrollY = -maxTeamListScrollY;
            } else {
                staffAtTeamPanelListScrollY -= 25;
                if (staffAtTeamPanelListScrollY < -maxStaffAtTeamPanelListScrollY) staffAtTeamPanelListScrollY = -maxStaffAtTeamPanelListScrollY;
            }
        }
    }

    public void deleteTeam() {
        PopupManager.enablePopup("deleteTeamPopup");
    }
}
