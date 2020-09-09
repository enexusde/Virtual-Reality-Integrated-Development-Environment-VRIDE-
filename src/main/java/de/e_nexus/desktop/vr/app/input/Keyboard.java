package de.e_nexus.desktop.vr.app.input;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import de.e_nexus.desktop.vr.app.input.conf.KeyboardConfiguration;
import de.e_nexus.vr.server.ClientKeyboardScancode;

public class Keyboard {
	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = Logger.getLogger(Keyboard.class.getCanonicalName());
	private boolean shiftDown, altDown, ctrlDown;
	private Long lastIncomming;
	private KeyboardConfiguration config = new KeyboardConfiguration();
	private KeystrokeRecipient primaryRecipient = null;
	private Set<MutableKeyPressEvent> currentlyDownpressing = new LinkedHashSet<MutableKeyPressEvent>();
	private Set<KeystrokeRecipient> recipients = new LinkedHashSet<KeystrokeRecipient>();

	private void genuineEventProcessor(ClientKeyboardScancode[] downs, ClientKeyboardScancode[] ups,
			long incommingTime) {
		calcFlags(downs);
		addDownKeys(downs, incommingTime);
		removeReleasedKeys(ups, incommingTime);
		primaryRecipient.notifyChangedDownpressing(currentlyDownpressing);
		notifyListeners();
	}

	private void notifyListeners() {
		for (KeystrokeRecipient rec : recipients) {
			rec.notifyChangedDownpressing(currentlyDownpressing);
		}
	}

	private void removeReleasedKeys(ClientKeyboardScancode[] ups, long incommingTime) {
		for (ClientKeyboardScancode clientKeyboardScancode : ups) {
			if (clientKeyboardScancode == ClientKeyboardScancode.SHIFT) {
				shiftDown = false;
			} else if (clientKeyboardScancode == ClientKeyboardScancode.ALT) {
				altDown = false;
			} else if (clientKeyboardScancode == ClientKeyboardScancode.CTRL) {
				ctrlDown = false;
			}
			Iterator<MutableKeyPressEvent> it = currentlyDownpressing.iterator();
			while (it.hasNext()) {
				MutableKeyPressEvent e = (MutableKeyPressEvent) it.next();
				if (e.getCode() == clientKeyboardScancode) {
					e.setReleased(incommingTime);
					it.remove();
				}
			}
		}
	}

	private void addDownKeys(ClientKeyboardScancode[] downs, long incommingTime) {
		for (ClientKeyboardScancode clientKeyboardScancode : downs) {
			currentlyDownpressing
					.add(new MutableKeyPressEvent(clientKeyboardScancode, incommingTime, ctrlDown, altDown, shiftDown));
		}
	}

	private void calcFlags(ClientKeyboardScancode[] downs) {
		for (ClientKeyboardScancode clientKeyboardScancode : downs) {
			if (clientKeyboardScancode == ClientKeyboardScancode.SHIFT) {
				shiftDown = true;
			} else if (clientKeyboardScancode == ClientKeyboardScancode.ALT) {
				altDown = true;
			} else if (clientKeyboardScancode == ClientKeyboardScancode.CTRL) {
				ctrlDown = true;
			}
		}
	}

	public void setPrimaryRecipient(KeystrokeRecipient recipient) {
		this.primaryRecipient = recipient;
	}

	public KeystrokeRecipient getPrimaryRecipient() {
		return primaryRecipient;
	}

	public KeyboardConfiguration getConfig() {
		return config;
	}

	public void notifyKeyboardEvent(ClientKeyboardScancode[] downs, ClientKeyboardScancode[] ups, long incommingTime) {
		if (lastIncomming == null) {
			lastIncomming = incommingTime;
		}
		if (lastIncomming > incommingTime) {
			LOG.severe("The incomming keyboard event is obsolete.");
		} else {
			genuineEventProcessor(downs, ups, incommingTime);
		}
	}
}
