package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.extras.select.client.ui.Option;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Helper class for storing the {@link ActivityReportTemplate} in the Option object of the Select fields
 * @author Alexander Kirilyuk
 */
public class SelectTemplateOption extends Option {
	
private ActivityReportTemplate template;

	/**
	 * initialize one object with a given {@link ActivityReportTemplate}
	 * @param template the Template
     */
	public SelectTemplateOption(ActivityReportTemplate template){
		this.template = template;
		this.setText(template.getName());
	}

	/**
	 * get the associated {@link ActivityReportTemplate}
	 * @return {@link ActivityReportTemplate}
     */
	public ActivityReportTemplate getTemplate() {
		return this.template;
	}

	/**
	 * set a new {@link ActivityReportTemplate}
	 * @param template the {@link ActivityReportTemplate} to save
     */
	public void setTemplate(ActivityReportTemplate template) {
		this.template = template;
	}

}
