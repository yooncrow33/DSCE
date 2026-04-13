package com.dsce.base.core.graphics.popup;

import com.dsce.base.core.Game;
import com.dsce.base.core.contents.staff.Staff;
import com.dsce.base.core.contents.team.Team;
import com.dsce.base.core.graphics.popup.internal.Popup;
import com.dsce.base.core.window.tabs.StaffTab;

public class DeleteTeamPopup extends Popup {
    @Override
    public void exe() {
        if (Game.teams.get(StaffTab.selectedTeamIndex).getName().equals("Basic")) {
            return;
        }
        for (int i = 0; i<Game.teams.size(); i++) {
            if (Game.teams.get(i).getName().equals(Game.teams.get(StaffTab.selectedTeamIndex).getName())) {
                for (Staff s : Game.teams.get(StaffTab.selectedTeamIndex).staffs) {
                    s.registerTeam("Basic");
                    s.loadTeam();
                }
                Game.teams.remove(i);
                StaffTab.selectedTeamIndex = 0;
            }
        }
    }

    public DeleteTeamPopup() {
        super("Are You Sure?" ,"Delete this Team","deleteTeamPopup");
    }
}
