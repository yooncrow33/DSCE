package com.dsce.base.core.contents.project;

import com.dsce.base.core.contents.project.internal.Engine;
import com.dsce.base.core.contents.project.internal.Graphics;
import com.dsce.base.core.contents.project.internal.Lang;

public class Project {
    String name = "if you can see this word, it's a bug. please report to me about this bug. github: Crunch-Life";
    float codeQuality = 0;
    float graphics = 0.0f;
    float funny = 0.0f;
    float addictive = 0.0f;
    boolean released = false;
    float price = 0.0f; //dollar
    float optimization = 0.0f;
    float stability = 0.0f;
    int fileSize = 0; //mb
    int scale = 0;
    ProjectType.type projectType = null;
    Engine.type projectEngineType = null;
    Graphics.type projectGraphicsType = null;
    Lang.type projectLangType = null;

    public String getName() { return name; }
    public float getCodeQuality() { return codeQuality; }
    public float getGraphics() { return graphics; }
    public float getFunny() { return funny; }
    public float getAddictive() { return addictive; }
    public boolean isReleased() { return released; }
    public float getPrice() { return price; }
    public float getOptimization() { return optimization; }
    public float getStability() { return stability; }
    public int getFileSize() { return fileSize; }
    public int getScale() { return scale; }
    public ProjectType.type getProjectType() { return projectType; }
    public Engine.type getProjectEngineType() { return projectEngineType; }
    public Graphics.type getProjectGraphicsType() { return projectGraphicsType; }
    public Lang.type getProjectLangType() { return projectLangType; }

    public void registerName(String name) {
        if (name == null) throw new IllegalArgumentException("Project Name cannot be null!");
        this.name = name;
    }

    public void registerProjectType(ProjectType.type projectType) {
        if (projectType == null) throw new IllegalArgumentException("ProjectType cannot be null!");
        this.projectType = projectType;
    }

    public void registerProjectEngineType(Engine.type projectEngineType) {
        if (projectEngineType == null) throw new IllegalArgumentException("Engine Type cannot be null!");
        this.projectEngineType = projectEngineType;
    }

    public void registerProjectGraphicsType(com.dsce.base.core.contents.project.internal.Graphics.type projectGraphicsType) {
        if (projectGraphicsType == null) throw new IllegalArgumentException("Graphics Type cannot be null!");
        this.projectGraphicsType = projectGraphicsType;
    }

    public void registerProjectLangType(Lang.type projectLangType) {
        if (projectLangType == null) throw new IllegalArgumentException("Language Type cannot be null!");
        this.projectLangType = projectLangType;
    }

    public void registerCodeQuality(float codeQuality) { this.codeQuality = codeQuality; }
    public void registerGraphics(float graphics) { this.graphics = graphics; }
    public void registerFunny(float funny) { this.funny = funny; }
    public void registerAddictive(float addictive) { this.addictive = addictive; }
    public void registerReleased(boolean released) { this.released = released; }
    public void registerPrice(float price) { this.price = price; }
    public void registerOptimization(float optimization) { this.optimization = optimization; }
    public void registerStability(float stability) { this.stability = stability; }
    public void registerFileSize(int fileSize) { this.fileSize = fileSize; }
    public void registerScale(int scale) { this.scale = scale; }
}
