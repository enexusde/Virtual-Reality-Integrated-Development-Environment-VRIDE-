package de.e_nexus.desktop.vr.app.comp.model.listener;

public interface ChangeLineListener extends VRListener {

	void notifyBeforeLineChange(String oldLine, String newLine);

	void notifyLineChanged(String oldLine, String newLine);

}
