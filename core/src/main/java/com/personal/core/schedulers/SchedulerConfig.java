package com.personal.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Personal Scheduler Config",
        description = "Sling Scheduler Config"
)
public @interface SchedulerConfig {

    @AttributeDefinition(
            name = "Scheduler Name",
            description = "Name of the Scheduler",
            type = AttributeType.STRING)
    public String schedulerName() default "Custom Sling Scheduler Config";

    @AttributeDefinition(
            name = "Cron Expression",
            description = "Cron Expression used by the scheduler",
            type = AttributeType.STRING)
    public String cronExpression() default "0 0/1 * * * ? *";

}
