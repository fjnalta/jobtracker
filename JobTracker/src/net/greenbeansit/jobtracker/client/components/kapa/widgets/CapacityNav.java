package net.greenbeansit.jobtracker.client.components.kapa.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import net.greenbeansit.jobtracker.client.components.CalendarObserver;
import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.components.kapa.CapaCalendarObserver;
import net.greenbeansit.jobtracker.client.components.widgets.SelectJobOption;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.PseudoJob;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.select.client.ui.OptGroup;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Shows the left Navigation in CapacityPage
 * This class handles the selection and view of Reports
 *
 * @author Philipp Minges
 */
public class CapacityNav extends Composite implements LogicObservable, CapaCalendarObserver {

    @UiField
    Select selectJob;

    @UiField
    TextArea description, myProjectsDescription;

    @UiField
    OptGroup myJobsOptGroup, allJobsOptGroup;

    @UiField
    Button buttonUp, buttonDown, buttonSave;

    @UiField
    TextBox possibilityPercentage, textIdentifier, myProjectsProjectName;

    @UiField
    Slider mySlider;

    @UiField
    TabListItem myProjects, newProject;

    @UiField
    TabPane newProjectPane, myProjectsPane;

    /**
     * This Method sets the possibility of the {@link UtilizationWeek}
     * @param event the slide event
     */
    @UiHandler("mySlider")
    void onSlide(SlideEvent<Double> event) {
        possibilityPercentage.setText(event.getValue().toString());
        currentUtilizationWeek.setPossibility(mySlider.getValue().intValue());
        notifyLogicHandler();
    }

    /**
     * This Method increases the possibility of the {@link UtilizationWeek} by 25%.
     * @param e {@link SlideEvent}
     */
    @UiHandler("buttonUp")
    void onClickUp(ClickEvent e) {
        if (mySlider.getValue() < 76)
            mySlider.setValue(mySlider.getValue() + 25);
        //refresh percentage TextBox
        possibilityPercentage.setText(mySlider.getValue().toString());
    }

    /**
     * This Method decreases the possibility of the {@link UtilizationWeek} by 25%.
     * @param e {@link ClickEvent}
     */
    @UiHandler("buttonDown")
    void onClickDown(ClickEvent e) {
        if (mySlider.getValue() > 24)
            mySlider.setValue(mySlider.getValue() - 25);
        possibilityPercentage.setText(mySlider.getValue().toString());
    }

    /**
     * This Method saves a new Pseudo Job to the Database
     * @param e {@link ClickEvent}
     */
    @UiHandler("buttonSave")
    public void savePseudoJob(final ClickEvent e) {
        PseudoJob template = new PseudoJob();
        if (textIdentifier.getText().length() > 0) {
            template.setName(textIdentifier.getText());
            template.setAuthor(handler.getCurrentUser().getId());
            template.setId(0);
            handler.savePseudoJob(template);
        } else {
            NotifyHelper.errorMessage("Fill missing fields");
        }
    }

    interface CapacityNavUiBinder extends UiBinder<Widget, CapacityNav> {
    }

    private static CapacityNavUiBinder uiBinder = GWT.create(CapacityNavUiBinder.class);

    private List<UtilizationWeek> utilizationWeekList = new ArrayList<UtilizationWeek>();
    private List<Job> jobList = new ArrayList<Job>();
    private List<PseudoJob> pJobList = new ArrayList<PseudoJob>();

    private UtilizationWeek currentUtilizationWeek = null;

