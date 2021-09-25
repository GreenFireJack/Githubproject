package com.sunriseframework.nds;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

/**
 * Created by chenhao on 2021/9/18.
 */
public class MainTest {

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\chenhao\\Desktop\\chromedriver_win32\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.jd.com/");
//        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
//        WebElement kw = webDriver.findElement(By.id("kw"));
        List<WebElement> loginBtnList = webDriver.findElements(By.className("link-login"));
        WebElement loginBtn = null;
        if(loginBtnList !=null&&loginBtnList.size()>0){
            loginBtn = loginBtnList.get(0);
        }
        loginBtn.click();
        WebElement loginTabByUsername = webDriver.findElement(By.linkText("账户登录"));
        loginTabByUsername.click();
        WebElement userNameInput = webDriver.findElement(By.id("loginname"));
        userNameInput.sendKeys("18323404264");//nloginpwd
        WebElement passwordInput = webDriver.findElement(By.id("nloginpwd"));
        passwordInput.sendKeys("chenaixin123");
        WebElement loginSubmit = webDriver.findElement(By.id("loginsubmit"));
        loginSubmit.click();
//        WebElement smallDiv = webDriver.findElement(By.className("JDJRV-smallimg"));//滑块class = JDJRV-smallimg
//        Point location = smallDiv.getLocation();
//        smallDiv.getSize();
////        Actions action = new Actions(webDriver);
//        while (true){
//            action.clickAndHold(smallDiv).perform();
//            action.dragAndDropBy(smallDiv, location.x+0,location.y).perform();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            String text = webDriver.findElement(By.className("JDJRV-slide-center")).getText();
//            if(text.equals("拼接成功")){//JDJRV-slide-center
//               break;
//            }
//        }
//        String pageSource = webDriver.getPageSource();
//webDriver.close();
//        System.out.println("Hello World!");
    }
}
