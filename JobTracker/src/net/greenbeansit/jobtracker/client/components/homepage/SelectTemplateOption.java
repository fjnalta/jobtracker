package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.extras.select.client.ui.Option;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

public class SelectTemplateOption extends Option {
	
private ActivityReportTemplate template;
	
	public SelectTemplateOption(ActivityReportTemplate template){
		this.template = template;
		this.setText(template.getName());
	}

	public ActivityReportTemplate getTemplate() {
		return this.template;
	}

	public void setTemplate(ActivityReportTemplate template) {
		this.template = template;
	}

}
