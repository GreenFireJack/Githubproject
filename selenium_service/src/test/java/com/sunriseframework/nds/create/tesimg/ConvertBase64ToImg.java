package com.sunriseframework.nds.create.tesimg;

import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by chenhao on 2021/9/19.
 */
public class ConvertBase64ToImg {

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr
     * @param newImgUrl
     */
    public static void convert(String imgStr,String newImgUrl,String newPictureName) {
        if (imgStr == null) return;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            //生成png图片
//            String imagePath= Config.getUploadPhysicalPath();
            ClassLoader classLoader = ConvertBase64ToImg.class.getClassLoader();
            URL resource = classLoader.getResource(newImgUrl);
            String newPicturePath = resource.getFile();
            //新生成的图片
            String imgFilePath = newPicturePath+newPictureName;
            File file = new File(imgFilePath);
            if(file.exists()){
                file.delete();
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return;
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
    }
}
