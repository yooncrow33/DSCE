package com.dsce.base.core;

import com.dsce.base.core.contents.project.internal.Graphics;
import com.dsce.base.utils.Utils;

public class Window {
    final int width = 1920;
    final int height = 880;
    int windowTabIndex = 0;

    public void render(Graphics g) {
        switch (windowTabIndex) {
            case 0:
                //new project

                break;
            case 1:
                //project management

                break;
            case 2:
                //docs

                break;
            case 3:
                //staff

                break;
            case 4:
                //break room

                break;
            case 5:
                //community

                break;
            default:
                System.err.println("Invalid Window Index: " + windowTabIndex);
                System.err.println("Window Index Error! " + Utils.getReportMessage());
                break;
        }
    }
}
