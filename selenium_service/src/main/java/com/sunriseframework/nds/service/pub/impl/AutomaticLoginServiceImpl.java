package com.sunriseframework.nds.service.pub.impl;

import com.sunriseframework.nds.config.EnProperties;
import com.sunriseframework.nds.config.PropertiesUtil;
import com.sunriseframework.nds.service.pub.interfaces.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by chenhao on 2021/9/22.
 */
@Service("automaticLoginService")
public class AutomaticLoginServiceImpl implements AutomaticLoginService{
    private static Logger logger = LoggerFactory.getLogger(AutomaticLoginServiceImpl.class);

    @Autowired
    private ConvertBase64ToImgService convertBase64ToImgService;

    @Autowired
    private DistanceAccountService distanceAccountService;

    @Autowired
    private DistanceScaleService distanceScaleService;

    @Autowired
    private MoveService moveService;

    @Autowired
    private PropertiesUtil propertiesUtil;


    @Override
    public boolean login(WebDriver webDriver) {
        logger.info("AutomaticLoginService.login()+++++++++++++++++++++begin++++++++++++++");
        String requestUrl = propertiesUtil.getValue(EnProperties.REQUEST_URL);
        webDriver.get(requestUrl);
        By toLoginBy = By.className(propertiesUtil.getValue(EnProperties.TO_LOGIN));
        WebElement loginBtn = webDriver.findElement(toLoginBy);
        loginBtn.click();
        By checkLoginWayBy = By.linkText(propertiesUtil.getValue(EnProperties.CHECK_LOG_WAY));
        WebElement loginTabByUsername = webDriver.findElement(checkLoginWayBy);
        loginTabByUsername.click();
        By userNameInputBy = By.id(propertiesUtil.getValue(EnProperties.USERNAME_INPUT));
        WebElement userNameInput = webDriver.findElement(userNameInputBy);
        userNameInput.sendKeys(propertiesUtil.getValue(EnProperties.USERNAME));
        By passwordInputBy = By.id(propertiesUtil.getValue(EnProperties.PASSWORD_INPUT));
        WebElement passwordInput = webDriver.findElement(passwordInputBy);
        passwordInput.sendKeys(propertiesUtil.getValue(EnProperties.PASSWORD));
        By loginSubmitBy = By.id(propertiesUtil.getValue(EnProperties.LOGIN_SUBMIT));
        WebElement loginSubmit = webDriver.findElement(loginSubmitBy);
        loginSubmit.click();
        By slideBtnBy = By.cssSelector(propertiesUtil.getValue(EnProperties.LOGIN_SLIDE_VALIDATION_BTN));
//        Wait.waitForLoad(webDriver, moveBtn);
        WebElement slideBtn = webDriver.findElement(slideBtnBy);

//        ConcurrentServer.start(webDriver,slideBtn);
//        Server.start();


        boolean loginIsSuccess = true;
        loginIsSuccess:
        while (true){
            Set<Cookie> cookies = webDriver.manage().getCookies();
            //登陆成功会返回这三个属性 unick = chenaiqinxin , _pst = chenaiqinxin ,TrackID = 1q-vafW9dpQU9Cc7DUaJANtltCtvGGPe_foA2RU_FFnV8vLD96KcskkmCJuhoC2kYyzZdnc46OwgO0SeyO2zl_YL-3fDjN2CT3t5PgYrzlck
            String[] loginSuccessFlags = propertiesUtil.getValue(EnProperties.LOGIN_IS_SUCCESS).split(",");
            for (Cookie c :cookies ) {
                if(loginSuccessFlags[0].equals(c.getName())||loginSuccessFlags[1].equals(c.getName())||
                        loginSuccessFlags[2].equals(c.getName())){
                    logger.info("login success:"+"Cookie["+c.getName()+" = "+c.getValue()+"]");
                    break loginIsSuccess;
                }
            }
            By bigImgParentNodeBy = By.className(propertiesUtil.getValue(EnProperties.BIG_IMG_PARENT_NODE));
            WebElement bigImgParent = webDriver.findElement(bigImgParentNodeBy);
            By bigImgBy = By.xpath(propertiesUtil.getValue(EnProperties.BIG_IMG_XPATH));
            WebElement bigImg = bigImgParent.findElement(bigImgBy);
            String bigImgSrc = bigImg.getAttribute("src");
            String bigImgBase64Str = bigImgSrc.substring(bigImgSrc.indexOf(",") + 1, bigImgSrc.length());
            String bigImgLocalPath = propertiesUtil.getValue(EnProperties.BIG_IMG_LOCALPATH);
            String bigImgFileName = propertiesUtil.getValue(EnProperties.BIG_IMG_FILENAME);
            convertBase64ToImgService.convert(bigImgBase64Str, bigImgLocalPath, bigImgFileName);
            String templateImgPath = propertiesUtil.getValue(EnProperties.TEMPLATE_IMG_LOCAL_PATH);
            String backgroundImgPath = propertiesUtil.getValue(EnProperties.BIG_IMG_LOCALPATH)+propertiesUtil.getValue(EnProperties.BIG_IMG_FILENAME);
            int dis = distanceAccountService.account(backgroundImgPath, templateImgPath);//计算缺口距离
            double scaleRadio = Double.parseDouble(propertiesUtil.getValue(EnProperties.MOVE_DISTANCE_SCALE_RADIO));
            dis = distanceScaleService.scaling(dis,scaleRadio); //按比例缩放
            try {
                moveService.move(webDriver, slideBtn, dis);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            Thread.sleep(5000); //成功时停顿5秒
            int pauseTime = Integer.parseInt(propertiesUtil.getValue(EnProperties.SLIDE_VALIDATION_PAUSE_TIME));
            try {
                Thread.sleep(pauseTime); //停顿5秒
            } catch (InterruptedException e) {
//                logger.error();
                e.printStackTrace();
            }


        }
        return true;
    }
}
