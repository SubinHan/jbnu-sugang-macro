package lab.square.lecturemacro.core;

public class Main {

	private static final long TIMEOUT = 8000l;
	
	public static void main(String[] args) {
		while(true) {
			try {
				Macro m = new Macro();
				HeartbeatThread t = new HeartbeatThread(m);
				m.setListener(t);
				t.start();
				
				while(true) {
					System.out.println("Checking heartbeat... : " + (System.currentTimeMillis() - t.getHeartbeatTime()));
					if(System.currentTimeMillis() - t.getHeartbeatTime() > TIMEOUT) {
						System.out.println("Restarting");
						m.close();
						t.stop();
						break;
					}
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
