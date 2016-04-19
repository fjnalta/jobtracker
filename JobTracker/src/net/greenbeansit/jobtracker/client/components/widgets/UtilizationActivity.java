package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.Container;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class UtilizationActivity extends Composite {

	private static UtilizationActivityUiBinder uiBinder = GWT.create(UtilizationActivityUiBinder.class);

	interface UtilizationActivityUiBinder extends UiBinder<Widget, UtilizationActivity> {
	}

	
	public UtilizationActivity() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
