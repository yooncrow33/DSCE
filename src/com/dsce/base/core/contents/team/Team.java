package com.dsce.base.core.contents.team;

import com.dsce.base.core.contents.staff.Staff;
import com.dsce.base.core.graphics.overlay.internal.ArrList;

import java.util.ArrayList;

public class Team implements ArrList {
    public ArrayList<Staff> staffs = new ArrayList<>();
    String name;

    public void registerName(String name) {
        this.name = name;
    }

    public void registerStaffs(ArrayList<Staff> staffs) {
        this.staffs = staffs;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getLabel() {
        return getName();
    }

    @Override
    public String getValue() {
        return "scale : " + staffs.size();
    }
}
