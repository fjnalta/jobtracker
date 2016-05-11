package net.greenbeansit.jobtracker.client.components.widgets;

import java.util.List;

import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.extras.select.client.ui.OptGroup;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.HomePageObservable;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Widget Class which provide the functionality of saving and loading report templates,
 *and enter a working description
 * 
 * @author Alexander Kirilyuk
 *
 */

public class WorkDiscriptionWidget extends Composite implements HomePageObservable
{		

	private static WorkDiscriptionWidgetUiBinder uiBinder = 
			GWT.create(WorkDiscriptionWidgetUiBinder.class);

	interface WorkDiscriptionWidgetUiBinder extends UiBinder<Widget, WorkDiscriptionWidget>
	{
		
	}
	
	@UiField 
	Select selectTemplate;
	
	@UiField 
	OptGroup optGroupTemplate;
	
	@UiField
	TextArea textDiscription;
	
	@UiField
	TextBox textIdentifier;
	
	@UiField
	TextBox textName;
	
	ActivityReportTemplate selectedTemplate;
	
	public WorkDiscriptionWidget()
	{	
		initWidget(uiBinder.createAndBindUi(this));
		
		handler.addObservable(this);
		handler.updateObservable(this);
		selectTemplate.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				selectedTemplate = ((SelectTemplateOption)selectTemplate.getSelectedItem()).getTemplate();
			}
		});
	}


	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
	}
	
	@UiHandler("buttonSave")
	public void saveTemplate(final ClickEvent e){
		ActivityReportTemplate template = new ActivityReportTemplate();
		template.setId(0L);
		template.setDescription(textDiscription.getText());
		template.setTemplateName(textName.getText());
		handler.saveTemplate(template);
	}
	
	@UiHandler("buttonLoad")
	public void loadTemplate(final ClickEvent e){
		textDiscription.setText(selectedTemplate.getDescription());
		textName.setText(selectedTemplate.getTemplateName());
	}

	
	public void addTemplates(List<ActivityReportTemplate> templateList){
		for(ActivityReportTemplate t : templateList){
			selectTemplate.add(new SelectTemplateOption(t));
		}
	}

	@Override
	public void update() {
		selectTemplate.clear();
		addTemplates(handler.getTemplateList());
		selectTemplate.refresh();
	}


	@Override
	public void notifyHandler() {
		ActivityReportTemplate template = new ActivityReportTemplate();
		template.setId(0L);
		template.setDescription(textDiscription.getText());
		template.setTemplateName(textName.getText());
		handler.setCurrentTemplate(template);
	}
}
