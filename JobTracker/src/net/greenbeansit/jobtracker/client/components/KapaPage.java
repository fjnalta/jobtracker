package net.greenbeansit.jobtracker.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by Philipp Minges on 23.05.16.
 */
public class KapaPage extends Composite{
    interface KapaPageUiBinder extends UiBinder<Widget, KapaPage> {
    }

    private static KapaPageUiBinder uiBinder = GWT.create(KapaPageUiBinder.class);

    public KapaPage() {
        initWidget(uiBinder.createAndBindUi(this));
        initialize();
    }

    private void initialize()
    {

    }


}