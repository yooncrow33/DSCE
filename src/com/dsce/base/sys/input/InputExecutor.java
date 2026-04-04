package com.dsce.base.sys.input;

import java.awt.event.KeyEvent;
import java.security.Key;

public abstract class InputExecutor {
    public abstract void keyPressedExe(KeyEvent e);
    public abstract void keyReleasedExe(KeyEvent e);
    public abstract void scrollUpExe();
    public abstract void scrollDownExe();
    public abstract void mouseClickExe();

}
