package lab.square.lecturemacro.core;

public class HeartbeatThread extends Thread implements IHeartbeatListener {

	long heartbeatTime;
	
	public HeartbeatThread(Runnable target) {
		super(target);
		heartbeatTime = System.currentTimeMillis();
	}

	public void heartbeat(Object o) {
		heartbeatTime = System.currentTimeMillis();
	}
	
	public long getHeartbeatTime() {
		return heartbeatTime;
	}
	
	

}
