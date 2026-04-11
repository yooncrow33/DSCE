package com.dsce.base.core.contents.staff.inetnal;

public class Trait {
    String name;
    float fastLearn;
    float hardWork;

    public Trait() {}
    public Trait(TraitOption option) {
        this.name = option.name;
        this.fastLearn = option.fastLearn;
        this.hardWork = option.hardWork;
    }

    public void registerName(String name) {
        this.name = name;
    }

    public void registerFastLearn(float fastLearn) {
        this.fastLearn = fastLearn;
    }

    public String getName() {
        return name;
    }

    public float getFastLearn() {
        return fastLearn;
    }

    public float getHardWork() {
        return hardWork;
    }

    public void registerHardWork(float hardWork) {
        this.hardWork = hardWork;
    }
}
