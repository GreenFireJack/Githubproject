package com.sunriseframework.nds.config;

import com.sunriseframework.nds.business.pub.interfaces.AutomaticOrderFromJDTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by chenhao on 2021/9/22.
 */
@Configuration
@EnableScheduling
public class ScheduleTaskConfig {

    @Autowired
    private AutomaticOrderFromJDTask automaticOrderFromJDTask;

    @Scheduled(initialDelay = 10000, fixedRate = 1000*60*60*23)
    private void configureTasks() throws Exception {
        automaticOrderFromJDTask.execute();
    }
}
