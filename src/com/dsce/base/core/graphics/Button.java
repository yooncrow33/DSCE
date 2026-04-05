package com.dsce.base.core.graphics;

import com.dsce.base.sys.mouse.Mouse;

public class Button {
    final int x,y,w,h;

    public Button(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public boolean isOnMouse() {
        return (Mouse.g().x() >= x && Mouse.g().x() <= x + w &&
                Mouse.g().y() >= y && Mouse.g().y() <= y + h);
    }
}
