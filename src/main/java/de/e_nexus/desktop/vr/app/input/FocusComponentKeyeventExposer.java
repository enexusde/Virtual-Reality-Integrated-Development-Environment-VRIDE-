package de.e_nexus.desktop.vr.app.input;

import java.util.Set;

import de.e_nexus.desktop.vr.app.input.focus.FocusableVRComponent;
import de.e_nexus.desktop.vr.app.input.focus.TextFocusWorker;

public class FocusComponentKeyeventExposer implements KeystrokeRecipient {

	private TextFocusWorker textFocusWorker;

	public FocusComponentKeyeventExposer(TextFocusWorker textFocusWorker) {
		this.textFocusWorker = textFocusWorker;
	}

	public void notifyChangedDownpressing(Set<MutableKeyPressEvent> currentlyDownpressing) {
		FocusableVRComponent<?> focusedComponent = textFocusWorker.getFocusedComponent();
		focusedComponent.getModel();
	}
}
