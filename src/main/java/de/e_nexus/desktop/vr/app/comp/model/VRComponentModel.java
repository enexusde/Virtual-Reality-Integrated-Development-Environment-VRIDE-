package de.e_nexus.desktop.vr.app.comp.model;

import java.util.Collection;
import java.util.logging.Logger;

import de.e_nexus.desktop.vr.app.comp.listener.GetAction;
import de.e_nexus.desktop.vr.app.comp.listener.InvokeListener;
import de.e_nexus.desktop.vr.app.comp.listener.SetAction;
import de.e_nexus.desktop.vr.app.comp.model.ex.ModelPropertyNotChangedException;
import de.e_nexus.desktop.vr.app.comp.model.listener.VRListener;

public interface VRComponentModel {
	/**
	 * The logger for this class.
	 */
	static final Logger LOG = Logger.getLogger(VRComponentModel.class.getCanonicalName());

	public static <P, L extends VRListener> void change(Collection<L> listenersToNotify,
			InvokeListener<P, L> methodToNotifyBeforeChange, GetAction<P> methodToGetOldValue,
			SetAction<P> methodOfSettingNewValue, InvokeListener<P, L> methodToNotifyAfter, P newValueToSet)
			throws ModelPropertyNotChangedException {
		P old = methodToGetOldValue.get();
		if (old == newValueToSet) {
			throw new ModelPropertyNotChangedException();
		}
		for (L listener : listenersToNotify) {
			methodToNotifyBeforeChange.invoke(listener, old);
		}
		methodOfSettingNewValue.set(newValueToSet);
		for (L listener : listenersToNotify) {
			methodToNotifyAfter.invoke(listener, old);
		}
	}
}
