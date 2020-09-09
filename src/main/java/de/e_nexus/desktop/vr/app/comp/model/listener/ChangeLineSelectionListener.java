package de.e_nexus.desktop.vr.app.comp.model.listener;

public interface ChangeLineSelectionListener extends VRListener {

	Object notifyLineSelectionChanged(int oldSelection, int newSelection);

	Object notifyBeforeLineSelectionChange(int oldSelection, int newSelection);

}
