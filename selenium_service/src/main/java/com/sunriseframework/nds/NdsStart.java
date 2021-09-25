package com.sunriseframework.nds;

import com.sunriseframework.nds.config.NdsStartConfig;
import org.opencv.core.Core;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;

/**
 * @Title 启动类
 * @Description 项目启动入口
 * @author chenhao
 * @version 1.0 2021-09-18
 */
public class NdsStart {
	public static void main(String[] args) throws Exception
	{
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NdsStartConfig.class);
		context.start();
		//启动需设置-Djava.library.path=./build/resources/main/lib/
		openCvInit();
		while (true) {
			Thread.sleep(1000000l);
		}
	}

	public static void openCvInit(){
//		String openCvDllUrl = System.getProperty("user.dir")+"/lib/opencv_java453.dll";//idea中访问
		String openCvDllUrl = System.getProperty("user.dir")+"/opencv_java453.dll";//根目录命令行访问
		System.load(openCvDllUrl);
		// 加载OpenCV本地库
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
}
