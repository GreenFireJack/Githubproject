package com.sunriseframework.nds.service.pub.interfaces;

/**
 * Created by chenhao on 2021/9/21.
 */
public interface ConvertBase64ToImgService {

    /**
     * 对Base64编码的字符串解码并生成图片
     * @param imgStr
     * @param newImgUrl
     * @param newPictureName
     */
    void convert(String imgStr,String newImgUrl,String newPictureName);
}
