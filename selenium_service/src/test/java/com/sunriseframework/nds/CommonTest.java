package com.sunriseframework.nds;

import java.util.Random;

/**
 * Created by chenhao on 2021/9/20.
 */
public class CommonTest {

    public static void main(String[] args) {
//        String str = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";
//        String substring = str.substring(str.lastIndexOf(".")+1, str.length());
//        System.out.println(substring);

        for (int i = 0; true; i++) {
            int moveX = new Random().nextInt(8) - 5;
            System.out.println(moveX);
        }
    }
}
