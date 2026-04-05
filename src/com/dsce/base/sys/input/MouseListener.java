package com.dsce.base.sys.input;


import com.dsce.base.sys.ViewMetrics;
import com.dsce.base.sys.mouse.Click;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseListener extends MouseAdapter implements MouseWheelListener {

    InputExecutor inputExecutor = null;

    public void registerInputExecutor(InputExecutor inputExecutor) {
        this.inputExecutor = inputExecutor;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation(); // 음수: 위로, 양수: 아래로
        if (notches < 0) {
            //up scroll
            if (inputExecutor != null) { inputExecutor.scrollUpExe(); }
        } else {
            //down scroll
            if (inputExecutor != null) { inputExecutor.scrollDownExe(); }
        }
    }

    public MouseListener( ViewMetrics viewMetrics) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (inputExecutor != null) { inputExecutor.mouseClickExe(); }
        Click.g().clickEvent();
    }
}
