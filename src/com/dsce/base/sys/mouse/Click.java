package com.dsce.base.sys.mouse;

import java.util.ArrayList;

public class Click {
    private static Click instance;
    private ArrayList<IClickEvent> clickEvents = new ArrayList<>();

    public static Click g() {
        if (instance == null) instance = new Click();
        return instance;
    }

    private Click() {}

    public void clickEvent() {
        for (IClickEvent iClickEvent : clickEvents) {
            iClickEvent.clickEvent();
        }
    }

    public void registerClickEventObject(IClickEvent iClickEvent) {
        if (iClickEvent != null && !clickEvents.contains(iClickEvent)) {
            clickEvents.add(iClickEvent);
        }
    }
    public void unregisterClickEventObject(IClickEvent iClickEvent) {
        clickEvents.remove(iClickEvent);
    }

}
