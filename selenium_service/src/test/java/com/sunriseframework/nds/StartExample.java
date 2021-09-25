package com.sunriseframework.nds;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by chenhao on 2021/9/18.
 */
public class StartExample {

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\chenhao\\Desktop\\chromedriver_win32\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://www.baidu.com");
        WebElement kw = webDriver.findElement(By.id("kw"));
        kw.sendKeys("暗算");
        WebElement su = webDriver.findElement(By.id("su"));
        su.click();
        //webDriver.close();
        System.out.println("Hello World!");
    }
}
