package de.e_nexus.desktop.vr.app.input.focus;

import de.e_nexus.desktop.vr.app.comp.model.VRComponentModel;

public interface VRModelHolder<M extends VRComponentModel> {
	M getModel();
}
