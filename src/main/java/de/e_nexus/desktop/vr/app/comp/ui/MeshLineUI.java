package de.e_nexus.desktop.vr.app.comp.ui;

import de.e_nexus.desktop.vr.app.comp.VRInputLine;
import de.e_nexus.desktop.vr.app.comp.model.listener.ChangeLineListener;
import de.e_nexus.vr.server.VRServer;
import de.e_nexus.vr.server.mesh.Mesh;

public class MeshLineUI extends MeshUI implements ChangeLineListener {

	private VRInputLine vrInputText;

	public MeshLineUI(Mesh<?> mesh, VRServer server, VRInputLine vrInputText) {
		super(mesh, server);
		this.vrInputText = vrInputText;
		vrInputText.getModel().addChangeLineListener(this);
	}

	@Override
	public void notifyBeforeLineChange(String oldLine, String newLine) {

	}

	@Override
	public void notifyLineChanged(String oldLine, String newLine) {

	}

	public VRInputLine getVRInputText() {
		return vrInputText;
	}
}
