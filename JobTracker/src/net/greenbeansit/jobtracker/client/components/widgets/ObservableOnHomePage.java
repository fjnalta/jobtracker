package net.greenbeansit.jobtracker.client.components.widgets;

import net.greenbeansit.jobtracker.client.components.HomePage;

public interface ObservableOnHomePage {
	void registerObserver(HomePage homePage);
	void removeObserver(HomePage homePage);
	void notificate();
}
