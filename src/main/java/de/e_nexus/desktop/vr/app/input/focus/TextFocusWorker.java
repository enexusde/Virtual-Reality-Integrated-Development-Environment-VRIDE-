package de.e_nexus.desktop.vr.app.input.focus;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import de.e_nexus.desktop.vr.app.input.focus.listener.FocusChangedListener;

public class TextFocusWorker {
	private final Set<FocusableVRComponent<?>> focusableComponents = new LinkedHashSet<>();
	private final Set<FocusChangedListener> listeners = new LinkedHashSet<>();
	private FocusableVRComponent<?> focusedComponent = null;

	public Set<FocusableVRComponent<?>> getFocusableComponents() {
		return Collections.unmodifiableSet(focusableComponents);
	}

	public void addFocusableComponent(FocusableVRComponent<?> component) {
		focusableComponents.add(component);
	}

	public void removeFocusableComponent(FocusableVRComponent<?> component) {
		focusableComponents.remove(component);
	}

	public void clearFocus() {
		focusedComponent = null;
	}

	public void addFocusListener(FocusChangedListener listener) {
		this.listeners.add(listener);
	}

	public Set<FocusChangedListener> getFocusListeners() {
		return Collections.unmodifiableSet(listeners);
	}

	public void removeFocusListener(FocusChangedListener listener) {
		this.listeners.remove(listener);
	}

	public FocusableVRComponent<?> getFocusedComponent() {
		return focusedComponent;
	}

	public void setFocusedComponent(FocusableVRComponent<?> newlyFocusedComponent) {
		synchronized (this) {
			FocusableVRComponent<?> oldComponent = this.focusedComponent;
			for (FocusChangedListener l : listeners) {
				l.beforeChangeFocus(oldComponent, newlyFocusedComponent);
			}
			this.focusedComponent = newlyFocusedComponent;
			for (FocusChangedListener l : listeners) {
				l.afterChangeFocus(oldComponent, newlyFocusedComponent);
			}
		}
	}

}
