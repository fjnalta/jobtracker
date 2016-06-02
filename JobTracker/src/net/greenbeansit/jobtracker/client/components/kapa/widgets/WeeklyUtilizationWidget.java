package net.greenbeansit.jobtracker.client.components.kapa.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.CommandCanceledException;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Philipp Minges on 01.06.16.
 */
public class WeeklyUtilizationWidget extends Composite {

    /**
     * UiBinder Interface for {@link WeeklyUtilizationWidget}
     */
    interface WeeklyUtilizationWidgetUiBinder extends UiBinder<Widget, WeeklyUtilizationWidget> {
    }

    private static WeeklyUtilizationWidgetUiBinder uiBinder = GWT.create(WeeklyUtilizationWidgetUiBinder.class);

    // Path for the css File
    private final String suffixPath = "net-greenbeansit-jobtracker-client-components-widgets-calendar-CalendarUtilizationWidget_CalendarUtilizationWidgetUiBinderImpl_GenCss_style-";

    @UiField
    FlexTable table;
    private List<HorizontalPanel> list;

    /**
     * constructor
     */
    public WeeklyUtilizationWidget() {
        initWidget(uiBinder.createAndBindUi(this));

        Timer timer = new Timer() {
            @Override
            public void run() {
                createNewUtilizationOverview();
            }
        };
        timer.schedule(300);
    }

    /**
     * creates a new utilization overview
     */
    private void createNewUtilizationOverview(){
        table.removeAllRows();

        this.list = createBarChartList();
        for (int i = 0; i < 6; i++) {
            //add barcharts to first row
            table.setWidget(i, 0, list.get(i));
        }
    }

    /**
     * creates a new bar chart list
     * @return List<HorizontalPanel> with the charts
     */
    // TODO - need to turn the bars 90°
    private List<HorizontalPanel> createBarChartList() {
        List<HorizontalPanel> list = new ArrayList<HorizontalPanel>();

        for (int element = 0; element < 6; element++) {
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
    private HorizontalPanel getBarChart(Date date) {

        HorizontalPanel vp = new HorizontalPanel();

        double rnd = getBarChartHeight(date);

        vp.setHeight("7px");
        vp.setWidth(rnd + "px");

        if (rnd < 40) {
            vp.setStyleName(this.suffixPath + "barChartHorizontal", true);
        } else {
            vp.setStyleName(this.suffixPath + "barChartHeightHorizontal", true);
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