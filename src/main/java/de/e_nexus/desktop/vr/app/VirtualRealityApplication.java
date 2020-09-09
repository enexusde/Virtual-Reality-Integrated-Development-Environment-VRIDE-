package de.e_nexus.desktop.vr.app;

import java.util.Set;

import de.e_nexus.desktop.vr.app.comp.VRComponent;
import de.e_nexus.desktop.vr.app.comp.model.VRComponentModel;
import de.e_nexus.desktop.vr.app.input.focus.TextFocusWorker;
import de.e_nexus.desktop.vr.app.input.focus.listener.RegisterFocusableToWorker;
import de.e_nexus.desktop.vr.app.model.DefaultVRApplicationModel;
import de.e_nexus.desktop.vr.app.model.VRApplicationModel;
import de.e_nexus.desktop.vr.app.model.listener.VRApplicationComponentListener;
import de.e_nexus.vr.server.VRServer;

public class VirtualRealityApplication extends VRComponentContainer<VRComponentModel, VRComponent<?, ?>> {

	private VRServer vrServer;

	private VRApplicationModel model = new DefaultVRApplicationModel();

	private TextFocusWorker textFocusWorker;

	public VirtualRealityApplication(VRServer vrServer, TextFocusWorker textFocusWorker) {
		this.vrServer = vrServer;
		this.textFocusWorker = textFocusWorker;
		getModel().addApplicationComponentListener(new DefaultPopulateMeshToVRServer(this));
		getModel().addApplicationComponentListener(new RegisterFocusableToWorker(this, textFocusWorker));
	}

	public VRApplicationModel getModel() {
		return model;
	}

	public VRServer getVRServer() {
		return vrServer;
	}

	@Override
	protected Set<VRApplicationComponentListener> getComponentListeners() {
		return getModel().getApplicationComponentListeners();
	}

	public TextFocusWorker getTextFocusWorker() {
		return textFocusWorker;
	}

}
