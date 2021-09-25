package com.sunriseframework.nds.service.pub.interfaces;

import org.openqa.selenium.WebDriver;

/**
 * Created by chenhao on 2021/9/22.
 */
public interface AutoCommitOrderService {

    void commitOrder(WebDriver webDriver) throws InterruptedException;
}
