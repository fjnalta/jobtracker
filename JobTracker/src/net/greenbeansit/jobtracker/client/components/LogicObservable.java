package net.greenbeansit.jobtracker.client.components;

public interface LogicObservable {
	
	LogicHandler handler = new LogicHandler();
	
	public void updateObservable();
	public void notifyLogicHandler();

}
