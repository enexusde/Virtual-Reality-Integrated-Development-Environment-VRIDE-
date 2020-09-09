package de.e_nexus.desktop.vr.app.comp.model.listener;

public interface LineCarretChangeListener extends VRListener {

	void notifyBeforeLineCarretChange(int oldPosition, int newCarretPosition);

	void notifyLineCarretChanged(int oldPosition, int newCarretPosition);

}
