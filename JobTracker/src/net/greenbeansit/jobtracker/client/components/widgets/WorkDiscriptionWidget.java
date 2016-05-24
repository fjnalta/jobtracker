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

import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Widget Class which provide the functionality of saving and loading report templates,
 *and enter a working description
 * 
 * @author Alexander Kirilyuk
 *
 */

public class WorkDiscriptionWidget extends Composite implements LogicObservable
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
		handler.loadTemplates();
	}


	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
	}
	
	@UiHandler("buttonSave")
	public void saveTemplate(final ClickEvent e){
		ActivityReportTemplate template = new ActivityReportTemplate();
		template.setTaskId(0);
		if(textDiscription.getText().length()>0&&textName.getText().length()>0){
			template.setText(textDiscription.getText());
			template.setName(textName.getText());
			template.setTaskId(null);
			handler.saveTemplate(template);
		}else{
			NotifyHelper.errorMessage("fill in the missing fields");
		}	
	}
	
	@UiHandler("buttonLoad")
	public void loadTemplate(final ClickEvent e){
		textDiscription.setText(selectedTemplate.getText());
		textName.setText(selectedTemplate.getName());
	}

	
	public void addTemplates(List<ActivityReportTemplate> templateList){
		for(ActivityReportTemplate t : templateList){
			selectTemplate.add(new SelectTemplateOption(t));
		}
	}

	@Override
	public void updateObservable() {
		selectTemplate.clear();
		addTemplates(handler.getTemplateList());
		selectTemplate.refresh();
	}


	@Override
	public void notifyLogicHandler() {
		ActivityReportTemplate template = new ActivityReportTemplate();
		template.setTaskId(0);
		template.setText(textDiscription.getText());
		template.setName(textName.getText());
		handler.setCurrentTemplate(template);
	}
}
