package net.greenbeansit.jobtracker.client.components.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class represents the capacity widget on the HomePage.
 *
 * @author Jonathan Brenner.
 */
public class CapazityWidget extends Composite{

	private static CapazityWidgetUiBinder uiBinder = GWT.create(CapazityWidgetUiBinder.class);

	interface CapazityWidgetUiBinder extends UiBinder<Widget, CapazityWidget> {
	}

	/**
	 * Initializes a new Instance of CapazityWidget
	 */
	public CapazityWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	// TODO - this class is never used.

}
