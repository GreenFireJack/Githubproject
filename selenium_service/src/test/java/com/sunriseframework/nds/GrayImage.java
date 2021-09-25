package com.sunriseframework.nds;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by chenhao on 2021/9/20.
 */
public class GrayImage {


//    @Test
    public static void count(String[] args) throws IOException {
        ClassLoader classLoader = GrayImage.class.getClassLoader();
        URL resource = classLoader.getResource("assets/images/download1.png");
        String filePath = resource.getFile();
        BufferedImage bgBI = ImageIO.read(new File(filePath));
        for (int i = 0; i < bgBI.getWidth(); i++) {
            for (int j = 0; j < bgBI.getHeight(); j++) {
                if(i ==128 && j==91){
                    System.out.println("阴影：");
                }
                int[] bgRgb = new int[3];
                int rgb = bgBI.getRGB(i, j);
                bgRgb[0] = (rgb & 0xff0000) >> 16; //36  141	133	126
                bgRgb[1] = (rgb & 0xff00) >> 8;  //1
                bgRgb[2] = (rgb & 0xff);    //2
                System.out.println(rgb+"\t"+ bgRgb[0]+"\t"+bgRgb[1]+"\t"+bgRgb[2]);
            }
        }
    }

    private static int difference(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) + Math.abs(a[2] - b[2]);
    }

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = GrayImage.class.getClassLoader();
        URL inResource = classLoader.getResource("assets/images/download.png");
        URL outResource = classLoader.getResource("assets/images/");
        String inFilePath = inResource.getFile();
        String outFilePath = outResource.getFile()+"download0.png";
        BufferedImage image  = ImageIO.read(new File(inFilePath));
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayImage = new BufferedImage(width, height,  image.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray=0;
                gray=getSmall(r, g, b);//最小值法灰度化
                System.out.println("像素坐标：" + " x=" + i + "   y=" + j + "   灰度值=" + gray);
                grayImage.setRGB(i, j, colorToRGB(255, gray, gray, gray));
            }
        }
        File newFile = new File(outFilePath);
        ImageIO.write(grayImage, "png", newFile);
    }

    //比较三个数的大小取最小数
    public static int getSmall(int x,int y,int z){
        int i = x - y <= 0 ? x : y;
        int r = i - z <= 0 ? i : z;
        return r;
    }

    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

}
