package de.e_nexus.desktop.vr.app.comp;

import de.e_nexus.desktop.vr.app.comp.model.VRButtonModel;
import de.e_nexus.desktop.vr.app.comp.ui.MeshUI;
import de.e_nexus.desktop.vr.app.comp.ui.VRUIManagerAuthority;

public class VRButton extends AbstractVRButton<VRButtonModel, MeshUI> {

	public VRButton(VRButtonModel model) {
		super(model);
		init();
	}

	private void init() {
		updateUI();
	}

	private void updateUI() {
		if (this.getUI() == null) {
			setUI(VRUIManagerAuthority.getDefaultManager().constructMeshUI(this));
		}
	}
}
