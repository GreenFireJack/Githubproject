package com.sunriseframework.nds;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.map.MapBuilder;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * Created by chenhao on 2021/9/19.
 */
public class Navigator {

//    @Test
    public static WebDriver get() throws FileNotFoundException {
        String webDriverDir = "C:\\Users\\chenhao\\Desktop\\chromedriver_win32\\chromedriver.exe";
        // 加载驱动
        System.setProperty("webdriver.chrome.driver", webDriverDir);

//        "C:\Users\chenhao\AppData\Local\Google\Chrome\Application\chrome.exe" --remote-debugging-port=9222 --user-data-dir="C:selenumAutomationProfile"

        // 设置浏览器参数
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        /**
        excludeSwitches", Arrays.asList("enable-automation")在高版本的谷歌浏览器是无法屏蔽
        window.navigator.webdriver 为false 的特征，这里写出来是为了配合其他参数来关闭浏览器上显示"正在收到自动测试软件控制"的提示
        **/

        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
//        options.addArguments("--headless"); //隐藏窗口
        options.addArguments("--disable-blink-features");
        options.addArguments("--disable-blink-features=AutomationControlled");//隐藏 window.navigator.webdriver
        options.setExperimentalOption("useAutomationExtension", false);

        options.setExperimentalOption("prefs", prefs);

        // 创建驱动对象 ChromeDriver实现了WebDriver接口
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1280, 1024));

        // 去除seleium全部指纹特征
        ClassLoader classLoader = Navigator.class.getClassLoader();
//        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("assets/js/stealth.min.js");
        File file = new File(resource.getFile());
        FileReader fileReader = new FileReader(file);
        String js = fileReader.readString();
        // MapBuilder是依赖hutool工具包的api
        Map<String, Object> commandMap = MapBuilder.create(new LinkedHashMap<String, Object>()).put("source", js)
                .build();
        // executeCdpCommand这个api在selenium3中是没有的,请使用selenium4才能使用此api
        ((ChromeDriver) driver).executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", commandMap);
        driver.get("https://bot.sannysoft.com");
        return driver;
    }

}
