package com.dsce.base.core.graphics.overlay.internal;

import com.dsce.base.sys.mouse.Mouse;
import com.dsce.base.utils.RenderU;

import java.awt.*;
import java.util.ArrayList;

public abstract class ListOverlay {
    int mdx, mdy = 20;
    int width = 500;
    int height = 390;
    final ArrayList<?  extends ArrList> list;
    public int listScrollY;
    public int maxListScrollY = 0;
    public int selectedIndex;
    public boolean enabled = false;
    public String key;

    public ListOverlay(String key, ArrayList<? extends ArrList> list) {
        this.key = key;
        this.list = list;
    }

    public void clickEvent() {
        for (int i = 0; i < list.size(); i++) {
            if (Mouse.g().x()>= OverlayManager.x &&Mouse.g().x()<= OverlayManager.x +width&& OverlayManager.y +listScrollY +(i*50)<= Mouse.g().y()&&50+ OverlayManager.y +listScrollY +(i*50)>=Mouse.g().y()) {
                //managementSelectedProject =  list.get(i);
                selectedIndex = i;
            }
        }
    }
    public void render(Graphics g) {
        if (list.size() >= 7) {
            int i = list.size() - 7;
            maxListScrollY = i*50;
        }
        g.setColor(Color.white);
        g.fillRect(OverlayManager.x -2, OverlayManager.y -2,width+4,height+4);

        ((Graphics2D) g).setStroke(new BasicStroke(3f));
        g.setColor(Color.black);
        g.drawRect(OverlayManager.x -2, OverlayManager.y -2,width+4,height+4);

        for (int i = 0; i < list.size(); i++) {
            if (OverlayManager.y + listScrollY + (i * 50) < OverlayManager.y) continue;
            if (i==selectedIndex) {
                g.setColor(Color.white);
                g.fillRect(OverlayManager.x, OverlayManager.y + listScrollY + (i * 50), width, 50);
                g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
                g.setColor(Color.blue);
                g.drawString(list.get(i).getLabel(), OverlayManager.x +10, OverlayManager.y + listScrollY + (i * 50) + 35);
                g.setColor(Color.black);
                g.drawString("| " + list.get(i).getValue(), OverlayManager.x +200, OverlayManager.y + listScrollY + (i * 50) + 35);
            } else {
                if ((i & 1) == 0) {
                    g.setColor(new Color(5, 100, 135));
                } else {
                    g.setColor(new Color(25, 130, 185));
                }
                g.fillRect(OverlayManager.x, OverlayManager.y + listScrollY + (i * 50), width, 50);
                g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
                g.setColor(Color.green);
                g.drawString(list.get(i).getLabel(), OverlayManager.x +10, OverlayManager.y + listScrollY + (i * 50) + 35);
                g.setColor(Color.white);
                g.drawString("| " + list.get(i).getValue(), OverlayManager.x +200, OverlayManager.y + listScrollY + (i * 50) + 35);
            }
            if (listScrollY+(i*50)>=300) {
                break;
            }
        }

        g.setColor(Color.gray);
        g.fillRect(OverlayManager.x,OverlayManager.y,width,50);
        g.setColor(Color.blue);
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,40));
        RenderU.drawStringCenter(g,"temp", OverlayManager.x+250,OverlayManager.y+18);

        g.setColor(Color.gray);
        g.fillRect(OverlayManager.x,OverlayManager.y+350,width,40);
        g.setColor(Color.blue);
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,35));
        RenderU.drawStringCenter(g,"temp", OverlayManager.x+250,OverlayManager.y+350+15);
    }

    public void scrollUp() {
        System.out.println("db");
        if (!enabled) return;
        listScrollY += 25;
        if (listScrollY > -50) listScrollY = 50;
    }

    public void scrollUDown() {
        if (!enabled) return;
        listScrollY -= 25;
        if (listScrollY < -maxListScrollY) listScrollY = -maxListScrollY;
    }

    public ArrList getCurrentValue() {
        return list.get(selectedIndex);
    }

    public abstract void exe();
}
