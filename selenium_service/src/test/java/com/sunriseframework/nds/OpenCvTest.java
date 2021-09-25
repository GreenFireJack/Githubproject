package com.sunriseframework.nds;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

/**
 * Created by chenhao on 2021/9/20.
 */
public class OpenCvTest {
    public static void main(String[] args) {
        URL openCvDllUrl = ClassLoader.getSystemResource("lib/opencv_java453.dll");
        System.load(openCvDllUrl.getPath());
        // 加载OpenCV本地库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        ClassLoader classLoader = CountDistanceUtil.class.getClassLoader();
        URL slideBlockResource = classLoader.getResource("assets/images/slideBlock.png");
        String slideBlockPicPath = slideBlockResource.getFile().substring(1);
        // 对滑块进行处理
        Mat slideBlockMat = Imgcodecs.imread(slideBlockPicPath);
        // 1、灰度化图片
        Imgproc.cvtColor(slideBlockMat, slideBlockMat, Imgproc.COLOR_BGR2GRAY);
        // 2、去除周围黑边
        for (int row = 0; row < slideBlockMat.height(); row++) {
            for (int col = 0; col < slideBlockMat.width(); col++) {
                if (slideBlockMat.get(row, col)[0] == 0) {
                    slideBlockMat.put(row, col, 96);
                }
            }
        }
        // 3、inRange二值化转黑白图
        Core.inRange(slideBlockMat, Scalar.all(96), Scalar.all(96), slideBlockMat);

        URL slideBgResource = classLoader.getResource("assets/images/bigLogin.png");
        String slideBgPicPath = slideBgResource.getFile().substring(1);
        //对滑动背景图进行处理
        Mat slideBgMat = Imgcodecs.imread(slideBgPicPath);
        // 1、灰度化图片
        Imgproc.cvtColor(slideBgMat, slideBgMat, Imgproc.COLOR_BGR2GRAY);
        // 2、二值化
        Imgproc.threshold(slideBgMat, slideBgMat, 127, 255, Imgproc.THRESH_BINARY); Mat g_result = new Mat();
        /* * matchTemplate：在模板和输入图像之间寻找匹配,获得匹配结果图像 * result：保存匹配的结果矩阵 * TM_CCOEFF_NORMED标准相关匹配算法 */
        Imgproc.matchTemplate(slideBgMat, slideBlockMat, g_result, Imgproc.TM_CCOEFF_NORMED);
        /* minMaxLoc：在给定的结果矩阵中寻找最大和最小值，并给出它们的位置 * maxLoc最大值 */
        Point matchLocation = Core.minMaxLoc(g_result).maxLoc; //返回匹配点的横向距离 return matchLocation.x;
        System.out.println(matchLocation.x);

    }
}
