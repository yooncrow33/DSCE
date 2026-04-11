package com.dsce.base.utils;

import com.dsce.base.utils.internal.NameData;

import java.util.Random;

import static com.dsce.base.utils.internal.NameData.FOREIGN_NAMES;

public class Utils {
    private static Random ran = new Random();

    public static String getReportMessage() {
        return "please report to me about this bug. github: Crunch-Life";
    }

    public static String getRandomName() {
        return FOREIGN_NAMES[ran.nextInt(FOREIGN_NAMES.length)];
    }


}
