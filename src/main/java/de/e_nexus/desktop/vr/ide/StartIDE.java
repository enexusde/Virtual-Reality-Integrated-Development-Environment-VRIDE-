package de.e_nexus.desktop.vr.ide;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Reference;
import org.osgi.service.cdi.annotations.Service;

import de.e_nexus.vr.server.ClientKeyboardScancode;
import de.e_nexus.vr.server.VRClientHelmetAndControllerListener;
import de.e_nexus.vr.server.VRClientKeyboardListener;
import de.e_nexus.vr.server.VRServer;
import de.e_nexus.vr.server.listeners.VRClientRequestAppInfo;
import de.e_nexus.vr.server.listeners.VRClientStatusListener;
import de.e_nexus.vr.server.listeners.interaction.HelmetAndControllerInfo;
import de.e_nexus.vr.server.osgi.inter.VRServerService;
import de.e_nexus.vr.tk.VRFrame;

@ApplicationScoped
@Service
public class StartIDE implements VRClientStatusListener, VRClientRequestAppInfo, VRClientHelmetAndControllerListener, VRClientKeyboardListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = Logger.getLogger(StartIDE.class.getCanonicalName());

	private final static Object lock = new Object();

	private VRServer vrServer;

	public void notifyStatus(boolean connected) {
		if (!connected) {
			synchronized (lock) {
				lock.notify();
			}
		}
	}

	@Reference
	@Inject
	private VRServerService vrServerService;

	private VRFrame applicationFrame;

	@PostConstruct
	public void initialize() {
		LOG.fine("Starting VR IDE");
		vrServer = vrServerService.getVRServer();
		applicationFrame = new VRFrame(vrServer, "test");

		LOG.info(getLatin1Title() + " started successfully!");
	}

	@PreDestroy
	public void teardown() {
		applicationFrame.remove();
		LOG.info("Stoped " + getLatin1Title() + " successfully!");
		vrServer = null;
	}

	public VRServer getVrServer() {
		return vrServer;
	}

	@Override
	public void notify(HelmetAndControllerInfo haci) {

	}

	@Override
	public String getLatin1Title() {
		return "VR IDE";
	}

	@Override
	public void notifyKeyboardEvent(ClientKeyboardScancode[] downs, ClientKeyboardScancode[] ups, long incommingTime) {
		System.out.println("me");
	}
}