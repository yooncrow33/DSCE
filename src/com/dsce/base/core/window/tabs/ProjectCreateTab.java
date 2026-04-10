package com.dsce.base.core.window.tabs;

import com.dsce.base.core.Game;
import com.dsce.base.core.contents.project.Project;
import com.dsce.base.core.contents.project.ProjectType;
import com.dsce.base.core.contents.project.internal.Engine;
import com.dsce.base.core.contents.project.internal.Lang;
import com.dsce.base.core.graphics.Button;
import com.dsce.base.core.text.InputText;
import com.dsce.base.sys.input.InputHandler;
import com.dsce.base.sys.mouse.Mouse;
import com.dsce.base.utils.RenderU;
import com.dsce.base.utils.Utils;

import java.awt.*;

import static com.dsce.base.core.Game.*;

public class ProjectCreateTab extends Tab {
    public int step = 0;

    private String tempEngine = "unity";
    private String tempLang = "cs";
    private String tempGraphics = "opengl";
    private String tempName = "default name";
    private String tempType = "standard";

    public String projectCreateTabStep0ButtonKeys[] = {"projectCreateStart"};
    public String projectCreateTabStep1ButtonKeys[] = {"unity", "unreal", "godot", "libgdx", "lwjgl"};
    public String projectCreateTabStep2ButtonKeys[] = {"c", "cpp", "rust", "java", "kotlin", "cs", "js", "py"};
    public String projectCreateTabStep3ButtonKeys[] = {"vulkan", "opengl", "directx"};
    public String projectCreateTabStep4ButtonKeys[] = {"prototype", "standard", "LTS"};
    public String projectCreateTabStep5ButtonKeys[] = {"projectCreate"};
    public String[] projectCreateTabButtons[] = {projectCreateTabStep0ButtonKeys,projectCreateTabStep1ButtonKeys,projectCreateTabStep2ButtonKeys,projectCreateTabStep3ButtonKeys,projectCreateTabStep4ButtonKeys,projectCreateTabStep5ButtonKeys};

    public ProjectCreateTab() {
        for (int i = 0; i < projectCreateTabButtons.length; i++) {
            if (i == 0 || i == 5) {
                buttonMap.put(projectCreateTabButtons[i][0],new Button(1920/2-150,1080-100-120,300,80));
            } else {
                for (int ie = 0; ie < projectCreateTabButtons[i].length; ie++) {
                    buttonMap.put(projectCreateTabButtons[i][ie],new Button((1920/2)-250,260+(ie*70),500,60));
                }
            }
        }
    }

    public void init() {
        int step = 0;

        String tempEngine = "unity";
        String tempLang = "cs";
        String tempGraphics = "opengl";
        String tempName = "default name";
        String tempType = "standard";
    }

