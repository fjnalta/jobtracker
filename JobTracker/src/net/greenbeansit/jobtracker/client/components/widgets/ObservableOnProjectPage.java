package net.greenbeansit.jobtracker.client.components.widgets;

import net.greenbeansit.jobtracker.client.components.ProjectPage;

public interface ObservableOnProjectPage {
	void registerObserver(ProjectPage projectPage);
	void removeObserver(ProjectPage projectPage);
	void notificate();
}
