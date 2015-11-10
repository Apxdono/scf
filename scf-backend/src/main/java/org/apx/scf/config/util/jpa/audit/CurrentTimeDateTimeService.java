package org.apx.scf.config.util.jpa.audit;

import java.util.Calendar;

/**
 * Created by Oleg on 26.10.2015.
 */
public class CurrentTimeDateTimeService implements DateTimeService {
    @Override
    public Calendar getCurrentDateAndTime() {
        return Calendar.getInstance();
    }
}
