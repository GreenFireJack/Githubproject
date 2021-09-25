package com.sunriseframework.nds.service.pub.impl;

import com.sunriseframework.nds.service.pub.interfaces.DistanceScaleService;
import org.springframework.stereotype.Service;

/**
 * Created by chenhao on 2021/9/20.
 */
@Service("distanceScaleService")
public class DistanceScaleServiceImpl implements DistanceScaleService{

    public  int scaling(int distance,double radio) {
        double dif = distance * radio;
//        int movDis = (int)(distance - dif)+2;//7,8次成功
        int movDis = (int)(distance - dif)+1;//可能位置太准确，导致识别为机器
        return movDis;
    }
}
