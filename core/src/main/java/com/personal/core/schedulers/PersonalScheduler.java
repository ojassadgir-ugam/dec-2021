package com.personal.core.schedulers;

import com.google.common.util.concurrent.AbstractScheduledService;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true,service= Runnable.class)
@Designate(ocd = SchedulerConfig.class)
public class PersonalScheduler implements Runnable{

    static final Logger LOG = LoggerFactory.getLogger(PersonalScheduler.class);
    int schedulerID;

    @Reference
    private Scheduler scheduler;

    @Activate
    protected void activate(SchedulerConfig config) {
        schedulerID = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SchedulerConfig config) {
        removeScheduler();
    }

    private void removeScheduler(){
        scheduler.unschedule(String.valueOf(schedulerID));
    }

    private void addScheduler(SchedulerConfig config){
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerID));
        scheduleOptions.canRunConcurrently(false);
        scheduler.schedule(this,scheduleOptions);
        LOG.info("\n--------------------- Scheduler Added ----------------------");
    }

    public void run() {
        LOG.info("\n================RUN METHOD EXECUTING ==================");
    }
}
