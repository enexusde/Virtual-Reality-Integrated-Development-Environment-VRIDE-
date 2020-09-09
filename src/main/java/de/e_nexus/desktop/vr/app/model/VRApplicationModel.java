package de.e_nexus.desktop.vr.app.model;

import java.util.Set;

import de.e_nexus.desktop.vr.app.comp.model.VRComponentModel;
import de.e_nexus.desktop.vr.app.model.listener.VRApplicationComponentListener;

public interface VRApplicationModel extends VRComponentModel {
	Set<VRApplicationComponentListener> getApplicationComponentListeners();

	void addApplicationComponentListener(VRApplicationComponentListener listener);

	boolean removeApplicationComponentListener(VRApplicationComponentListener listener);
}
