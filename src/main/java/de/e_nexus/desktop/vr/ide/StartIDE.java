package de.e_nexus.desktop.vr.ide;

import java.awt.Color;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Reference;
import org.osgi.service.cdi.annotations.Service;

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
import de.e_nexus.vr.server.mesh.Mesh;
import de.e_nexus.vr.server.mesh.tex.Texture;
import de.e_nexus.vr.server.mesh.tex.TextureStage;
import de.e_nexus.vr.server.osgi.inter.VRServerService;
import de.e_nexus.vr.server.util.TextureTools;

@ApplicationScoped
@Service
public class StartIDE implements VRClientStatusListener, VRClientRequestAppInfo, VRClientHelmetAndControllerListener, VRClientKeyboardListener {
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

	@Reference
	@Inject
	private VRServerService vrServerService;

	private Mesh m;

	@PostConstruct
	public void start() {
		vrServer = vrServerService.getVRServer();
		m = new Mesh();
		m.addCube(0, 0, 2f, 1f, 1f);
		m.setTexture(TextureStage.NORMALS, TextureTools.fromColor(Color.red));

		vrServer.addMesh(m);
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

	@PreDestroy
	public void stop() {
		vrServer.removeMesh(m);
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