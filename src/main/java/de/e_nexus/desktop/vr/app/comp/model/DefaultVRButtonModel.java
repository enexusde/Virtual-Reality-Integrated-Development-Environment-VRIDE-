package de.e_nexus.desktop.vr.app.comp.model;

import java.util.LinkedHashSet;
import java.util.Set;

import de.e_nexus.desktop.vr.app.comp.model.listener.VRButtonActionListener;
import de.e_nexus.desktop.vr.app.comp.model.listener.VRButtonSelectionListener;
import de.e_nexus.desktop.vr.app.comp.model.listener.VRButtonTitleListener;

public class DefaultVRButtonModel implements VRButtonModel {

	private String title;
	private boolean selected;
	private Set<VRButtonActionListener> actionListeners = new LinkedHashSet<>(0);
	private Set<VRButtonSelectionListener> selectionListeners = new LinkedHashSet<>(0);
	private Set<VRButtonTitleListener> titleListeners = new LinkedHashSet<>(0);

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		VRComponentModel.change(titleListeners, (L, OLD) -> L.notifyBeforeTitleChange(OLD, title), () -> this.title,
				(V) -> this.title = V, (L, OLD) -> L.notifyTitleChanged(OLD, title), title);
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setSelected(boolean selected) {
		VRComponentModel.change(selectionListeners, (L, OLD) -> {
			L.notifyBeforeSelectionChange(OLD, selected);
		}, () -> this.selected, (V) -> this.selected = V, (L, OLD) -> {
			L.notifySelectionChanged(OLD, selected);
		}, selected);
	}

	@Override
	public void addActionListener(VRButtonActionListener actionListener) {
		this.actionListeners.add(actionListener);
	}

	@Override
	public void removeActionListener(VRButtonActionListener actionListener) {
		this.actionListeners.remove(actionListener);
	}

	@Override
	public void addSelectionListener(VRButtonSelectionListener selectionListener) {
		this.selectionListeners.add(selectionListener);
	}

	@Override
	public void removeSelectionListener(VRButtonSelectionListener selectionListener) {
		this.selectionListeners.remove(selectionListener);
	}

	@Override
	public void addTitleListener(VRButtonTitleListener titleListener) {
		this.titleListeners.add(titleListener);
	}

	@Override
	public void removeTitleListener(VRButtonTitleListener titleListener) {
		this.titleListeners.remove(titleListener);
	}
}
