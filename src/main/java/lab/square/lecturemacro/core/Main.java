package lab.square.lecturemacro.core;

public class Main {

	private static final long TIMEOUT = 6500l;
	
	public static void main(String[] args) {
		while(true) {
			try {
				final Macro m = new Macro();
				final HeartbeatThread t = new HeartbeatThread(m);
				m.setListener(t);
				t.start();
				
				while(true) {
					System.out.println("Checking heartbeat... : " + (System.currentTimeMillis() - t.getHeartbeatTime()));
					if(System.currentTimeMillis() - t.getHeartbeatTime() > TIMEOUT) {
						System.out.println("Restarting");
						new Thread(new Runnable() {

							public void run() {
								m.close();
								t.stop();								
							}
							
						}).start();
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
