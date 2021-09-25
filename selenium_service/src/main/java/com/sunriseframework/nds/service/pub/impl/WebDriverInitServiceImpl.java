package com.sunriseframework.nds.service.pub.impl;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.map.MapBuilder;
import com.sunriseframework.nds.service.pub.interfaces.WebDriverInitService;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chenhao on 2021/9/22.
 */
@Service("webDriverInitService")
public class WebDriverInitServiceImpl implements WebDriverInitService {
    @Override
    public WebDriver getWebDriver() {

        // 加载驱动
//        URL fileURL=this.getClass().getResource("/chromedriver.exe");
//        String webDriverDir = fileURL.getFile();
        String webDriverDir = System.getProperty("user.dir")+"/chromedriver.exe";//根目录命令行访问

//        String webDriverDir = "./chromedriver_win32/chromedriver.exe"; //idea中编码时访问
        System.setProperty("webdriver.chrome.driver", webDriverDir);

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
        options.addArguments("--disable-blink-features=AutomationControlled");//window.navigator.webdriver
        options.setExperimentalOption("useAutomationExtension", false);

        options.setExperimentalOption("prefs", prefs);

        // 创建驱动对象 ChromeDriver实现了WebDriver接口
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1280, 1024));

        // 去除seleium全部指纹特征
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = System.getProperty("user.dir")+"/assets/js/stealth.min.js";//根目录命令行访问
//        URL resource = classLoader.getResource("assets/js/stealth.min.js");
        File file = new File(resourcePath);
        FileReader fileReader = new FileReader(file);

        String js = fileReader.readString();
        Map<String, Object> commandMap = MapBuilder.create(new LinkedHashMap<String, Object>()).put("source", js)
                .build();
        ((ChromeDriver) driver).executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", commandMap);
        return driver;
    }
}
