package net.greenbeansit.jobtracker.client.components;

public interface HomePageObservable {
	
	HomePageHandler handler = new HomePageHandler();
	
	public void update();
	public void notifyHandler();

}
