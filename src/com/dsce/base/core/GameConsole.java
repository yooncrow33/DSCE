package com.dsce.base.core;

import com.dsce.base.sys.Console;
import com.dsce.base.sys.ConsoleExecutor;

import java.util.List;

public class GameConsole extends ConsoleExecutor {

    final Game game;

    public GameConsole(Game game) {
        this.game = game;
    }

    @Override
    public void exe(List<String> args) {
        String cmd = args.get(0);
        if (cmd.equals("set")) {
            setterExecutor(args);
        }

    }

    private void setterExecutor(List<String> args) {
        String type = args.get(1);
        String target = args.get(2);
        String valueStr = args.get(3);

        // valueStr.toIntOrNull() ?: 0 대응
        int value;
        try {
            value = Integer.parseInt(valueStr);
        } catch (NumberFormatException e) {
            value = 0;
        }

        if (type.equals("val")) {
            if (target.equals("a")) {
                Game.ap = value;
            } else if (target.equals("m")) {
                Game.money = value;
            }
        } else if (type.equals("bool")) {

        }
    }
}
