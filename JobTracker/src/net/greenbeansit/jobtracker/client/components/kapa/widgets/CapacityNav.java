package net.greenbeansit.jobtracker.client.components.kapa.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.components.widgets.SelectJobOption;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.PseudoJob;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.select.client.ui.OptGroup;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows the left Navigation in CapacityPage
 * This class handles the selection and view of Reports
 *
 * @author Philipp Minges
 */
public class CapacityNav extends Composite implements LogicObservable {

    @UiField
    Select selectJob;

    @UiField
    OptGroup myJobsOptGroup, allJobsOptGroup;

    @UiField
    Button buttonUp, buttonDown, buttonSave;

    @UiField
    TextBox possibilityPercentage, textIdentifier;

    @UiField
    Slider mySlider;

    @UiHandler("mySlider")
    void onSlide(SlideEvent<Double> event) {
        possibilityPercentage.setText(event.getValue().toString());
    }

    @UiHandler("buttonUp")
    void onClickUp(ClickEvent e) {
        if (mySlider.getValue() < 76)
            mySlider.setValue(mySlider.getValue() + 25);
        //refresh percentage TextBox
        possibilityPercentage.setText(mySlider.getValue().toString());
    }

    @UiHandler("buttonDown")
    void onClickDown(ClickEvent e) {
        if (mySlider.getValue() > 24)
            mySlider.setValue(mySlider.getValue() - 25);
        possibilityPercentage.setText(mySlider.getValue().toString());
    }

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

    private List<Job> jobList = new ArrayList<Job>();
    private List<PseudoJob> pJobList = new ArrayList<PseudoJob>();

    private PseudoJob currentPJob = null;
    private Job currentJob = null;

    public CapacityNav() {
        initWidget(uiBinder.createAndBindUi(this));

        handler.addObservable(this);
        handler.updateObservable(this);

        Timer timer = new Timer() {
            @Override
            public void run() {
                initializeUIElements();
                selectJob.addValueChangeHandler(new ValueChangeHandler<String>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<String> event) {
                        if (selectJob.getSelectedItem().getText().contains("Pseudo")) {
                            currentPJob = ((SelectJobOption) selectJob.getSelectedItem()).getPjob();
                            notifyLogicHandler();
                        }
                        if (!selectJob.getSelectedItem().getText().contains("Pseudo")) {
                            currentJob = ((SelectJobOption) selectJob.getSelectedItem()).getJob();
                            notifyLogicHandler();
                        }
                    }
                });
            }
        };
        timer.schedule(100);

        handler.loadJobs();
        handler.loadPseudoJobs();
    }

    private void initializeUIElements() {
        buttonDown.setIcon(IconType.ARROW_DOWN);
        buttonUp.setIcon(IconType.ARROW_UP);
        possibilityPercentage.setText(mySlider.getValue().toString());
    }

    private void addJobs(List<Job> jobList) {
        for (Job currentJob : jobList) {
            SelectJobOption tempOption = new SelectJobOption(currentJob);
            allJobsOptGroup.add(tempOption);
        }
    }

    private void addPseudoJobs(List<PseudoJob> pJobList) {
        for (PseudoJob currentPJob : pJobList) {
            SelectJobOption tempOption = new SelectJobOption(currentPJob);
            myJobsOptGroup.add(tempOption);
        }
    }

    @Override
    public void updateObservable() {
        allJobsOptGroup.clear();
        myJobsOptGroup.clear();

        this.pJobList = handler.getPseudoJobList();
        this.jobList = handler.getJobList();

        addPseudoJobs(this.pJobList);
        addJobs(this.jobList);

        currentPJob = handler.getCurrentPseudoJob();
        currentJob = handler.getCurrentJob();
        if (currentJob != null || currentPJob != null) {
            for (Option option : selectJob.getItems()) {
                ((SelectJobOption) option).setSelected(false);

                if (((SelectJobOption) option).getText().contains("Pseudo")) {
                    if (((SelectJobOption) option).getPjob().equals(currentPJob)) {
                        option.setSelected(true);
                    }
                } else {
                    if (((SelectJobOption) option).getJob().equals(currentJob)) {
                        option.setSelected(true);
                    }
                }
            }
        }
        selectJob.refresh();
    }

    @Override
    public void notifyLogicHandler() {
        if (currentJob != null) {
            handler.setCurrentJob(currentJob);
            handler.setCurrentPJob(null);
        }
        if (currentPJob != null) {
            handler.setCurrentPJob(currentPJob);
            handler.setCurrentJob(null);
        }
    }
}