    public void clickEvent() {
        switch (step) {
            case 0:
                if (buttonMap.get(projectCreateTabButtons[0][0]).isOnMouse()) {
                    step = 1;
                }
                break;
            case 1:
                if (buttonMap.get(projectCreateTabButtons[1][0]).isOnMouse()) {
                    tempEngine = "unity";
                }
                if (buttonMap.get(projectCreateTabButtons[1][1]).isOnMouse()) {
                    tempEngine = "unreal";
                }
                if (buttonMap.get(projectCreateTabButtons[1][2]).isOnMouse()) {
                    tempEngine = "godot";
                }
                if (buttonMap.get(projectCreateTabButtons[1][3]).isOnMouse()) {
                    tempEngine = "libgdx";
                }
                if (buttonMap.get(projectCreateTabButtons[1][4]).isOnMouse()) {
                    tempEngine = "lwjgl";
                }
                if (buttonMap.get("projectCreateNext").isOnMouse()) {
                    step++;
                }
                if (buttonMap.get("projectCreateBack").isOnMouse()) {
                    step=0;
                }
                break;
            case 2:
                if (buttonMap.get(projectCreateTabButtons[2][0]).isOnMouse()) {
                    tempLang = "c";
                }
                if (buttonMap.get(projectCreateTabButtons[2][1]).isOnMouse()) {
                    tempLang = "cpp";
                }
                if (buttonMap.get(projectCreateTabButtons[2][2]).isOnMouse()) {
                    tempLang = "rust";
                }
                if (buttonMap.get(projectCreateTabButtons[2][3]).isOnMouse()) {
                    tempLang = "java";
                }
                if (buttonMap.get(projectCreateTabButtons[2][4]).isOnMouse()) {
                    tempLang = "kotlin";
                }
                if (buttonMap.get(projectCreateTabButtons[2][5]).isOnMouse()) {
                    tempLang = "cs";
                }
                if (buttonMap.get(projectCreateTabButtons[2][6]).isOnMouse()) {
                    tempLang = "js";
                }
                if (buttonMap.get(projectCreateTabButtons[2][7]).isOnMouse()) {
                    tempLang = "py";
                }
                if (buttonMap.get("projectCreateNext").isOnMouse()) {
                    step++;
                }
                if (buttonMap.get("projectCreateBack").isOnMouse()) {
                    step--;
                }
                break;
            case 3:
                if (buttonMap.get(projectCreateTabButtons[3][0]).isOnMouse()) {
                    tempGraphics = "vulkan";
                }
                if (buttonMap.get(projectCreateTabButtons[3][1]).isOnMouse()) {
                    tempGraphics = "opengl";
                }
                if (buttonMap.get(projectCreateTabButtons[3][2]).isOnMouse()) {
                    tempGraphics = "directx";
                }
                if (buttonMap.get("projectCreateNext").isOnMouse()) {
                    step++;
                    InputHandler.setRequestInputTextForGameStringBuilder(true);
                }
                if (buttonMap.get("projectCreateBack").isOnMouse()) {
                    step--;
                    InputHandler.setRequestInputTextForGameStringBuilder(false);
                }
                break;
            case 4:
                if (buttonMap.get(projectCreateTabButtons[4][0]).isOnMouse()) {
                    tempType = "prototype";
                }
                if (buttonMap.get(projectCreateTabButtons[4][1]).isOnMouse()) {
                    tempType = "standard";
                }
                if (buttonMap.get(projectCreateTabButtons[4][2]).isOnMouse()) {
                    tempType = "LTS";
                }
                if (buttonMap.get("projectCreateNext").isOnMouse()) {
                    step++;
                    InputHandler.setRequestInputTextForGameStringBuilder(true);
                }
                if (buttonMap.get("projectCreateBack").isOnMouse()) {
                    step--;
                    InputHandler.setRequestInputTextForGameStringBuilder(false);
                }
                break;
            case 5:
                if (buttonMap.get(projectCreateTabButtons[5][0]).isOnMouse()) {
                    step = 0;
                    Project p = new Project();
                    tempName=InputText.lastInputWord;
                    p.registerName(tempName);
                    p.registerCodeQuality(0.0f);
                    p.registerGraphics(0.0f);
                    p.registerFunny(0.0f);
                    p.registerAddictive(0.0f);
                    p.registerReleased(false);
                    p.registerPrice(0.0f);
                    p.registerOptimization(0.0f);
                    p.registerStability(0.0f);
                    p.registerFileSize(0);
                    p.registerScale(0);
                    p.registerProjectType(ProjectType.type.valueOf(tempType));
                    p.registerProjectEngineType(Engine.type.valueOf(tempEngine));
                    p.registerProjectGraphicsType(com.dsce.base.core.contents.project.internal.Graphics.type.valueOf(tempGraphics));
                    p.registerProjectLangType(Lang.type.valueOf(tempLang));

                    Game.addProject(p);
                    InputText.initLastInputWord();
                }
                if (Mouse.g().x() >= 300 && Mouse.g().x() <= 300 + (1920 - 600) &&
                        Mouse.g().y() >= 300 && Mouse.g().y() <= 300 + 100) {
                    InputHandler.setRequestInputTextForGameStringBuilder(true);
                }
                if (buttonMap.get("projectCreateNext").isOnMouse()) {
                    step--;
                    InputHandler.setRequestInputTextForGameStringBuilder(false);
                }
                break;
            default:
                System.err.println("Invalid Project Create Step Index: " + step);
                System.err.println("Step Index Error! " + Utils.getReportMessage());
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(new Color(25, 115, 175));
        g.fillRect(0,100,1920,880);
        switch (step) {
            case 0:
                g.setColor(Color.white);
                g.setFont(new Font(Font.MONOSPACED,Font.BOLD,140));
                RenderU.drawStringCenter(g,"Create New Project!",(1920/2),350);

                Button startButton = buttonMap.get(projectCreateTabButtons[0][0]);
                g2.setStroke(new BasicStroke(2f));
                g.setColor(startButton.isOnMouse()? Color.green : Color.gray);
                g.fillRect(startButton.getX(),startButton.getY(),startButton.getW(),startButton.getH());
                g.setColor(Color.white);
                g.drawRect(startButton.getX(),startButton.getY(),startButton.getW()-2,startButton.getH()-2);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
                g.setColor(Color.black);
                RenderU.drawStringCenter(g,"Start",startButton.getX()+(startButton.getW()/2),startButton.getY()+32);
                break;
            case 1:
                for (int i = 0; i < projectCreateTabStep1ButtonKeys.length; i++) {
                    Button current = buttonMap.get(projectCreateTabStep1ButtonKeys[i]);
                    RenderU.drawButton(g,current,Color.green,Color.gray,Color.white,Color.black,32,projectCreateTabStep1ButtonKeys[i],25);
                }
                g.setColor(Color.white);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 82));
                RenderU.drawStringCenter(g,"Engine Select",1920/2,185);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 64));
                g.drawString("Selected Engine : ",20,970);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 82));
                RenderU.drawStringCenter(g,tempEngine,1920/2,920);
                RenderU.drawButton(g,buttonMap.get("projectCreateNext"),Color.green,Color.gray,Color.white,Color.black,32,"next",25);
                RenderU.drawButton(g,buttonMap.get("projectCreateBack"),Color.green,Color.gray,Color.white,Color.black,32,"back",25);
                break;
            case 2:
                for (int i = 0; i < projectCreateTabStep2ButtonKeys.length; i++) {
                    Button current = buttonMap.get(projectCreateTabStep2ButtonKeys[i]);
                    RenderU.drawButton(g,current,Color.green,Color.gray,Color.white,Color.black,32,projectCreateTabStep2ButtonKeys[i],25);
                }
                g.setColor(Color.white);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 82));
                RenderU.drawStringCenter(g,"Lang Select",1920/2,185);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 64));
                g.drawString("Selected Lang : ",20,970);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 82));
                RenderU.drawStringCenter(g,tempLang,1920/2,920);
                RenderU.drawButton(g,buttonMap.get("projectCreateNext"),Color.green,Color.gray,Color.white,Color.black,32,"next",25);
                RenderU.drawButton(g,buttonMap.get("projectCreateBack"),Color.green,Color.gray,Color.white,Color.black,32,"back",25);
                break;
            case 3:
                for (int i = 0; i < projectCreateTabStep3ButtonKeys.length; i++) {
                    Button current = buttonMap.get(projectCreateTabStep3ButtonKeys[i]);
                    RenderU.drawButton(g,current,Color.green,Color.gray,Color.white,Color.black,32,projectCreateTabStep3ButtonKeys[i],25);
                }
                g.setColor(Color.white);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 82));
                RenderU.drawStringCenter(g,"Graphics Select",1920/2,185);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 64));
                g.drawString("Selected Graphics : ",20,970);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 82));
                RenderU.drawStringCenter(g,tempGraphics,1920/2,920);
                RenderU.drawButton(g,buttonMap.get("projectCreateNext"),Color.green,Color.gray,Color.white,Color.black,32,"next",25);
                RenderU.drawButton(g,buttonMap.get("projectCreateBack"),Color.green,Color.gray,Color.white,Color.black,32,"back",25);
                break;
            case 4:
                for (int i = 0; i < projectCreateTabStep4ButtonKeys.length; i++) {
                    Button current = buttonMap.get(projectCreateTabStep4ButtonKeys[i]);
                    RenderU.drawButton(g,current,Color.green,Color.gray,Color.white,Color.black,32,projectCreateTabStep4ButtonKeys[i],25);
                }
                g.setColor(Color.white);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 82));
                RenderU.drawStringCenter(g,"Project Type Select",1920/2,185);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 64));
                g.drawString("Selected Type : ",20,970);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 82));
                RenderU.drawStringCenter(g,tempType,1920/2,920);
                RenderU.drawButton(g,buttonMap.get("projectCreateNext"),Color.green,Color.gray,Color.white,Color.black,32,"next",25);
                RenderU.drawButton(g,buttonMap.get("projectCreateBack"),Color.green,Color.gray,Color.white,Color.black,32,"back",25);
                break;
            case 5:
                g.setColor(InputHandler.isRequestInputTextForGameStringBuilder()? Color.green : Color.white);
                g.fillRect(400,300,1920-800,100);
                g.setColor(Color.black);
                g.setFont(new Font(Font.MONOSPACED,Font.BOLD,82));
                RenderU.drawStringCenter(g,InputText.currentWord,(1920/2),350);

                g.setColor(Color.white);
                Button create = buttonMap.get(projectCreateTabButtons[5][0]);
                g2.setStroke(new BasicStroke(2f));
                g.setColor(create.isOnMouse()? Color.green : Color.gray);
                g.fillRect(create.getX(),create.getY(),create.getW(),create.getH());
                g.setColor(Color.white);
                g.drawRect(create.getX(),create.getY(),create.getW()-2,create.getH()-2);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
                g.setColor(Color.black);
                RenderU.drawStringCenter(g,"Create",create.getX()+(create.getW()/2),create.getY()+32);
                RenderU.drawButton(g,buttonMap.get("projectCreateNext"),Color.green,Color.gray,Color.white,Color.black,32,"back",25);
                break;
            default:
                System.err.println("Invalid Project Create Step Index: " + step);
                System.err.println("Step Index Error! " + Utils.getReportMessage());
        }
    }
    public static void addDummyProject() {
        Project p = new Project();
        p.registerName("dummy");
        p.registerCodeQuality(0.0f);
        p.registerGraphics(0.0f);
        p.registerFunny(0.0f);
        p.registerAddictive(0.0f);
        p.registerReleased(false);
        p.registerPrice(0.0f);
        p.registerOptimization(0.0f);
        p.registerStability(0.0f);
        p.registerFileSize(0);
        p.registerScale(0);
        p.registerProjectType(ProjectType.type.valueOf("LTS"));
        p.registerProjectEngineType(Engine.type.valueOf("lwjgl"));
        p.registerProjectGraphicsType(com.dsce.base.core.contents.project.internal.Graphics.type.valueOf("opengl"));
        p.registerProjectLangType(Lang.type.valueOf("cs"));

        Game.addProject(p);
    }
}
