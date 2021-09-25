package com.sunriseframework.nds;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by chenhao on 2021/9/18.
 */
public class ErrorTest {

    @Test
    public void test() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("disable-infobars");
//        options.setExperimentalOption("detach",false);
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\chenhao\\Desktop\\chromedriver_win32\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://www.baidu.com");
        //导致打不开网站
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello word");

    }
}
