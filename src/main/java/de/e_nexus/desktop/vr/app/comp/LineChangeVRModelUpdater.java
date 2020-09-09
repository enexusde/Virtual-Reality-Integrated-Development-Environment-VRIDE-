package de.e_nexus.desktop.vr.app.comp;

import de.e_nexus.desktop.vr.app.comp.model.listener.ChangeLineListener;
import de.e_nexus.desktop.vr.app.comp.ui.DefaultUIManager;
import de.e_nexus.desktop.vr.app.comp.ui.MeshUI;
import de.e_nexus.desktop.vr.app.comp.ui.VRUIManagerAuthority;

public class LineChangeVRModelUpdater implements ChangeLineListener {

	private VRInputLine vrInputLine;

	public LineChangeVRModelUpdater(VRInputLine vrInputLine) {
		this.vrInputLine = vrInputLine;
	}

	@Override
	public void notifyBeforeLineChange(String oldLine, String newLine) {
	}

	@Override
	public void notifyLineChanged(String oldLine, String newLine) {
		MeshUI oldUi = vrInputLine.getUI();
		DefaultUIManager constructor = VRUIManagerAuthority.getDefaultManager();
		MeshUI meshUI = constructor.constructLineUI(vrInputLine);
		oldUi.update(meshUI.getMesh());
	}

}
