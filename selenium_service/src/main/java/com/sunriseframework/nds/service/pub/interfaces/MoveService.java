package com.sunriseframework.nds.service.pub.interfaces;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by chenhao on 2021/9/22.
 */
public interface MoveService {

    void move(WebDriver driver, WebElement element, int distance) throws Exception;
}
