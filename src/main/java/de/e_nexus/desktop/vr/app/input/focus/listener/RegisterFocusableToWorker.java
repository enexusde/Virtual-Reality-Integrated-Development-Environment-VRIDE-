package de.e_nexus.desktop.vr.app.input.focus.listener;

import de.e_nexus.desktop.vr.app.VirtualRealityApplication;
import de.e_nexus.desktop.vr.app.comp.VRComponent;
import de.e_nexus.desktop.vr.app.input.focus.FocusableVRComponent;
import de.e_nexus.desktop.vr.app.input.focus.TextFocusWorker;
import de.e_nexus.desktop.vr.app.model.listener.VRApplicationComponentListener;

public class RegisterFocusableToWorker implements VRApplicationComponentListener {

	private VirtualRealityApplication virtualRealityApplication;
	private TextFocusWorker textFocusWorker;

	public RegisterFocusableToWorker(VirtualRealityApplication virtualRealityApplication,
			TextFocusWorker textFocusWorker) {
		this.virtualRealityApplication = virtualRealityApplication;
		this.textFocusWorker = textFocusWorker;
	}

	@Override
	public void beforeAdd(VRComponent<?, ?> component) {

	}

	@Override
	public void afterAdd(VRComponent<?, ?> component) {
		if (component instanceof FocusableVRComponent) {
			textFocusWorker.addFocusableComponent((FocusableVRComponent<?>) component);
		}

	}

	@Override
	public void beforeRemove(VRComponent<?, ?> component) {
		if (component instanceof FocusableVRComponent) {
			if (textFocusWorker.getFocusableComponents().contains((FocusableVRComponent<?>) component)) {
				textFocusWorker.removeFocusableComponent((FocusableVRComponent<?>) component);
			}
		}
	}

	@Override
	public void afterRemove(VRComponent<?, ?> component) {
	}

	public VirtualRealityApplication getVirtualRealityApplication() {
		return virtualRealityApplication;
	}
}
