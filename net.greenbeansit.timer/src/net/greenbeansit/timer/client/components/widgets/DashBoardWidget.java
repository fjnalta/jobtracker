package net.greenbeansit.timer.client.components.widgets;

import org.gwtbootstrap3.client.ui.Container;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DashBoardWidget extends Composite
{
	private static DashBoardWidgetUiBinder uiBinder =
			GWT.create(DashBoardWidgetUiBinder.class);

	interface DashBoardWidgetUiBinder extends UiBinder<Widget, DashBoardWidget>
	{
		
	}
	
	private DashBoardActivity activity;
	
	@UiField
	Container content;

	@UiHandler("buttonClose")
	void onClickCloseButton(ClickEvent e)
	{
		activity.onClose();
		
		this.removeFromParent();
	}
	
	
	
	public DashBoardWidget(DashBoardActivity activity)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		this.activity = activity;
		
		content.add(activity);
	}


	
}
