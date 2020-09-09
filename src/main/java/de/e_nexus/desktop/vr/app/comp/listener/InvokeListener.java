package de.e_nexus.desktop.vr.app.comp.listener;

import de.e_nexus.desktop.vr.app.comp.model.listener.VRListener;

public interface InvokeListener<OLD, L extends VRListener> {
	void invoke(L listener, OLD oldValue);
}
