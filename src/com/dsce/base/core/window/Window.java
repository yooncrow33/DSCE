package com.dsce.base.core.window;

import com.dsce.base.core.contents.staff.Staff;
import com.dsce.base.core.window.tabs.ManagementTab;
import com.dsce.base.core.window.tabs.ProjectCreateTab;
import com.dsce.base.core.window.tabs.StaffTab;
import com.dsce.base.utils.Utils;

import java.awt.*;

public class Window {
    final int width = 1920;
    final int height = 880;
    public int windowTabIndex = 0;

    public ProjectCreateTab projectCreateTab = new ProjectCreateTab();
    public ManagementTab managementTab = new ManagementTab();
    public StaffTab staffsTab = new StaffTab();

    public void render(Graphics g) {
        switch (windowTabIndex) {
            case 0:
                //new project
                projectCreateTab.render(g);
                break;
            case 1:
                //project management
                managementTab.render(g);
                break;
            case 2:
                //team

                break;
            case 3:
                //staff
                staffsTab.render(g);
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

    public void init() {
        switch (windowTabIndex) {
            case 0:
                //new project
                projectCreateTab.init();
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
