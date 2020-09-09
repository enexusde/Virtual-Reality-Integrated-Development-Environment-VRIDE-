package de.e_nexus.desktop.vr.app.comp.model.listener;

public interface VRButtonSelectionListener extends VRListener {
	default void notifyBeforeSelectionChange(boolean oldValue, boolean newValue) {
	}

	void notifySelectionChanged(boolean oldValue, boolean newValue);
}
