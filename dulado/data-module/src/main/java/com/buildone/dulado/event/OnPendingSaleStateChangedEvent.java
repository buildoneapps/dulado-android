package com.buildone.dulado.event;

import com.buildone.dulado.model.PendingSaleObject;

/**
 * Created by Alessandro Pryds on 08/10/2017.
 */

public class OnPendingSaleStateChangedEvent {
    private final PendingSaleObject pendingSale;
    private boolean accepted;

    public OnPendingSaleStateChangedEvent(PendingSaleObject pendingSale, boolean accepted) {
        this.pendingSale = pendingSale;
        this.accepted = accepted;
    }

    public PendingSaleObject getPendingSale() {
        return pendingSale;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
