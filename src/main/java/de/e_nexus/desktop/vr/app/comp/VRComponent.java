package de.e_nexus.desktop.vr.app.comp;

import de.e_nexus.desktop.vr.app.comp.model.VRComponentModel;
import de.e_nexus.desktop.vr.app.comp.ui.MeshUI;
import de.e_nexus.desktop.vr.app.input.focus.VRModelHolder;

public class VRComponent<M extends VRComponentModel, UI extends MeshUI> implements VRModelHolder<M> {
	private UI ui;
	private M model;

	public VRComponent(M model) {
		this.model = model;
	}

	public void setUI(UI ui) {
		this.ui = ui;
	}

	public UI getUI() {
		return ui;
	}

	@Override
	public M getModel() {
		return model;
	}
}
