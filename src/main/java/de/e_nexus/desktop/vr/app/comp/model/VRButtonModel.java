package de.e_nexus.desktop.vr.app.comp.model;

import de.e_nexus.desktop.vr.app.comp.model.listener.VRButtonActionListener;
import de.e_nexus.desktop.vr.app.comp.model.listener.VRButtonSelectionListener;
import de.e_nexus.desktop.vr.app.comp.model.listener.VRButtonTitleListener;

public interface VRButtonModel extends VRComponentModel {

	void removeTitleListener(VRButtonTitleListener titleListener);

	void addTitleListener(VRButtonTitleListener titleListener);

	void removeSelectionListener(VRButtonSelectionListener selectionListener);

	void addSelectionListener(VRButtonSelectionListener selectionListener);

	void removeActionListener(VRButtonActionListener actionListener);

	void addActionListener(VRButtonActionListener actionListener);

	void setSelected(boolean selected);

	boolean isSelected();

	void setTitle(String title);

	String getTitle();

}
