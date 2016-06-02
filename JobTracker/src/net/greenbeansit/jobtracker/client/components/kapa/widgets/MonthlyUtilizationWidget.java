package net.greenbeansit.jobtracker.client.components.kapa.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import net.greenbeansit.jobtracker.client.components.CalendarObserver;
import net.greenbeansit.jobtracker.client.components.widgets.calendar.FullCalendarCustomize;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.IconType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Shows the utilization per month above the calendar
 *
 * @author Philipp Minges
 */
public class MonthlyUtilizationWidget extends Composite implements CalendarObserver {

    private static CalendarUtilizationWidgetUiBinder uiBinder = GWT.create(CalendarUtilizationWidgetUiBinder.class);

    /**
     * This Method is called from the {@link CalendarObserver}
     */
    @Override
    public void update() {
    }

    /**
     * This Method notifies the {@link CalendarObserver} about changes.
     */
    @Override
    public void notifyHandler() {
        calendarHandler.updateObserver(this);
    }

    interface CalendarUtilizationWidgetUiBinder extends UiBinder<Widget, MonthlyUtilizationWidget> {
    }

    @UiField
    FlexTable table;

    @UiField
    Button leftButton;

    @UiField
    Button rightButton;

    /**
     * This Method sets the previous Month of the {@link CapacityCalendarWidget}
     * @param e {@link ClickEvent}
     */
    @UiHandler("leftButton")
    public void clickHandlerLeftButton(ClickEvent e) {
        createNewTimeline();
		calendarHandler.calendar.previous();
        notifyHandler();
    }

    /**
     * This Method sets the next Month of the {@link CapacityCalendarWidget}
     * @param e {@link ClickEvent}
     */
    @UiHandler("rightButton")
    public void clickHandlerRightButton(ClickEvent e) {
        createNewTimeline();
		calendarHandler.calendar.next();
        notifyHandler();
    }

    // Path for the css File
    private final String suffixPath = "net-greenbeansit-jobtracker-client-components-widgets-calendar-CalendarUtilizationWidget_CalendarUtilizationWidgetUiBinderImpl_GenCss_style-";

    private String[] months = {"Jan", "Feb", "Mar", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dez"};
    private List<VerticalPanel> list;

    /**
     * Initializes a new Instance of the {@link MonthlyUtilizationWidget}. This Widget
     * shows the Utilization for every Month in this year.
     */
    public MonthlyUtilizationWidget() {

        calendarHandler.addObserver(this);

        initWidget(uiBinder.createAndBindUi(this));

        Timer timer = new Timer() {
            @Override
            public void run() {
                createNewTimeline();
            }
        };
        timer.schedule(300);

        this.leftButton.setIcon(IconType.ARROW_LEFT);
        this.rightButton.setIcon(IconType.ARROW_RIGHT);
    }

    /**
     * This method creates a new TimeLine
     */
    private void createNewTimeline() {
        table.removeAllRows();

        this.list = createBarChartList();
        for (int i = 0; i < 12; i++) {
            //add barcharts to first row
            table.setWidget(0, i, list.get(i));
            //and month buttons to second row
            final int calendarMonth = i;
            table.setWidget(1, i, new Button(months[i], new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    Date currentDate = calendarHandler.calendar.getDate();
                    currentDate.setMonth(calendarMonth);
                    calendarHandler.calendar.goToDate(currentDate);
                    notifyHandler();
                }
            }));
        }
    }

    /**
     * This Method creates a Vertical Panel for every Month
     * @return list the ArrayList of the Panels.
     */
    private List<VerticalPanel> createBarChartList() {
        List<VerticalPanel> list = new ArrayList<VerticalPanel>();

        for (int element = 0; element <= 11; element++) {
            list.add(getBarChart(null));
        }
        return list;
    }

    /**
     * Get the BarChart for a month
     *
     * @param date actual date
     * @return an new BarChart to the given date
     */
    private VerticalPanel getBarChart(Date date) {
        VerticalPanel vp = new VerticalPanel();

        double rnd = getBarChartHeight(date);

        vp.setHeight(rnd + "px");

        if (rnd < 40) {
            vp.setStyleName(this.suffixPath + "barChart", true);
        } else {
            vp.setStyleName(this.suffixPath + "barChartHeight", true);
        }
        return vp;
    }

    /**
     * get the barChart height of a day
     *
     * @param date
     * @return
     */
    private double getBarChartHeight(Date date) {

        //TODO read utilization from DB
        return Math.random() * 50;
    }
}
