package com.dsce.base.sys.file;

import com.dsce.base.core.Game;
import com.dsce.base.core.contents.project.Project;
import com.dsce.base.core.contents.project.ProjectType;
import com.dsce.base.core.contents.project.internal.Engine;
import com.dsce.base.core.contents.project.internal.Graphics;
import com.dsce.base.core.contents.project.internal.Lang;
import com.dsce.base.core.contents.staff.Staff;
import com.dsce.base.core.contents.team.Team;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class FileManager {
    private static final String path = System.getProperty("user.home") + File.separator + ".dsce" + File.separator + "save" + File.separator + "save.Traits";

    public static void save() {
        Properties p = new Properties();
        ArrayList<Project> projects = Game.projects;

        p.setProperty("project_count", String.valueOf(projects.size()));
        p.setProperty("m", String.valueOf(Game.money));
        p.setProperty("a", String.valueOf(Game.ap));

        for (int i = 0; i < projects.size(); i++) {
            Project pj = projects.get(i);
            String prefix = "project" + i + "_";

            p.setProperty(prefix + "name", pj.getName()); // 필드 접근을 위해 Getter가 필요합니다.
            p.setProperty(prefix + "codeQuality", String.valueOf(pj.getCodeQuality()));
            p.setProperty(prefix + "graphics", String.valueOf(pj.getGraphics()));
            p.setProperty(prefix + "funny", String.valueOf(pj.getFunny()));
            p.setProperty(prefix + "addictive", String.valueOf(pj.getAddictive()));
            p.setProperty(prefix + "released", String.valueOf(pj.isReleased()));
            p.setProperty(prefix + "price", String.valueOf(pj.getPrice()));
            p.setProperty(prefix + "optimization", String.valueOf(pj.getOptimization()));
            p.setProperty(prefix + "stability", String.valueOf(pj.getStability()));
            p.setProperty(prefix + "fileSize", String.valueOf(pj.getFileSize()));
            p.setProperty(prefix + "scale", String.valueOf(pj.getScale()));

            // Enum 타입 저장 (null 체크 포함)
            if (pj.getProjectType() != null) p.setProperty(prefix + "projectType", pj.getProjectType().name());
            if (pj.getProjectEngineType() != null) p.setProperty(prefix + "engineType", pj.getProjectEngineType().name());
            if (pj.getProjectGraphicsType() != null) p.setProperty(prefix + "graphicsType", pj.getProjectGraphicsType().name());
            if (pj.getProjectLangType() != null) p.setProperty(prefix + "langType", pj.getProjectLangType().name());
        }

        ArrayList<Staff> staffs = Game.staffs;
        p.setProperty("staff_count", String.valueOf(staffs.size()));

        for (int i = 0; i < staffs.size(); i++) {
            Staff sf = staffs.get(i);
            String prefix = "staff" + i + "_";
            // Staff 클래스 내부에 정의된 저장 메서드 호출
            sf.saveToProperties(p, prefix);
        }

        ArrayList<Team> teams = Game.teams;
        p.setProperty("team_count", String.valueOf(staffs.size()));

        for (int i = 0; i < staffs.size(); i++) {
            Team t = teams.get(i);
            String prefix = "team" + i + "_";
            p.setProperty(prefix+"name", String.valueOf(t.getName()));
        }


        // 폴더 생성 확인
        File file = new File(path);
        file.getParentFile().mkdirs();

        try (FileOutputStream out = new FileOutputStream(path)) {
            p.store(out, "User Save Data - Projects");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "저장 실패: " + e.getMessage());
        }
    }

    public static void load() {
        Properties p = new Properties();
        File file = new File(path);

        if (!file.exists()) return;

        try (FileInputStream in = new FileInputStream(path)) {
            p.load(in);

            Game.money = Integer.parseInt(p.getProperty("m", "0"));
            Game.ap = Integer.parseInt(p.getProperty("a", "0"));

            int count = Integer.parseInt(p.getProperty("project_count", "0"));
            ArrayList<Project> loadedProjects = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                Project pj = new Project();
                String prefix = "project" + i + "_";

                pj.registerName(p.getProperty(prefix + "name", "broken name"));
                pj.registerCodeQuality(Float.parseFloat(p.getProperty(prefix + "codeQuality", "0.0")));
                pj.registerGraphics(Float.parseFloat(p.getProperty(prefix + "graphics", "0.0")));
                pj.registerFunny(Float.parseFloat(p.getProperty(prefix + "funny", "0.0")));
                pj.registerAddictive(Float.parseFloat(p.getProperty(prefix + "addictive", "0.0")));
                pj.registerReleased(Boolean.parseBoolean(p.getProperty(prefix + "released", "false")));
                pj.registerPrice(Float.parseFloat(p.getProperty(prefix + "price", "0.0")));
                pj.registerOptimization(Float.parseFloat(p.getProperty(prefix + "optimization", "0.0")));
                pj.registerStability(Float.parseFloat(p.getProperty(prefix + "stability", "0.0")));
                pj.registerFileSize(Integer.parseInt(p.getProperty(prefix + "fileSize", "0")));
                pj.registerScale(Integer.parseInt(p.getProperty(prefix + "scale", "0")));

                // Enum 복구
                try {
                    String pt = p.getProperty(prefix + "projectType");
                    if (pt != null) pj.registerProjectType(ProjectType.type.valueOf(pt));

                    String et = p.getProperty(prefix + "engineType");
                    if (et != null) pj.registerProjectEngineType(Engine.type.valueOf(et));

                    String gt = p.getProperty(prefix + "graphicsType");
                    if (gt != null) pj.registerProjectGraphicsType(Graphics.type.valueOf(gt));

                    String lt = p.getProperty(prefix + "langType");
                    if (lt != null) pj.registerProjectLangType(Lang.type.valueOf(lt));
                } catch (IllegalArgumentException e) {
                    // Enum 값 변경 시 예외 처리
                    System.err.println("Fail");
                }

                loadedProjects.add(pj);
            }

            ArrayList<Staff> staffs = Game.staffs;
            p.getProperty("staff_count", String.valueOf(staffs.size()));

            int sCount = Integer.parseInt(p.getProperty("staff_count", "0"));
            ArrayList<Staff> loadedStaffs = new ArrayList<>();

            for (int i = 0; i < sCount; i++) {
                Staff sf = new Staff();
                String prefix = "staff" + i + "_";
                // Staff 클래스 내부에 정의된 로드 메서드 호출
                sf.loadFromProperties(p, prefix);
                loadedStaffs.add(sf);
            }
            Game.staffs = loadedStaffs;

            int tCount = Integer.parseInt(p.getProperty("staff_count", "0"));
            ArrayList<Team> loadedTeams = new ArrayList<>();

            for (int i = 0; i < tCount; i++) {
                Team t = new Team();
                String prefix = "team" + i + "_";
                t.registerName(p.getProperty(String.valueOf(prefix+"name")));
            }
            Game.teams = loadedTeams;

            Game.projects = loadedProjects;

        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "로드 실패: " + e.getMessage());
        }
    }
}