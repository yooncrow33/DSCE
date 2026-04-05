package com.dsce.base.sys.mouse;

public class Mouse {
    private static Mouse instance;
    private int mx, my;

    private Mouse() {} // 외부 생성 방지

    public static Mouse g() {
        if (instance == null) instance = new Mouse();
        return instance;
    }

    //@Deprecated(forRemoval = true)
    public void update(int x, int y) { this.mx = x; this.my = y; }

    public int x() { return mx; }
    public int y() { return my; }
}
