package com.sunriseframework.nds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by chenhao on 2021/9/19.
 */
public class Wait {

    public static void waitForLoad(final WebDriver driver, final By by) {
        new WebDriverWait(driver,10).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                WebElement element = driver.findElement(by);
                if(element !=null){
                    return true;
                }
                return false;
            }
        });
    }
}
