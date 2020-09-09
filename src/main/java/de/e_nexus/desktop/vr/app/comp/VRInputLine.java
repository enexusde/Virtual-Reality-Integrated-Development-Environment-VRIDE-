package de.e_nexus.desktop.vr.app.comp;

import de.e_nexus.desktop.vr.app.comp.model.DefaultInputLineModel;
import de.e_nexus.desktop.vr.app.comp.ui.MeshUI;
import de.e_nexus.desktop.vr.app.comp.ui.VRUIManagerAuthority;
import de.e_nexus.desktop.vr.app.input.focus.FocusableVRComponent;

public class VRInputLine extends VRComponent<DefaultInputLineModel, MeshUI>
		implements FocusableVRComponent<DefaultInputLineModel> {

	public VRInputLine(String text) {
		super(new DefaultInputLineModel());
		getModel().setText(text);
		init();
		getModel().addChangeLineListener(new LineChangeVRModelUpdater(this));
	}

	private void init() {
		updateUI();
	}

	private void updateUI() {
		if (this.getUI() == null) {
			setUI(VRUIManagerAuthority.getDefaultManager().constructLineUI(this));
		}
	}
}
