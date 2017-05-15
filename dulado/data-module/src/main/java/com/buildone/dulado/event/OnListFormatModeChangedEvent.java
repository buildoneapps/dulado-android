package com.buildone.dulado.event;

/**
 * Created by Alessandro Pryds on 08/05/2017.
 */

public class OnListFormatModeChangedEvent {

    private int listFormat;

    public OnListFormatModeChangedEvent(int listFormat) {
        this.listFormat = listFormat;
    }

    public int getListFormat() {
        return listFormat;
    }
}
