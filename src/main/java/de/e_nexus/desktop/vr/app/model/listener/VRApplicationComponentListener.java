package de.e_nexus.desktop.vr.app.model.listener;

import de.e_nexus.desktop.vr.app.comp.VRComponent;

public interface VRApplicationComponentListener {

	void beforeAdd(VRComponent<?, ?> component);

	void afterAdd(VRComponent<?, ?> component);

	void beforeRemove(VRComponent<?, ?> component);

	void afterRemove(VRComponent<?, ?> component);

}
