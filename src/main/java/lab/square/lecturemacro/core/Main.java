package lab.square.lecturemacro.core;

public class Main {

	private static final long TIMEOUT = 6500l;
	private static final long RESTART = 300000l;
	
	public static void main(String[] args) {
		loop();
	}

	private static void loop() {
		while(true) {
			start();
		}
	}

	private static void start() {
		try {
			final Macro m = new Macro();
			HeartbeatThread t = startHeartbeat(m);
			long started = System.currentTimeMillis();
			
			waitAndWakeUpWhenMacroFails(m, t, started);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static HeartbeatThread startHeartbeat(final Macro m) {
		final HeartbeatThread t = new HeartbeatThread(m);
		m.setListener(t);
		t.start();
		return t;
	}

	private static void waitAndWakeUpWhenMacroFails(final Macro m, final HeartbeatThread t, long started) {
		while(true) {
			System.out.println("Checking heartbeat... : " + (System.currentTimeMillis() - t.getHeartbeatTime()));
			if(System.currentTimeMillis() - t.getHeartbeatTime() > TIMEOUT) {
				restart(m, t);
				break;
			}
			if(System.currentTimeMillis() - started > RESTART) {
				restart(m, t);
				break;
			}
			Utils.sleep(1000l);
		}
	}

	private static void restart(final Macro m, final HeartbeatThread t) {
		System.out.println("Restarting");
		new Thread(new Runnable() {

			public void run() {
				m.close();
				t.stop();								
			}
			
		}).start();
	}

}
