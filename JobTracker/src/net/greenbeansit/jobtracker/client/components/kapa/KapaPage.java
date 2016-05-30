package net.greenbeansit.jobtracker.client.components.kapa;

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
import net.greenbeansit.jobtracker.client.components.widgets.SelectJobOption;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.PseudoJob;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;
import org.gwtbootstrap3.extras.select.client.ui.OptGroup;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philipp Minges on 23.05.16.
 */
public class KapaPage extends Composite implements CalendarObserver {

    @UiField
    Button  buttonUpYearStart, buttonDownYearStart,
            buttonUpCalendarWeekStart, buttonDownCalendarWeekStart,
            buttonUpYearEnd, buttonDownYearEnd, buttonUpDayWeek,
            buttonDownDayWeek, buttonUpCalendarWeekEnd, buttonDownCalendarWeekEnd;

    @UiField
    Row content;

    @UiField
    Heading dateHeading;

    interface KapaPageUiBinder extends UiBinder<Widget, KapaPage> {
    }

    private static KapaPageUiBinder uiBinder = GWT.create(KapaPageUiBinder.class);

    FullCalendar fc = new FullCalendar("kapaCalendar", ViewOption.month, true);

    public KapaPage() {
        initWidget(uiBinder.createAndBindUi(this));
        initialize();
    }

    private void initialize() {
        //set Buttons
        setButtons();
        //load calendar
        content.add(fc);
    }

    @Override
    public void update() {
        fc.render();
    }

    @Override
    public void notifyHandler() {
        calendarHandler.updateObserver(this);
    }

    private void setButtons(){

        buttonUpYearStart.setIcon(IconType.ARROW_UP);
        buttonDownYearStart.setIcon(IconType.ARROW_DOWN);

        buttonUpCalendarWeekStart.setIcon(IconType.ARROW_UP);
        buttonDownCalendarWeekStart.setIcon(IconType.ARROW_DOWN);

        buttonUpYearEnd.setIcon(IconType.ARROW_UP);
        buttonDownYearEnd.setIcon(IconType.ARROW_DOWN);

        buttonUpCalendarWeekEnd.setIcon(IconType.ARROW_UP);
        buttonDownCalendarWeekEnd.setIcon(IconType.ARROW_DOWN);

        buttonUpDayWeek.setIcon(IconType.ARROW_UP);
        buttonDownDayWeek.setIcon(IconType.ARROW_DOWN);
    }
}