package de.e_nexus.desktop.vr.app.comp.ui;

import de.e_nexus.vr.server.VRServer;
import de.e_nexus.vr.server.mesh.Mesh;

public class MeshUI {
	private Mesh<?> mesh;
	private VRServer server;

	public MeshUI(Mesh<?> mesh, VRServer server) {
		this.mesh = mesh;
		this.server = server;
	}

	public void update(Mesh<?> newMesh) {
		server.removeMesh(mesh);
		this.mesh = newMesh;
		server.addMesh(mesh);
	}

	public Mesh<?> getMesh() {
		return mesh;
	}
}
