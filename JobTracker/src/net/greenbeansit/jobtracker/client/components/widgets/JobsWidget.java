package net.greenbeansit.jobtracker.client.components.widgets;

import java.util.ArrayList;
import java.util.List;

import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.select.client.ui.OptGroup;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.localization.HomePageConstants;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * The Widget for selecting the different Jobs for a Report
 *
 * @author Alexander Kirilyuk
 */
public class JobsWidget extends Composite implements LogicObservable {

    private static JobsWidgetUiBinder uiBinder = GWT.create(JobsWidgetUiBinder.class);

    private static HomePageConstants constants = GWT.create(HomePageConstants.class);

    /**
     * UiBinder interface for {@link JobsWidget}
     */
    interface JobsWidgetUiBinder extends UiBinder<Widget, JobsWidget> {

    }


    @UiField
    ClearFix container;

    @UiField
    Select selectJob;

    @UiField
    OptGroup favoriteJobsOptGroup;

    @UiField
    OptGroup allJobsOptGroup;

    private Job currentJob = null;
    private List<Job> jobList = new ArrayList<Job>();

    /**
     * standard constructor for the JobsWidget class
     * it initializes the uiBinder, registers itselft to the LogicHandler and add a valueChangeHandler to the liveSearch
     * selectJob
     * After that it calls the handler.loadJobs() to load the jobs from the backend
     */
    public JobsWidget() {
        initWidget(uiBinder.createAndBindUi(this));

        handler.addObservable(this);
        selectJob.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                currentJob = ((SelectJobOption) selectJob.getSelectedItem()).getJob();
                notifyLogicHandler();
            }
        });
        handler.loadJobs();

        selectJob.setPlaceholder(constants.selectJobPlaceHolder());
        selectJob.setLiveSearchPlaceholder(constants.selectJobLiveSearchPlaceHolder());
        favoriteJobsOptGroup.setLabel(constants.groupFavoriteJobs());
        allJobsOptGroup.setLabel(constants.groupAllOtherJobs());

    }

    /**
     * method for loading the jobs to the LiveSearch
     *
     * @param jobList List<Job> with the Jobs to load
     */
    private void addJobs(List<Job> jobList) {
        for (Job currentJob : jobList) {
            SelectJobOption tempOption = new SelectJobOption(currentJob);
            String customerName = handler.getCustomerNameForID(currentJob.getCustomerID()).getName();
            tempOption.setText(currentJob.getJobNr() + "-" + currentJob.getPosNr() + "-" + currentJob.getPayMode()
                    + " | " + customerName + " | " + currentJob.getDesc());
            allJobsOptGroup.add(tempOption);
        }
    }

    /**
     * Interface method of the {@link LogicObservable}
     * Needed for updating the widget on new data.
     */
    @Override
    public void updateObservable() {
        allJobsOptGroup.clear();
        this.jobList = handler.getJobList();
        addJobs(this.jobList);
        currentJob = handler.getCurrentJob();
        if (currentJob != null) {
            for (Option opt : selectJob.getItems()) {
                ((SelectJobOption) opt).setSelected(false);
                if (((SelectJobOption) opt).getJob().equals(currentJob)) {
                    opt.setSelected(true);
                }
            }
        }
        selectJob.refresh();

    }

    /**
     * Interface method of the {@link LogicObservable}
     * Needet to submit the selected job to LogicHandler
     */
    @Override
    public void notifyLogicHandler() {
        if (currentJob != null) {
            handler.setCurrentJob(currentJob);
        } else {
            NotifyHelper.errorMessage("Wählen sie bitte einen Job aus");
        }
    }
}
