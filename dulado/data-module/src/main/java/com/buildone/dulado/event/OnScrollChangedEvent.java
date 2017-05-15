package com.buildone.dulado.event;

/**
 * Created by Alessandro Pryds on 10/05/2017.
 */

public class OnScrollChangedEvent {
    private boolean isScrolling;

    public OnScrollChangedEvent(boolean isScrolling) {
        this.isScrolling = isScrolling;
    }

    public boolean isScrolling() {
        return isScrolling;
    }
}
