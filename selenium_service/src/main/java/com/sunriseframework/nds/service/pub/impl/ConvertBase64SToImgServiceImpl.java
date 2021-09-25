package com.sunriseframework.nds.service.pub.impl;

import com.sunriseframework.nds.service.pub.interfaces.ConvertBase64ToImgService;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by chenhao on 2021/9/19.
 */
@Service("convertBase64ToImgService")
public class ConvertBase64SToImgServiceImpl implements ConvertBase64ToImgService{


    public void convert(String imgStr,String newImgUrl,String newPictureName) {
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
//            ClassLoader classLoader = getClass().getClassLoader();
//            URL resource = classLoader.getResource(newImgUrl);
//            String newPicturePath = resource.getFile();
            String newPicturePath = System.getProperty("user.dir")+newImgUrl;//根目录命令行访问

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
