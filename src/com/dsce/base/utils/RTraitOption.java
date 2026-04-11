package com.dsce.base.utils;

import com.dsce.base.core.contents.staff.inetnal.TraitOption;

import java.util.ArrayList;
import java.util.Random;

public class RTraitOption {
    private static Random ran = new Random();
    private static ArrayList<TraitOption> traitOptions = new ArrayList<>();

    static {
        TraitOption strongMan = new TraitOption();
        strongMan.setName("StrongMan");
        strongMan.setFastLearn(0.2f);
        strongMan.setHardWork(0.6f);
        traitOptions.add(strongMan);
    }

    public static TraitOption getRandomTrait() {
        int i = ran.nextInt(traitOptions.size());
        return traitOptions.get(i);
    }

}
