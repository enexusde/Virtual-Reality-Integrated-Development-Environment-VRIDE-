package de.e_nexus.desktop.vr.app;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import de.e_nexus.desktop.vr.app.comp.VRComponent;
import de.e_nexus.desktop.vr.app.comp.model.VRComponentModel;
import de.e_nexus.desktop.vr.app.model.listener.VRApplicationComponentListener;

public abstract class VRComponentContainer<M extends VRComponentModel, T extends VRComponent<?, ?>> {
	private final Set<VRComponent<?, ?>> components = new TreeSet<VRComponent<?, ?>>(
			(top, bottom) -> ((Integer) top.hashCode()).compareTo(bottom.hashCode()));

	protected abstract Set<VRApplicationComponentListener> getComponentListeners();

	public void addComponent(VRComponent<?, ?> component) {
		for (VRApplicationComponentListener cl : getComponentListeners()) {
			cl.beforeAdd(component);
		}
		components.add(component);
		for (VRApplicationComponentListener cl : getComponentListeners()) {
			cl.afterAdd(component);
		}
	}

	public Set<VRComponent<?, ?>> getComponents() {
		return Collections.unmodifiableSet(components);
	}

	public void removeComponent(T componentToRemove) {
		if (!components.contains(componentToRemove)) {
			return;
		}
		for (VRApplicationComponentListener cl : getComponentListeners()) {
			cl.beforeRemove(componentToRemove);
		}
		components.remove(componentToRemove);
		for (VRApplicationComponentListener cl : getComponentListeners()) {
			cl.afterRemove(componentToRemove);
		}
	}

	public void removeAll() {
		Set<VRComponent<?, ?>> comps = new LinkedHashSet<>(components);
		for (VRApplicationComponentListener cl : getComponentListeners()) {
			for (VRComponent<?, ?> vrComponent : comps) {
				cl.beforeRemove(vrComponent);
			}
		}
		for (VRComponent<?, ?> vrComponent : components)
			components.remove(vrComponent);
		for (VRApplicationComponentListener cl : getComponentListeners()) {
			for (VRComponent<?, ?> vrComponent : comps) {
				cl.afterRemove(vrComponent);
			}
		}
	}
}