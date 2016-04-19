package net.greenbeansit.jobtracker.client.components.widgets;

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

public class CapazityActivity extends Composite {

	private static CapazityActivityUiBinder uiBinder = GWT.create(CapazityActivityUiBinder.class);

	interface CapazityActivityUiBinder extends UiBinder<Widget, CapazityActivity> {
	}

	public CapazityActivity() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
