package de.e_nexus.desktop.vr.app;

import de.e_nexus.desktop.vr.app.comp.model.VRComponentModel;

public interface ModelHolder<A extends VRComponentModel> {
	A getModel();
}
