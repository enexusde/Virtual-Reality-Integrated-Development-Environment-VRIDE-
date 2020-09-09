package de.e_nexus.desktop.vr.app.input;

import de.e_nexus.vr.server.ClientKeyboardScancode;

public final class MutableKeyPressEvent {
	private boolean ctrlDown;
	private boolean altDown;
	private boolean shiftDown;

	public MutableKeyPressEvent(ClientKeyboardScancode code, long since, boolean ctrlDown, boolean altDown,
			boolean shiftDown) {
		super();
		this.ctrlDown = ctrlDown;
		this.altDown = altDown;
		this.shiftDown = shiftDown;
		this.code = code;
		this.since = since;
	}

	private final ClientKeyboardScancode code;
	private final long since;
	private Long released;

	public void setReleased(long released) {
		this.released = released;
	}

	public Long getReleased() {
		return released;
	}

	public boolean isReleased() {
		return released != null;
	}

	public ClientKeyboardScancode getCode() {
		return code;
	}

	public boolean isAltDown() {
		return altDown;
	}

	public boolean isCtrlDown() {
		return ctrlDown;
	}

	public boolean isShiftDown() {
		return shiftDown;
	}

	public long getSince() {
		return since;
	}
}
