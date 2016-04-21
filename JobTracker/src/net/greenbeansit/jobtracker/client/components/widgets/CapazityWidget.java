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

public class CapazityWidget extends Composite {

	private static CapazityWidgetUiBinder uiBinder = GWT.create(CapazityWidgetUiBinder.class);

	interface CapazityWidgetUiBinder extends UiBinder<Widget, CapazityWidget> {
	}

	public CapazityWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
