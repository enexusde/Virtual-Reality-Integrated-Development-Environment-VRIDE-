package de.e_nexus.desktop.vr.app.comp.ui;

import java.awt.Color;

import de.e_nexus.desktop.vr.app.comp.VRButton;
import de.e_nexus.desktop.vr.app.comp.VRInputLine;
import de.e_nexus.vr.server.VRServer;
import de.e_nexus.vr.server.mesh.Mesh;
import de.e_nexus.vr.server.mesh.UVVector;
import de.e_nexus.vr.server.mesh.tex.BufferedImageTexture;
import de.e_nexus.vr.server.mesh.tex.TextureStage;
import de.e_nexus.vr.server.util.TextureTools;

public class DefaultUIManager {

	private VRServer vrServer;

	public MeshUI constructMeshUI(VRButton vrButton) {
		Mesh<UVVector> buttonMesh = new Mesh<>();
		String title = vrButton.getModel().getTitle();

		BufferedImageTexture textTexture = TextureTools.fromText(title, new Color(26, 26, 26, 255),
				new Color(236, 236, 236, 255), null);
		buttonMesh.addCube(0, 0, 0, textTexture.getHeight() / 200, textTexture.getWidth() / 200);
		buttonMesh.setTexture(TextureStage.DIFFUSE, textTexture);
		return new MeshUI(buttonMesh, vrServer);
	}

	public void setVRServer(VRServer vrServer) {
		this.vrServer = vrServer;
	}

	public MeshUI constructLineUI(VRInputLine vrInputText) {
		Mesh<UVVector> textMesh = new Mesh<>();
		BufferedImageTexture text = TextureTools.fromText(vrInputText.getModel().getCurrentText(), Color.black,
				Color.WHITE, null);
		textMesh.addCube(0f, 1f, 1f, text.getWidth() * 1f, text.getHeight() * 1f);
		textMesh.setTexture(TextureStage.DIFFUSE, text);
		MeshLineUI meshLineUI = new MeshLineUI(textMesh, vrServer, vrInputText);
		return meshLineUI;
	}
}
