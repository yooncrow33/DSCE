package com.dsce.base.core;

import com.dsce.base.core.graphics.overlay.internal.OverlayManager;
import com.dsce.base.core.window.tabs.ManagementTab;
import com.dsce.base.core.window.tabs.ProjectCreateTab;
import com.dsce.base.core.window.tabs.StaffTab;
import com.dsce.base.sys.input.InputHandler;
import com.dsce.base.sys.input.MouseListener;

import java.awt.event.KeyEvent;

public class GameInput extends com.dsce.base.sys.input.InputExecutor {

    boolean debug = false;

    @Override
    public void keyPressedExe(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F1) {
            debug = true;
        }
        if (debug) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                ProjectCreateTab.addDummyProject();
            }
        }
    }

    @Override
    public void keyReleasedExe(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            debug = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            StaffTab.scan();
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            StaffTab.deal();
        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            StaffTab.refreshLaborMarket();
        }
    }

    @Override
    public void scrollUpExe() {
        OverlayManager.scrollUp();
        ManagementTab.scrollUp();
        StaffTab.scrollUp();
    }

    @Override
    public void scrollDownExe() {
        OverlayManager.scrollDown();
        ManagementTab.scrollUDown();
        StaffTab.scrollUDown();
    }
}
