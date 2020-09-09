package de.e_nexus.desktop.vr.ide;

import java.net.SocketTimeoutException;
import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.e_nexus.desktop.vr.app.VirtualRealityApplication;
import de.e_nexus.desktop.vr.app.comp.VRInputLine;
import de.e_nexus.desktop.vr.app.comp.ui.VRUIManagerAuthority;
import de.e_nexus.desktop.vr.app.input.FocusComponentKeyeventExposer;
import de.e_nexus.desktop.vr.app.input.Keyboard;
import de.e_nexus.desktop.vr.app.input.focus.TextFocusWorker;
import de.e_nexus.vr.server.ClientKeyboardScancode;
import de.e_nexus.vr.server.VRClientHelmetAndControllerListener;
import de.e_nexus.vr.server.VRClientKeyboardListener;
import de.e_nexus.vr.server.VRServer;
import de.e_nexus.vr.server.listeners.VRClientRequestAppInfo;
import de.e_nexus.vr.server.listeners.VRClientStatusListener;
import de.e_nexus.vr.server.listeners.VRExceptionListener;
import de.e_nexus.vr.server.listeners.interaction.HelmetAndControllerInfo;
import de.e_nexus.vr.server.osgi.inter.VRServerService;

public class StartIDE implements VRClientStatusListener, BundleActivator, VRClientRequestAppInfo,
		VRClientHelmetAndControllerListener, VRClientKeyboardListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = Logger.getLogger(StartIDE.class.getCanonicalName());

	private final static Object lock = new Object();

	private final Keyboard keyboard = new Keyboard();

	private VirtualRealityApplication app;

	private VRServer vrServer;

	public void notifyStatus(boolean connected) {
		if (!connected) {
			synchronized (lock) {
				lock.notify();
			}
		}
	}

	public void start(BundleContext ctx) throws Exception {
		LOG.info("Start '" + getLatin1Title() + "'");
		ServiceReference<VRServerService> ref = ctx.getServiceReference(VRServerService.class);
		VRServerService vrServerService = ctx.getService(ref);
		vrServer = vrServerService.getVRServer();
		VRUIManagerAuthority.getDefaultManager().setVRServer(vrServer);
		vrServer.getListeners().addInfoListener(this);
		vrServer.getListeners().addVRClientStatusListener(this);
		vrServer.getListeners().addInteractionListener(this);
		TextFocusWorker textFocusWorker = new TextFocusWorker();
		FocusComponentKeyeventExposer textInputExposer = new FocusComponentKeyeventExposer(textFocusWorker);
		keyboard.setPrimaryRecipient(textInputExposer);
		vrServer.getListeners().addKeyboardListener(this);
		vrServer.getListeners().addVRExceptionListener(new VRExceptionListener() {
			public void handle(Throwable e) {
				if (!(e instanceof SocketTimeoutException)) {
					e.printStackTrace();
				}
			}
		});
		app = new VirtualRealityApplication(vrServer, textFocusWorker);
		VRInputLine commandLine = new VRInputLine("Hallo");
		app.addComponent(commandLine);
		app.getTextFocusWorker().setFocusedComponent(commandLine);
	}

	public void stop(BundleContext ctx) throws Exception {
		LOG.info("Stop " + getLatin1Title());
		app.removeAll();
		VRUIManagerAuthority.getDefaultManager().setVRServer(null);
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
		Thread t = new Thread(() -> keyboard.notifyKeyboardEvent(downs, ups, incommingTime));
		System.out.println("me");
		t.start();
	}
}