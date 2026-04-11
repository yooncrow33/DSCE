package com.dsce.base.core.popup.internal;

import com.dsce.base.sys.mouse.Click;
import com.dsce.base.sys.mouse.IClickEvent;

import java.awt.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PopupManager {
    private static Map<String, Popup> popups = new HashMap<>();
    public static boolean requestFocus = false;

    public static void registerPopup(String key,Popup popup) {
        popups.put(key,popup);
    }

    public void update() {
        requestFocus = false;
        for (Popup p : popups.values()) {
            if (p.enabled) {requestFocus = true; break;}
        }
    }

    public static void enablePopup(String key) {
        for (Popup p : popups.values()) {
            p.enabled = false;
            if (Objects.equals(p.key, key)) {
                p.enabled = true;
            }
        }
    }

    public void render(Graphics g) {
        for (Popup p : popups.values()) {
            if (!p.enabled) continue;
            p.render(g);
        }
    }


    public static void clickEvent() {
        for (Popup p : popups.values()) {
            if (!p.enabled) continue;
            p.clickEvent();
        }
    }
}
