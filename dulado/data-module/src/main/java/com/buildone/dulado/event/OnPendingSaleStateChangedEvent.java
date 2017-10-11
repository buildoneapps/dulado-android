package com.buildone.dulado.event;

import com.buildone.dulado.model.PendingSaleObject;

/**
 * Created by Alessandro Pryds on 08/10/2017.
 */

public class OnPendingSaleStateChangedEvent {
    private final PendingSaleObject pendingSale;

    public OnPendingSaleStateChangedEvent(PendingSaleObject pendingSale) {
        this.pendingSale = pendingSale;
    }
}
