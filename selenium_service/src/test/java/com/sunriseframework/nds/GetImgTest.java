package com.sunriseframework.nds;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by chenhao on 2021/9/18.
 */
public class GetImgTest {

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\chenhao\\Desktop\\chromedriver_win32\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://www.baidu.com");
        WebElement lg = webDriver.findElement(By.id("lg"));
        WebElement baiDuImg = lg.findElement(By.xpath("//img[starts-with(@src,'//www.baidu.com')]"));
        String src = baiDuImg.getAttribute("src");
        System.out.println(src);
        //webDriver.close();
        System.out.println("Hello World!");
    }
}
