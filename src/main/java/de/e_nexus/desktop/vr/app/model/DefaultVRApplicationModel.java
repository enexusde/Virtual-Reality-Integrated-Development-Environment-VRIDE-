package de.e_nexus.desktop.vr.app.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import de.e_nexus.desktop.vr.app.model.listener.VRApplicationComponentListener;

public class DefaultVRApplicationModel implements VRApplicationModel {
	private Set<VRApplicationComponentListener> component = new LinkedHashSet<>(0);

	@Override
	public void addApplicationComponentListener(VRApplicationComponentListener listener) {
		component.add(listener);
	}

	@Override
	public Set<VRApplicationComponentListener> getApplicationComponentListeners() {
		return Collections.unmodifiableSet(component);
	}

	@Override
	public boolean removeApplicationComponentListener(VRApplicationComponentListener listener) {
		return component.remove(listener);
	}
}
