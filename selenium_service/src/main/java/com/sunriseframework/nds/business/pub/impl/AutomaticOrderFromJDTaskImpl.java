package com.sunriseframework.nds.business.pub.impl;

import com.sunriseframework.nds.business.pub.interfaces.AutomaticOrderFromJDTask;
import com.sunriseframework.nds.service.pub.interfaces.AutoCommitOrderService;
import com.sunriseframework.nds.service.pub.interfaces.AutomaticLoginService;
import com.sunriseframework.nds.service.pub.interfaces.WebDriverInitService;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenhao on 2021/9/22.
 */
@Service("automaticOrderFromJDTask")
public class AutomaticOrderFromJDTaskImpl implements AutomaticOrderFromJDTask{

    @Autowired
    private WebDriverInitService webDriverInitService;

    @Autowired
    private AutomaticLoginService automaticLoginService;

    @Autowired
    private AutoCommitOrderService autoCommitOrderService;

    @Override
    public void execute() throws Exception {
        WebDriver webDriver = webDriverInitService.getWebDriver();
        boolean loginStatus = automaticLoginService.login(webDriver);
        if(loginStatus){
            autoCommitOrderService.commitOrder(webDriver);
        }
    }
}
