package com.dsce.base.core.graphics.overlay.internal;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OverlayManager {
    public static Map<String, ListOverlay> listOverlays = new HashMap();

    public static int x,y = 200;

    public static void setX(int xd) {
        x = xd;
    }

    public static void setY(int yd) {
        y = yd;
    }

    public static void addX(int xd) {
        x += xd;
    }

    public static void addY(int yd) {
        y += yd;
    }

    public static void registerListOverly(String key, ListOverlay listOverlay) {
        listOverlays.put(key,listOverlay);
    }

    public static void enableListOverlay(String key) {
        for (ListOverlay p : listOverlays.values()) {
            p.enabled = false;
            if (Objects.equals(p.key, key)) {
                p.enabled = true;
            }
        }
    }

    public static void render(Graphics g) {
        for (ListOverlay p : listOverlays.values()) {
            if (!p.enabled) continue;
            p.render(g);
        }
    }

    public static void clickEvent() {
        for (ListOverlay p : listOverlays.values()) {
            if (p.enabled) {
                p.clickEvent();
            }
        }
    }
    public static void scrollUp() {
        for (ListOverlay p : listOverlays.values()) {
            if (p.enabled) {
                p.scrollUp();
            }
        }
    }
    public static void scrollDown() {
        for (ListOverlay p : listOverlays.values()) {
            if (p.enabled) {
                p.scrollUDown();
            }
        }
    }

}
