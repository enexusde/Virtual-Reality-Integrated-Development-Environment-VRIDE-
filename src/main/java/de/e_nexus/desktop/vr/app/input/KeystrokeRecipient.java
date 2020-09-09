package de.e_nexus.desktop.vr.app.input;

import java.util.Set;

public interface KeystrokeRecipient {

	void notifyChangedDownpressing(Set<MutableKeyPressEvent> currentlyDownpressing);

}
