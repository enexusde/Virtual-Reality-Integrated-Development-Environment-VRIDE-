package de.e_nexus.desktop.vr.app.comp;

import de.e_nexus.desktop.vr.app.comp.model.VRComponentModel;
import de.e_nexus.desktop.vr.app.comp.ui.MeshUI;

public abstract class AbstractVRButton<M extends VRComponentModel, UI extends MeshUI> extends VRComponent<M, UI> {

	public AbstractVRButton(M model) {
		super(model);
	}

}
