package com.sunriseframework.nds;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chenhao on 2021/9/19.
 */
public class Move {

    public static void move01(WebDriver driver, WebElement element, int distance) throws InterruptedException {
        int xDis = distance;// distance to move
        int moveX = new Random().nextInt(10) - 5;
        int moveY = 1;
        Actions actions = new Actions(driver);
        new Actions(driver).clickAndHold(element).perform();//click and hold the moveButton
        Thread.sleep(2000);//slow down
        //问题出在+moveX
        actions.moveByOffset((xDis+moveX)/2,moveY).perform();
        Thread.sleep((int)(Math.random()*2000));
        actions.moveByOffset((xDis+moveX)/2,moveY).perform();//double move,to slow down the move and escape check
        Thread.sleep(500);
        actions.release(element).perform();
    }


    private static void printLocation(WebElement webElement){
        Point point = webElement.getLocation();
        System.out.println(point.toString());
    }


    public static void move(WebDriver driver, WebElement element, int distance) throws InterruptedException {
//        int xDis = distance + 11;
        int xDis = distance;
        System.out.println("应平移距离：" + xDis);
        int moveX = new Random().nextInt(8) - 5;
        int moveY = 1;
        Actions actions = new Actions(driver);
        new Actions(driver).clickAndHold(element).perform();
        Thread.sleep(200);
        printLocation(element);
        actions.moveToElement(element, moveX, moveY).perform();
        System.out.println(moveX + "--" + moveY);
        printLocation(element);
        for (int i = 0; i < 22; i++){
            int s = 10;
            if (i % 2 == 0){
                s = -10;
            }
            actions.moveToElement(element, s, 1).perform();
//            printLocation(element);
            Thread.sleep(new Random().nextInt(100) + 150);
        }

        System.out.println(xDis + "--" + 1);
//        actions.moveByOffset(xDis, 1).perform();
        actions.moveByOffset(xDis-10, 1).perform();
        printLocation(element);
        Thread.sleep(200);
        actions.release(element).perform();
    }

    public static void move03(WebDriver driver, WebElement element, int distance) throws InterruptedException {
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
