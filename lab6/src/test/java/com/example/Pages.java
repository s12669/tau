package com.example;

import com.example.pages.HelpDesk;
import org.jbehave.web.selenium.WebDriverProvider;

public class Pages {

    private WebDriverProvider driverProvider;

    //Pages -- moze byc ich kilka
    private HelpDesk helpDesk;

    public Pages(WebDriverProvider driverProvider) {
        super();
        this.driverProvider = driverProvider;
    }

    public HelpDesk helpdesk() {
        if (helpDesk == null) {
            helpDesk = new HelpDesk(driverProvider);
        }
        return helpDesk;
    }
}
