package net.greenbeansit.jobtracker.client.components.widgets;

import java.util.List;

import net.greenbeansit.jobtracker.client.components.LogicHandler;

import net.greenbeansit.jobtracker.shared.Job;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormLabel;
import org.gwtbootstrap3.client.ui.Heading;
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
import net.greenbeansit.jobtracker.client.localization.HomePageConstants;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Widget Class which provide the functionality of saving and loading report templates,
 *and enter a working description
 * 
 * @author Alexander Kirilyuk
 *
 */

public class WorkDescriptionWidget extends Composite implements LogicObservable
{		

	private static WorkDescriptionWidgetUiBinder uiBinder =
			GWT.create(WorkDescriptionWidgetUiBinder.class);

	private static HomePageConstants constants = GWT.create(HomePageConstants.class);
	
	/**
	 * UiBinder Interface for {@link WorkDescriptionWidget}
	 */
	interface WorkDescriptionWidgetUiBinder extends UiBinder<Widget, WorkDescriptionWidget>
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
	FormLabel labelDescription, labelTask,labelVorlage;
	
	@UiField
	TextBox textName;
	
	@UiField
	Button buttonSave;
	
	@UiField
	Button buttonLoad;

	

	@UiField
	FormLabel headingCollapseTemplate;
	
	ActivityReportTemplate selectedTemplate;

	/**
	 * Standard constructor, first register this object to LogicHandler
	 * then add a ValueChangeHandler on the select for handling the selection of different templates from the
	 * selectTemplate Select object
	 * Then the constructor calls the {@link LogicHandler#loadTemplates()} method load the Templates from the Backend
	 */
	public WorkDescriptionWidget()
	{	
		initWidget(uiBinder.createAndBindUi(this));
		handler.addObservable(this);
		selectTemplate.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				selectedTemplate = ((SelectTemplateOption)selectTemplate.getSelectedItem()).getTemplate();
			}
		});
		handler.loadTemplates();
		
		textDiscription.setPlaceholder(constants.workDescriptionPlaceHolder());
		textName.setPlaceholder(constants.textTemplateNamePlaceHolder());
		buttonLoad.setText(constants.buttonLoadTemplate());
		buttonSave.setText(constants.buttonSaveTemplate());


		headingCollapseTemplate.setText(constants.buttonTemplateText());
		
		labelDescription.setText(constants.headingWorkDescription());
		labelTask.setText(constants.headingSelectTask());
		labelVorlage.setText(constants.buttonTemplateText());
	}

	/**
	 * Method for saving a created template to the backend.
	 * It uses the {@link LogicHandler#saveTemplate(ActivityReportTemplate)} method
	 * @param e ClickEvent e
     */
	@UiHandler("buttonSave")
	public void saveTemplate(final ClickEvent e){
		ActivityReportTemplate template = new ActivityReportTemplate();
		if(!textDiscription.getText().isEmpty()&&!textName.getText().isEmpty()){
			template.setText(textDiscription.getText());
			template.setName(textName.getText());
			template.setTaskId(null);
			handler.saveTemplate(template);
		}else{
			NotifyHelper.errorMessage("Fill missing fields");
		}	
	}

	/**
	 * Method for loading a selected template to the textFields
	 * @param e CLickEvent
     */
	@UiHandler("buttonLoad")
	public void loadTemplate(final ClickEvent e){
		textDiscription.setText(selectedTemplate.getText());
		textName.setText(selectedTemplate.getName());
		Job selectedJob = new Job();
		selectedJob.setJobNr(selectedTemplate.getJobNr());
		selectedJob.setPosNr(selectedTemplate.getPosNr());
		for(Job elem : handler.getJobList()){
			if(elem.equals(selectedJob)){
				handler.setCurrentJob(elem);
				handler.updateAllObservables();
			}
		}
	}

	/**
	 * add new Templates to the selectTemplate field
	 * @param templateList a List with the templates to add
     */
	public void addTemplates(List<ActivityReportTemplate> templateList){
		for(ActivityReportTemplate t : templateList){
			selectTemplate.add(new SelectTemplateOption(t));
		}
	}

	/**
	 * Interface method of the {@link LogicObservable}
	 * Is in charge for updating this widget on new data
	 *
	 */
	@Override
	public void updateObservable() {
		selectTemplate.clear();
		addTemplates(handler.getTemplateList());
		ActivityReportTemplate currentTemplate = handler.getCurrentTemplate();
		if(handler.getCurrentTemplate()!=null){
			if(currentTemplate.getName()!=null){
				textName.setText(currentTemplate.getName());
			}
			if(currentTemplate.getText()!=null){
				textDiscription.setText(currentTemplate.getText());
			}
			if(currentTemplate.getTaskId()!=null){
				textIdentifier.setText(String.valueOf(currentTemplate.getTaskId()));
			}
		}
		selectTemplate.refresh();
	}

	/**
	 * Interface method of the {@link LogicObservable}
	 * In charge for submitting the current template inforamtions to the {@link LogicHandler}
	 */
	@Override
	public void notifyLogicHandler() {
		ActivityReportTemplate template = new ActivityReportTemplate();
		template.setName(null);
		template.setTaskId(null);
		template.setText(null);
		if(!textIdentifier.getText().isEmpty()){
			//template.setTaskId();
		}
		if(!textName.getText().isEmpty()){
			template.setName(textName.getText());
		}

		if(!textDiscription.getText().isEmpty()){
			template.setText(textDiscription.getText());
			handler.setCurrentTemplate(template);

		}else{
			NotifyHelper.errorMessage("Bitte geben sie eine Tätigkeitsbeschreibung ein");
		}

	}
}