    /**
     * This Method creates a new Instance of the Capacity Navigation.
     */
    public CapacityNav() {
        initWidget(uiBinder.createAndBindUi(this));

        handler.addObservable(this);
        handler.updateObservable(this);
        calendarHandler.addObserver(this);

        createUtilizationWeekList();
        
        initializeUIElements();
        selectJob.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                currentUtilizationWeek = ((SelectJobOption) selectJob.getSelectedItem()).getUtilizationWeek();
                notifyLogicHandler();

            }
        });

        handler.loadJobs();
        handler.loadPseudoJobs();
    }

    /**
     * Displays all Jobs and Pseudo Jobs in CapacityNav.
     */
    private void createUtilizationWeekList() {
        utilizationWeekList.clear();

        this.pJobList = handler.getPseudoJobList();
        this.jobList = handler.getJobList();

        for (PseudoJob pjob : pJobList) {
            UtilizationWeek uw = new UtilizationWeek(0, 0, pjob.getName(), new Date(),0, 0, new Date(), 0, pjob.getId(), 0);
            utilizationWeekList.add(uw);
        }
        for (Job job : jobList) {
            UtilizationWeek uw = new UtilizationWeek(999, 0, job.toString(), new Date(), 0, 0, new Date(), 0, 9999, 0);
            utilizationWeekList.add(uw);
        }
    }

    /**
     * Sets the Icons for the Buttons and the Value for the possibility Slider.
     */
    private void initializeUIElements() {
        buttonDown.setIcon(IconType.ARROW_DOWN);
        buttonUp.setIcon(IconType.ARROW_UP);
        possibilityPercentage.setText(mySlider.getValue().toString());
        description.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                notifyLogicHandler();
            }
        });
    }

    /**
     * Adds the {@link UtilizationWeek}s to the select box.
     * @param utilWeekList the {@link UtilizationWeek}
     */
    private void addUtilizationWeeks(List<UtilizationWeek> utilWeekList) {
        for (UtilizationWeek currentUtilWeek : utilWeekList) {
            if(currentUtilWeek.getId().intValue() == 999) {
                SelectJobOption tempOption = new SelectJobOption(currentUtilWeek);
                allJobsOptGroup.add(tempOption);
            } else {
                SelectJobOption tempOption = new SelectJobOption(currentUtilWeek);
                myJobsOptGroup.add(tempOption);
            }
        }
    }

    /**
     * This Method is called from the {@link LogicObservable}
     */
    @Override
    public void updateObservable() {
        allJobsOptGroup.clear();
        myJobsOptGroup.clear();
        textIdentifier.clear();

        createUtilizationWeekList();
        addUtilizationWeeks(this.utilizationWeekList);
        currentUtilizationWeek = handler.getTempUtilizationWeek();

        if (currentUtilizationWeek != null) {
            for (Option option : selectJob.getItems()) {
                ((SelectJobOption) option).setSelected(false);
                if (((SelectJobOption) option).getUtilizationWeek().equals(currentUtilizationWeek)) {
                    option.setSelected(true);
                }
            }
        }
        notifyLogicHandler();
        selectJob.refresh();
    }

    /**
     * This Method is called from the {@link CalendarObserver}
     */
    @Override
    public void update() {
        loadMyProjectPage();
    }

    /**
     * This Method notifies the {@link CalendarObserver} about changes.
     */
    @Override
    public void notifyHandler() {

    }

    /**
     * This Method notifies the {@link LogicObservable} about given changes
     */
    @Override
    public void notifyLogicHandler() {
        if(currentUtilizationWeek != null) {
            currentUtilizationWeek.setText(description.getText());
            currentUtilizationWeek.setPossibility(mySlider.getValue().intValue());
            GWT.log("notify logicHandler " + currentUtilizationWeek.getText());
            handler.setTempUtilizationWeek(currentUtilizationWeek);
        }
    }

    private void loadMyProjectPage(){

        myProjects.setActive(true);
        myProjectsPane.setActive(true);
        newProject.setActive(false);
        newProjectPane.setActive(false);

        myProjectsProjectName.setText("NAME MISSING");
        myProjectsDescription.setText(calendarHandler.getCurrentUtilizationWeek().getText());
        mySlider.setValue(calendarHandler.getCurrentUtilizationWeek().getPossibility().doubleValue());
        possibilityPercentage.setText(calendarHandler.getCurrentUtilizationWeek().getPossibility().toString());
    }
}