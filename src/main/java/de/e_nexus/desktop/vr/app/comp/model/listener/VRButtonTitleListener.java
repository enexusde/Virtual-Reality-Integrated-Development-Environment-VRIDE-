package de.e_nexus.desktop.vr.app.comp.model.listener;

public interface VRButtonTitleListener extends VRListener {

	void notifyTitleChanged(String oldTitle, String newTitle);

	default void notifyBeforeTitleChange(String oldTitle, String newTitle) {
	}

}
