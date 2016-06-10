package net.greenbeansit.jobtracker.client.components.kapa.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import net.greenbeansit.jobtracker.client.components.kapa.data.CustomerOption;
import net.greenbeansit.jobtracker.client.components.widgets.SelectJobOption;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Customer;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.PseudoJob;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;
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
    Select selectJob,selectCustomer;

    @UiField
    TextArea description;

    @UiField
    OptGroup myJobsOptGroup, allJobsOptGroup;

    @UiField
    Button buttonUp, buttonDown, buttonSave;

    @UiField
    TextBox possibilityPercentage, textIdentifier;

    @UiField
    Slider mySlider;

    @UiField
    TabListItem myProjects, newProject;

    /**
     * This Method sets the possibility of the {@link UtilizationWeek}
     *
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
     *
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
     *
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
     *
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
    private Customer currentCustomer = null;

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
        selectCustomer.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                currentCustomer = ((CustomerOption) selectCustomer.getSelectedItem()).getCustomer();
                notifyLogicHandler();

            }
        });

        newProject.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                description.setText("");
            }
        });

        handler.loadJobs();
        handler.loadPseudoJobs();
        handler.loadAllCustomers();
    }

    /**
     * Displays all Jobs and Pseudo Jobs in CapacityNav.
     */
    private void createUtilizationWeekList() {
        utilizationWeekList.clear();

        this.pJobList = handler.getPseudoJobList();
        this.jobList = handler.getJobList();

        for (PseudoJob pjob : pJobList) {
            UtilizationWeek uw = new UtilizationWeek(0, 0, pjob.getName(), pjob.getName() ,new Date(), 0, 0, new Date(), 0, pjob.getId(), 0);
            utilizationWeekList.add(uw);
        }
        for (Job job : jobList) {
            UtilizationWeek uw = new UtilizationWeek(999, 0, job.toString(), job.getDesc(), new Date(), 0, 0, new Date(), 0, 9999, 0);
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
     *
     * @param utilWeekList the {@link UtilizationWeek}
     */
    private void addUtilizationWeeks(List<UtilizationWeek> utilWeekList) {
        for (UtilizationWeek currentUtilWeek : utilWeekList) {
            if (currentUtilWeek.getId().intValue() == 999) {
                SelectJobOption tempOption = new SelectJobOption(currentUtilWeek);
                allJobsOptGroup.add(tempOption);
            } else {
                SelectJobOption tempOption = new SelectJobOption(currentUtilWeek);
                myJobsOptGroup.add(tempOption);
            }
        }
    }

    private void addCustomers(List<Customer> customerList){
        for(Customer customer : customerList){
            CustomerOption tempOption = new CustomerOption(customer);
            selectCustomer.add(tempOption);
        }
    }

    /**
     * This Method is called from the {@link LogicObservable}
     */
    @Override
    public void updateObservable() {
        allJobsOptGroup.clear();
        myJobsOptGroup.clear();
        selectCustomer.clear();
        textIdentifier.clear();
        utilizationWeekList.clear();

        createUtilizationWeekList();
        addCustomers(handler.getCustomerList());
        addUtilizationWeeks(this.utilizationWeekList);
        currentUtilizationWeek = handler.getCurrentUtilizationWeek();

        if (currentUtilizationWeek != null) {
            for (Option option : selectJob.getItems()) {
            	if(option instanceof SelectJobOption)
            	{
            		((SelectJobOption) option).setSelected(false);
            		if (((SelectJobOption) option).getUtilizationWeek().getName().equals(currentUtilizationWeek.getName())) {
            			option.setSelected(true);
            		}
            	}
            }
            for (Option option : selectCustomer.getItems()) {
                ((CustomerOption) option).setSelected(false);
                if (((CustomerOption) option).getCustomer().getId().equals(currentUtilizationWeek.getCustomerId())) {
                    option.setSelected(true);
                }
            }
        }

        notifyLogicHandler();
        selectJob.refresh();
        selectCustomer.refresh();
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
        if (currentUtilizationWeek != null&& currentCustomer!=null) {
            currentUtilizationWeek.setText(description.getText());
            currentUtilizationWeek.setPossibility(mySlider.getValue().intValue());
            currentUtilizationWeek.setCustomerId(currentCustomer.getId());
            handler.setTempUtilizationWeek(currentUtilizationWeek);
        }
    }

    private void loadMyProjectPage() {
        if (calendarHandler.getCurrentUtilizationWeek() != null) {
            myProjects.showTab();
            description.setText(calendarHandler.getCurrentUtilizationWeek().getText());
            mySlider.setValue(calendarHandler.getCurrentUtilizationWeek().getPossibility().doubleValue());
            possibilityPercentage.setText(calendarHandler.getCurrentUtilizationWeek().getPossibility().toString());

            for (Option option : selectJob.getItems()) {
                GWT.log("DONE!");
                GWT.log(((SelectJobOption) option).getUtilizationWeek().getName());
                GWT.log(calendarHandler.getCurrentUtilizationWeek().getName());
                GWT.log(currentUtilizationWeek.getName());
                ((SelectJobOption) option).setSelected(false);
                if (((SelectJobOption) option).getUtilizationWeek().getName().equals(calendarHandler.getCurrentUtilizationWeek().getName())) {
//                if (((SelectJobOption) option).getUtilizationWeek().getName().equals(currentUtilizationWeek.getName())) {
                    option.setSelected(true);
                }
            }

        } else {
            newProject.showTab();
        }
    }
}