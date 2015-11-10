package org.apx.scf.config.util.jpa.audit;

import org.springframework.data.auditing.DateTimeProvider;

import java.util.Calendar;

/**
 * Created by Oleg on 26.10.2015.
 */
public class AuditingDateTimeProvider implements DateTimeProvider {

    private final DateTimeService dateTimeService;

    public AuditingDateTimeProvider(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public Calendar getNow() {
        return dateTimeService.getCurrentDateAndTime();
    }
}