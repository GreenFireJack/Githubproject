package com.sunriseframework.nds;


import org.opencv.core.Core;

import java.net.URL;

/**
 * Created by chenhao on 2021/9/20.
 */
public class OpenCvDevelopTest {
    public static void main(String[] args) {
//        运行时jvm参数设置：-Djava.library.path= -Djava.library.path=./lib/
        String filepath = System.getProperty("user.dir")+"/lib/opencv_java453.dll";
//        URL openCvDllUrl = ClassLoader.getSystemResource("/lib/opencv_java453.dll");
//        String path = openCvDllUrl.getPath();
        System.load(filepath);
        // 加载OpenCV本地库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
