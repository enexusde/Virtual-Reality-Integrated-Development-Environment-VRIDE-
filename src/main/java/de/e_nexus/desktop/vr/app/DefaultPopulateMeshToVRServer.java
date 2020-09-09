package de.e_nexus.desktop.vr.app;

import de.e_nexus.desktop.vr.app.comp.VRComponent;
import de.e_nexus.desktop.vr.app.comp.ui.MeshUI;
import de.e_nexus.desktop.vr.app.model.listener.VRApplicationComponentListener;
import de.e_nexus.vr.server.mesh.Mesh;

public class DefaultPopulateMeshToVRServer implements VRApplicationComponentListener {

	private VirtualRealityApplication virtualRealityApplication;

	public DefaultPopulateMeshToVRServer(VirtualRealityApplication virtualRealityApplication) {
		this.virtualRealityApplication = virtualRealityApplication;
	}

	@Override
	public void beforeAdd(VRComponent<?, ?> component) {

	}

	@Override
	public void afterAdd(VRComponent<?, ?> component) {
		MeshUI ui = component.getUI();
		Mesh<?> mesh = ui.getMesh();
		virtualRealityApplication.getVRServer().addMesh(mesh);
	}

	@Override
	public void beforeRemove(VRComponent<?, ?> component) {
	}

	@Override
	public void afterRemove(VRComponent<?, ?> component) {
		MeshUI ui = component.getUI();
		virtualRealityApplication.getVRServer().removeMesh(ui.getMesh());
	}

}
