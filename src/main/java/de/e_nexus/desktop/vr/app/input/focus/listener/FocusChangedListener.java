package de.e_nexus.desktop.vr.app.input.focus.listener;

import de.e_nexus.desktop.vr.app.input.focus.FocusableVRComponent;

public interface FocusChangedListener {

	void beforeChangeFocus(FocusableVRComponent<?> oldComponent, FocusableVRComponent<?> newlyFocusedComponent);

	void afterChangeFocus(FocusableVRComponent<?> oldComponent, FocusableVRComponent<?> newlyFocusedComponent);

}
