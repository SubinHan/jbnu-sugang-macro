package lab.square.lecturemacro.core;

public class Main {

	public static void main(String[] args) {
		while(true) {
			try {
				Macro m = new Macro();
				HeartbeatThread t = new HeartbeatThread(m);
				m.setListener(t);
				t.start();
				
				while(true) {
					if(System.currentTimeMillis() - t.getHeartbeatTime() > 15000l) {
						m.close();
						t.stop();
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
