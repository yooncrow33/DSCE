package com.dsce.base.core;

import com.dsce.base.core.window.tabs.ManagementTab;
import com.dsce.base.core.window.tabs.ProjectCreateTab;
import com.dsce.base.sys.input.InputHandler;
import com.dsce.base.sys.input.MouseListener;

import java.awt.event.KeyEvent;

public class GameInput extends com.dsce.base.sys.input.InputExecutor {

    boolean shift = false;

    public GameInput() {
        InputHandler.registerInputExecutor(this);
        MouseListener.registerInputExecutor(this);
    }

    @Override
    public void keyPressedExe(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift = true;
        }
        if (shift) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                //ProjectCreateTab.addDummyProject();
            }
        }

    }

    @Override
    public void keyReleasedExe(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift = true;
        }
    }

    @Override
    public void scrollUpExe() {
        ManagementTab.scrollUp();
    }

    @Override
    public void scrollDownExe() {
        ManagementTab.scrollUDown();
    }
}
