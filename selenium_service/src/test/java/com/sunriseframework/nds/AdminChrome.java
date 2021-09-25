package com.sunriseframework.nds;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by chenhao on 2021/9/21.
 */
public class AdminChrome {

    private final static String CHROME_DRIVER_NAME = "webdriver.chrome.driver";
    private final static String CHROME_DRIVER_87_PATH = "C:\\drive\\chromedriver-87.exe";

    public static void main(String[] args) {
        System.setProperty(CHROME_DRIVER_NAME, CHROME_DRIVER_87_PATH);
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        System.out.println(driver.getTitle());
        WebElement element = driver.findElement(By.xpath("//*[@id=\"sb_form_q\"]"));
        element.sendKeys("Java");
        element.sendKeys(Keys.ENTER);

        System.out.println(driver.getTitle());
    }

}
