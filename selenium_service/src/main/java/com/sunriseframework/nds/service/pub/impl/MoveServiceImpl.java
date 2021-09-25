package com.sunriseframework.nds.service.pub.impl;

import com.sunriseframework.nds.service.pub.interfaces.MoveService;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chenhao on 2021/9/19.
 */
@Service("moveService")
public class MoveServiceImpl implements MoveService{


    /**
     * 此算法成功10多次，现在已被封
     * @param driver
     * @param element
     * @param distance
     * @throws InterruptedException
     */
    public void move(WebDriver driver, WebElement element, int distance) throws InterruptedException {
        int xDis = distance;
        System.out.println("应平移距离：" + xDis);
        int moveX = new Random().nextInt(8) - 5;
        int moveY = 1;
        Actions actions = new Actions(driver);
        new Actions(driver).clickAndHold(element).perform();
        Thread.sleep(200);
        actions.moveToElement(element, moveX, moveY).perform();
        System.out.println(moveX + "--" + moveY);
        for (int i = 0; i < 22; i++){
            int s = 10;
            if (i % 2 == 0){
                s = -10;
            }
            actions.moveToElement(element, s, 1).perform();
            Thread.sleep(new Random().nextInt(100) + 150);
        }

        System.out.println(xDis + "--" + 1);
        actions.moveByOffset(xDis-10, 1).perform();
        Thread.sleep(200);
        actions.release(element).perform();
    }

    public static void move03(WebDriver driver, WebElement element, int distance){
        int randomTime = 0;
        if (distance > 90) {
            randomTime = 250;
        } else if (distance > 80 && distance <= 90) {
            randomTime = 150;
        }
        List<Integer> track = getMoveTrack(distance - 2);
        int moveY = 1;
        try {
            Actions actions = new Actions(driver);
            actions.clickAndHold(element).perform();
            Thread.sleep(200);
            for (int i = 0; i < track.size(); i++) {
                actions.moveByOffset(track.get(i), moveY).perform();
                Thread.sleep(new Random().nextInt(300) + randomTime);
            }
            Thread.sleep(200);
            actions.release(element).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getMoveTrack(int distance) {
        List<Integer> track = new ArrayList<>();// 移动轨迹
        Random random = new Random();
        int current = 0;// 已经移动的距离
        int mid = (int) distance * 4 / 5;// 减速阈值
        int a = 0;
        int move = 0;// 每次循环移动的距离
        while (true) {
            a = random.nextInt(10);
            if (current <= mid) {
                move += a;// 不断加速
            } else {
                move -= a;
            }
            if ((current + move) < distance) {
                track.add(move);
            } else {
                track.add(distance - current);
                break;
            }
            current += move;
        }
        return track;
    }

}
