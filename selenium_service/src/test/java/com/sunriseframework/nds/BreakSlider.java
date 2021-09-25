package com.sunriseframework.nds;

import com.sunriseframework.nds.create.tesimg.ConvertBase64ToImg;
import com.sunriseframework.nds.socket.ConcurrentServer;
import com.sunriseframework.nds.socket.Server;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by chenhao on 2021/9/18.
 */
public class BreakSlider {

    @Test
    public void test() throws InterruptedException, IOException {
        WebDriver webDriver = Navigator.get();
        webDriver.get("https://www.jd.com/");
        WebElement loginBtn = webDriver.findElement(By.className("link-login"));
        loginBtn.click();
        WebElement loginTabByUsername = webDriver.findElement(By.linkText("账户登录"));
        loginTabByUsername.click();
        WebElement userNameInput = webDriver.findElement(By.id("loginname"));
        userNameInput.sendKeys("18323404264");//nloginpwd 18323404264
        WebElement passwordInput = webDriver.findElement(By.id("nloginpwd"));
        passwordInput.sendKeys("chenaixin123");//chenaixin123  qx15823297033
        WebElement loginSubmit = webDriver.findElement(By.id("loginsubmit"));
        loginSubmit.click();
        //JDJRV-slide-inner JDJRV-slide-btn
//        WebElement slideBtn = webDriver.findElement(By.className("JDJRV-slide-inner JDJRV-slide-btn"));
        By moveBtn = By.cssSelector(".JDJRV-slide-inner.JDJRV-slide-btn");//滑动验证按钮
        Wait.waitForLoad(webDriver, moveBtn);
        WebElement slideBtn = webDriver.findElement(moveBtn);

//        ConcurrentServer.start(webDriver,slideBtn);
//        Server.start();


        boolean loginFlag = true;
        loginFlag:
        while (true){
            Set<Cookie> cookies = webDriver.manage().getCookies();
            //登陆成功会返回这三个属性
//            unick = chenaiqinxin
//            _pst = chenaiqinxin
//            TrackID = 1q-vafW9dpQU9Cc7DUaJANtltCtvGGPe_foA2RU_FFnV8vLD96KcskkmCJuhoC2kYyzZdnc46OwgO0SeyO2zl_YL-3fDjN2CT3t5PgYrzlck
            for (Cookie c :cookies ) {
                if("unick".equals(c.getName())||"_pst".equals(c.getName())||
                        "TrackID".equals(c.getName())){
                    System.out.println(c.getName());
                    break loginFlag;
                }
            }
            WebElement bigImgParent = webDriver.findElement(By.className("JDJRV-bigimg"));
            WebElement bigImg = bigImgParent.findElement(By.xpath("//img[starts-with(@src,'data:image/png;base64,')]"));
            String bigImgSrc = bigImg.getAttribute("src");
            String bigImgBase64Str = bigImgSrc.substring(bigImgSrc.indexOf(",")+1, bigImgSrc.length());
            ConvertBase64ToImg.convert(bigImgBase64Str,"assets/images/","bigLogin.png");
            String temp = "assets/images/slideBlock.png";
            String bg = "assets/images/bigLogin.png";
            int dis = CountDistanceUtil.count(bg, temp);//计算缺口距离
            dis = DistanceScaleUtil.scaling(dis,0.23); //按比例缩放
            Move.move(webDriver, slideBtn, dis);
//            Thread.sleep(5000); //成功时停顿5秒
            Thread.sleep(2000); //停顿5秒


        }

        //id  = form  input type = "text"   button cw-icon
        WebElement searchParent = webDriver.findElement(By.id("search"));
        WebElement searchInput = searchParent.findElement(By.xpath("//input[@id='key']"));
        searchInput.sendKeys("电池");

        WebElement searchBtn = searchParent.findElement(By.xpath("//button[@class='button]"));
        searchBtn.click();

        //5秒等待加载商品
        Thread.sleep(3000);
        //gl-item
        WebElement order = null;
        WebElement goodsList = webDriver.findElement(By.id("J_goodsList"));
        List<WebElement> glItems =goodsList.findElements(By.xpath("//li[@class='gl-item']"));
        if(glItems != null && glItems.size()>0) {
            order = glItems.get(0);
        }else{
            System.out.println("无商品可以购买");
        }
        order.click();

        //加入购物车
        WebElement joinShopCart = webDriver.findElement(By.linkText("加入购物车"));
        joinShopCart.click();
        WebElement toShopCartAccount = webDriver.findElement(By.linkText("去购物车结算"));
        toShopCartAccount.click();
        WebElement toAccount = webDriver.findElement(By.linkText("去结算"));
        toAccount.click();
        WebElement commitOrder = webDriver.findElement(By.linkText("提交订单"));
        commitOrder.click();




    }


}
