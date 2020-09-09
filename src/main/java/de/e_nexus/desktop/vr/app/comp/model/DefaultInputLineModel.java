package de.e_nexus.desktop.vr.app.comp.model;

import java.util.LinkedHashSet;
import java.util.Set;

import de.e_nexus.desktop.vr.app.comp.model.listener.ChangeLineListener;
import de.e_nexus.desktop.vr.app.comp.model.listener.ChangeLineSelectionListener;
import de.e_nexus.desktop.vr.app.comp.model.listener.LineCarretChangeListener;

public class DefaultInputLineModel implements VRComponentModel {
	private String currentText;
	private int carretPosition;
	private int selection;

	public Set<ChangeLineSelectionListener> changeLineSelectionListeners = new LinkedHashSet<>();
	public Set<ChangeLineListener> changeLineListeners = new LinkedHashSet<>();
	public Set<LineCarretChangeListener> changeLineCarretListeners = new LinkedHashSet<>();

	public int getCarretPosition() {
		return carretPosition;
	}

	public String getCurrentText() {
		return currentText;
	}

	public int getSelection() {
		return selection;
	}

	public void setText(String text) {
		VRComponentModel.change(changeLineListeners, (L, OLD) -> L.notifyBeforeLineChange(OLD, text),
				() -> this.currentText, (V) -> this.currentText = V, (L, OLD) -> L.notifyLineChanged(OLD, text), text);
	}

	public void setCarretPosition(int carretPosition) {
		VRComponentModel.change(changeLineCarretListeners,
				(L, OLD) -> L.notifyBeforeLineCarretChange(OLD, carretPosition), () -> this.carretPosition,
				(V) -> this.carretPosition = V, (L, OLD) -> L.notifyLineCarretChanged(OLD, carretPosition),
				carretPosition);
	}

	public void setSelection(int selection) {
		VRComponentModel.change(changeLineSelectionListeners,
				(L, OLD) -> L.notifyBeforeLineSelectionChange(OLD, selection), () -> this.selection,
				(V) -> this.selection = V, (L, OLD) -> L.notifyLineSelectionChanged(OLD, selection), selection);
	}

	public void addChangeLineSelectionListener(ChangeLineSelectionListener a) {
		changeLineSelectionListeners.add(a);
	}

	public void addChangeLineListener(ChangeLineListener a) {
		changeLineListeners.add(a);
	}

	public void addLineCarretChangeListener(LineCarretChangeListener a) {
		changeLineCarretListeners.add(a);
	}
}
