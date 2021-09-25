package com.sunriseframework.nds;

/**
 * Created by chenhao on 2021/9/20.
 */
public class DistanceScaleUtil {

    public static int scaling(int distance,double radio) {
        double dif = distance * radio;
//        int movDis = (int)(distance - dif)+2;//7,8次成功
        int movDis = (int)(distance - dif)+1;//可能位置太准确，导致识别为机器
        return movDis;
    }
}
