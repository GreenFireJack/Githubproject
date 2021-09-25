package com.sunriseframework.nds.service.pub.impl;

import com.sunriseframework.nds.config.EnProperties;
import com.sunriseframework.nds.config.PropertiesUtil;
import com.sunriseframework.nds.service.pub.interfaces.AutoCommitOrderService;
import com.sunriseframework.nds.service.pub.interfaces.WebDriverInitService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by chenhao on 2021/9/22.
 */
@Service("autoCommitOrderService")
public class AutoCommitOrderServiceImpl implements AutoCommitOrderService {

    private static Logger logger = LoggerFactory.getLogger(AutoCommitOrderServiceImpl.class);

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Override
    public void commitOrder(WebDriver webDriver) throws InterruptedException {
        logger.info("AutoCommitOrderService.login()+++++++++++++++++++++begin++++++++++++++");
        By searchParentBy = By.id(propertiesUtil.getValue(EnProperties.JDGOODS_SEARCH_PARENT_NODE_ID));
        WebElement searchParent = webDriver.findElement(searchParentBy);
        By searchInputSendKeysBy = By.xpath(propertiesUtil.getValue(EnProperties.JDGOODS_SEARCH_INPUT_XPATH));
        WebElement searchInput = searchParent.findElement(searchInputSendKeysBy);
        searchInput.sendKeys(propertiesUtil.getValue(EnProperties.JDGOODS_SEARCH_INPUT_SENDKEYS));

        By searchBtnBy = By.xpath(propertiesUtil.getValue(EnProperties.JDGOODS_SEARCH_BTN_XPATH));
        WebElement searchBtn = searchParent.findElement(searchBtnBy);
        searchBtn.click();

        //5秒等待加载商品
        Thread.sleep(3000);

        WebElement order = null;
        By goodsResultSetBy = By.id(propertiesUtil.getValue(EnProperties.JDGOODS_RESULTSET_ID));
        WebElement goodsList = webDriver.findElement(goodsResultSetBy);
        By goodsItemBy = By.xpath(propertiesUtil.getValue(EnProperties.JDGOODS_RESULTSET_ITEM_XPATH));
        List<WebElement> glItems =goodsList.findElements(goodsItemBy);
        if(glItems != null && glItems.size()>0) {
            order = glItems.get(0);
        }else{
            logger.info("无商品可以购买");
        }
        order.click();

        //加入购物车
        By joinShopCardBy = By.linkText(propertiesUtil.getValue(EnProperties.JOIN_SHOP_CARD_BTN_LINKTEXT));
        WebElement joinShopCart = webDriver.findElement(joinShopCardBy);
        joinShopCart.click();

        String currentWindow = webDriver.getWindowHandle(); //获取当前窗口的句柄
        Set<String> handles = webDriver.getWindowHandles(); //获取所有窗口的句柄
        Iterator<String> it = handles.iterator();
        while (it.hasNext()){
            String handle = it.next();
            if(!handle.equals(currentWindow)){
                webDriver = webDriver.switchTo().window(handle); //切换到新的句柄所指向的窗口
                break;
            }
        }

        //去购物车结算
        By toShopCardAccountBy = By.linkText(propertiesUtil.getValue(EnProperties.TO_SHOP_CARD_ACCOUNT_BTN_LINKTEXT));
        WebElement toShopCartAccount = webDriver.findElement(toShopCardAccountBy);
        toShopCartAccount.click();
        //去结算
        By toAccountBy = By.linkText(propertiesUtil.getValue(EnProperties.TO_ACCOUNT_BTN_LINKTEXT));
        WebElement toAccount = webDriver.findElement(toAccountBy);
        toAccount.click();
        //提交订单
        By commitOrderBy = By.id(propertiesUtil.getValue(EnProperties.COMMIT_ORDER_BTN_ID));
        WebElement commitOrder = webDriver.findElement(commitOrderBy);
        commitOrder.click();
        webDriver.close();
    }
}
