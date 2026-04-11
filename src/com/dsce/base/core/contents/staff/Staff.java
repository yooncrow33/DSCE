package com.dsce.base.core.contents.staff;

import com.dsce.base.core.contents.project.internal.Engine;
import com.dsce.base.core.contents.project.internal.Graphics;
import com.dsce.base.core.contents.project.internal.Lang;
import com.dsce.base.core.contents.staff.inetnal.Trait;
import com.dsce.base.core.graphics.overlay.internal.ArrList;

import java.util.ArrayList;
import java.util.Properties;

public class Staff implements ArrList {
    float xp;
    String name;

    int laborCost;

    float cLevel;
    float cppLevel;
    float rustLevel;
    float javaLevel;
    float kotlinLevel;
    float csLevel;
    float pyLevel;
    float jsLevel;

    float unityLevel;
    float godotLevel;
    float unrealLevel;
    float lwjglLevel;
    float libgdxLevel;

    float vulkanLevel;
    float directxLevel;
    float openglLevel;

    Engine.type engineStack;
    Lang.type langStack;
    Graphics.type graphicsStack;

    String team;

    public ArrayList<Trait> Traits = new ArrayList<>();

    public Staff() {
        updateStack();
    }

    public void registercLevel(float cLevel) { this.cLevel = cLevel; }
    public void registerCppLevel(float cppLevel) { this.cppLevel = cppLevel; }
    public void registerRustLevel(float rustLevel) { this.rustLevel = rustLevel; }
    public void registerJavaLevel(float javaLevel) { this.javaLevel = javaLevel; }
    public void registerKotlinLevel(float kotlinLevel) { this.kotlinLevel = kotlinLevel; }
    public void registerCsLevel(float csLevel) { this.csLevel = csLevel; }
    public void registerPyLevel(float pyLevel) { this.pyLevel = pyLevel; }
    public void registerJsLevel(float jsLevel) { this.jsLevel = jsLevel; }
    public void registerUnityLevel(float unityLevel) { this.unityLevel = unityLevel; }
    public void registerGodotLevel(float godotLevel) { this.godotLevel = godotLevel; }
    public void registerUnrealLevel(float unrealLevel) { this.unrealLevel = unrealLevel; }
    public void registerLwjglLevel(float lwjglLevel) { this.lwjglLevel = lwjglLevel; }
    public void registerLibgdxLevel(float libgdxLevel) { this.libgdxLevel = libgdxLevel; }
    public void registerVulkanLevel(float vulkanLevel) { this.vulkanLevel = vulkanLevel; }
    public void registerDirectxLevel(float directxLevel) { this.directxLevel = directxLevel; }
    public void registerOpenglLevel(float openglLevel) { this.openglLevel = openglLevel; }
    public void registerXp(float xp) { this.xp = xp; }
    public void registerName(String name) { this.name = name; }
    public float getXp() { return xp; }
    public String getName() { return name; }
    public String getTeam() {return team;}
    public void registerTeam(String team) {this.team = team;}

    public void registerLaborCost(int laborCost) {
        this.laborCost = laborCost;
    }

    public int getLaborCost() {
        return laborCost;
    }

    public float getcLevel() { return cLevel; }
    public float getCppLevel() { return cppLevel; }
    public float getRustLevel() { return rustLevel; }
    public float getJavaLevel() { return javaLevel; }
    public float getKotlinLevel() { return kotlinLevel; }
    public float getCsLevel() { return csLevel; }
    public float getPyLevel() { return pyLevel; }
    public float getJsLevel() { return jsLevel; }
    public float getUnityLevel() { return unityLevel; }
    public float getGodotLevel() { return godotLevel; }
    public float getUnrealLevel() { return unrealLevel; }
    public float getLwjglLevel() { return lwjglLevel; }
    public float getLibgdxLevel() { return libgdxLevel; }
    public float getVulkanLevel() { return vulkanLevel; }
    public float getDirectxLevel() { return directxLevel; }

    public ArrayList<Trait> getTraits() {
        return Traits;
    }

    public float getOpenglLevel() { return openglLevel; }
    public Engine.type getEngineStack() { return engineStack; }
    public Lang.type getLangStack() { return langStack; }
    public Graphics.type getGraphicsStack() { return graphicsStack; }

    public void updateStack() {
        // 1. Language Stack 결정
        float maxLang = -1.0f;

        if (cLevel > maxLang) { maxLang = cLevel; this.langStack = Lang.type.c; }
        if (cppLevel > maxLang) { maxLang = cppLevel; this.langStack = Lang.type.cpp; }
        if (rustLevel > maxLang) { maxLang = rustLevel; this.langStack = Lang.type.rust; }
        if (javaLevel > maxLang) { maxLang = javaLevel; this.langStack = Lang.type.java; }
        if (kotlinLevel > maxLang) { maxLang = kotlinLevel; this.langStack = Lang.type.kotlin; }
        if (csLevel > maxLang) { maxLang = csLevel; this.langStack = Lang.type.cs; }
        if (pyLevel > maxLang) { maxLang = pyLevel; this.langStack = Lang.type.py; }
        if (jsLevel > maxLang) { maxLang = jsLevel; this.langStack = Lang.type.js; }

        // 2. Engine Stack 결정
        float maxEngine = -1.0f;

        if (unityLevel > maxEngine) { maxEngine = unityLevel; this.engineStack = Engine.type.unity; }
        if (godotLevel > maxEngine) { maxEngine = godotLevel; this.engineStack = Engine.type.godot; }
        if (unrealLevel > maxEngine) { maxEngine = unrealLevel; this.engineStack = Engine.type.unreal; }
        if (lwjglLevel > maxEngine) { maxEngine = lwjglLevel; this.engineStack = Engine.type.lwjgl; }
        if (libgdxLevel > maxEngine) { maxEngine = libgdxLevel; this.engineStack = Engine.type.libgdx; }

        // 3. Graphics Stack 결정
        float maxGraphics = -1.0f;

        if (vulkanLevel > maxGraphics) { maxGraphics = vulkanLevel; this.graphicsStack = Graphics.type.vulkan; }
        if (directxLevel > maxGraphics) { maxGraphics = directxLevel; this.graphicsStack = Graphics.type.directx; }
        if (openglLevel > maxGraphics) { maxGraphics = openglLevel; this.graphicsStack = Graphics.type.opengl; }
    }

