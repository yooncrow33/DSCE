package com.dsce.base.core.contents.staff.inetnal;

import com.dsce.base.core.contents.staff.Staff;
import com.dsce.base.utils.RTraitOption;
import com.dsce.base.utils.Utils;

public class RandomStaff extends Staff {

   public int scan = 0;

   public boolean isGetTrait = Math.random() >= 0.5;

    public RandomStaff() {
        registerName(Utils.getRandomName());
        registerXp((float)Math.random());

        // --- Language Levels ---
        registercLevel((float)Math.random());
        registerCppLevel((float)Math.random());
        registerRustLevel((float)Math.random());
        registerJavaLevel((float)Math.random());
        registerKotlinLevel((float)Math.random());
        registerCsLevel((float)Math.random());
        registerPyLevel((float)Math.random());
        registerJsLevel((float)Math.random());

        // --- Engine Levels ---
        registerUnityLevel((float)Math.random());
        registerGodotLevel((float)Math.random());
        registerUnrealLevel((float)Math.random());
        registerLwjglLevel((float)Math.random());
        registerLibgdxLevel((float)Math.random());

        // --- Graphics API Levels ---
        registerVulkanLevel((float)Math.random());
        registerDirectxLevel((float)Math.random());
        registerOpenglLevel((float)Math.random());

        registerLaborCost(300); // temp value

        if (isGetTrait) {
            super.Traits.add(new Trait(RTraitOption.getRandomTrait()));
        }

        updateStack();
    }
}
