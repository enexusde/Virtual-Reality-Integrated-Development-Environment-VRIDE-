package de.e_nexus.desktop.vr.app.comp.ui;

public class VRUIManagerAuthority {
	private static DefaultUIManager manager = new DefaultUIManager();

	public static DefaultUIManager getDefaultManager() {
		return manager;
	}
}