    public void saveToProperties(Properties p, String prefix) {
        p.setProperty(prefix + "name", name);
        p.setProperty(prefix + "xp", String.valueOf(xp));
        p.setProperty(prefix+"team", team);

        // 언어 레벨
        p.setProperty(prefix + "cLevel", String.valueOf(cLevel));
        p.setProperty(prefix + "cppLevel", String.valueOf(cppLevel));
        p.setProperty(prefix + "rustLevel", String.valueOf(rustLevel));
        p.setProperty(prefix + "javaLevel", String.valueOf(javaLevel));
        p.setProperty(prefix + "kotlinLevel", String.valueOf(kotlinLevel));
        p.setProperty(prefix + "csLevel", String.valueOf(csLevel));
        p.setProperty(prefix + "pyLevel", String.valueOf(pyLevel));
        p.setProperty(prefix + "jsLevel", String.valueOf(jsLevel));

        // 엔진 레벨
        p.setProperty(prefix + "unityLevel", String.valueOf(unityLevel));
        p.setProperty(prefix + "godotLevel", String.valueOf(godotLevel));
        p.setProperty(prefix + "unrealLevel", String.valueOf(unrealLevel));
        p.setProperty(prefix + "lwjglLevel", String.valueOf(lwjglLevel));
        p.setProperty(prefix + "libgdxLevel", String.valueOf(libgdxLevel));

        // 그래픽스 레벨
        p.setProperty(prefix + "vulkanLevel", String.valueOf(vulkanLevel));
        p.setProperty(prefix + "directxLevel", String.valueOf(directxLevel));
        p.setProperty(prefix + "openglLevel", String.valueOf(openglLevel));

        // Property 리스트 저장
        p.setProperty(prefix + "property_count", String.valueOf(Traits.size()));
        for (int i = 0; i < Traits.size(); i++) {
            Trait prop = Traits.get(i);
            String propPrefix = prefix + "prop" + i + "_";
            p.setProperty(propPrefix + "name", prop.getName());
            p.setProperty(propPrefix + "fastLearn", String.valueOf(prop.getFastLearn()));
            p.setProperty(propPrefix + "hardWork", String.valueOf(prop.getHardWork()));
        }
    }

    public void loadFromProperties(Properties p, String prefix) {
        this.name = p.getProperty(prefix + "name", "Unknown Staff");
        this.xp = Float.parseFloat(p.getProperty(prefix + "xp", "0.0"));
        this.team = p.getProperty(prefix+"team","Basic");

        this.cLevel = Float.parseFloat(p.getProperty(prefix + "cLevel", "0.0"));
        this.cppLevel = Float.parseFloat(p.getProperty(prefix + "cppLevel", "0.0"));
        this.rustLevel = Float.parseFloat(p.getProperty(prefix + "rustLevel", "0.0"));
        this.javaLevel = Float.parseFloat(p.getProperty(prefix + "javaLevel", "0.0"));
        this.kotlinLevel = Float.parseFloat(p.getProperty(prefix + "kotlinLevel", "0.0"));
        this.csLevel = Float.parseFloat(p.getProperty(prefix + "csLevel", "0.0"));
        this.pyLevel = Float.parseFloat(p.getProperty(prefix + "pyLevel", "0.0"));
        this.jsLevel = Float.parseFloat(p.getProperty(prefix + "jsLevel", "0.0"));

        this.unityLevel = Float.parseFloat(p.getProperty(prefix + "unityLevel", "0.0"));
        this.godotLevel = Float.parseFloat(p.getProperty(prefix + "godotLevel", "0.0"));
        this.unrealLevel = Float.parseFloat(p.getProperty(prefix + "unrealLevel", "0.0"));
        this.lwjglLevel = Float.parseFloat(p.getProperty(prefix + "lwjglLevel", "0.0"));
        this.libgdxLevel = Float.parseFloat(p.getProperty(prefix + "libgdxLevel", "0.0"));

        this.vulkanLevel = Float.parseFloat(p.getProperty(prefix + "vulkanLevel", "0.0"));
        this.directxLevel = Float.parseFloat(p.getProperty(prefix + "directxLevel", "0.0"));
        this.openglLevel = Float.parseFloat(p.getProperty(prefix + "openglLevel", "0.0"));

        // Property 리스트 복구
        this.Traits.clear();
        int propCount = Integer.parseInt(p.getProperty(prefix + "property_count", "0"));
        for (int i = 0; i < propCount; i++) {
            String propPrefix = prefix + "prop" + i + "_";
            // Property 기본 생성자가 필요하거나, 값을 수동으로 채워야 합니다.
            Trait prop = new Trait();
            prop.registerName(p.getProperty(propPrefix + "name", "None"));
            prop.registerFastLearn(Float.parseFloat(p.getProperty(propPrefix + "fastLearn", "0.0")));
            prop.registerHardWork(Float.parseFloat(p.getProperty(propPrefix + "hardWork", "0.0")));
            this.Traits.add(prop);
        }

        updateStack(); // 로드 후 스택 갱신
    }

    @Override
    public String getLabel() {
        return getName();
    }

    @Override
    public String getValue() {
        return "";
    }
}
