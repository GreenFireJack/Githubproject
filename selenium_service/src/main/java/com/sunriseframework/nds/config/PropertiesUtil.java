package com.sunriseframework.nds.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Title 业务参数工具类
 * @Description 根据业务参数的key获取value
 * @author chenhao
 * @version 1.0 2021-09-22
 */
@Component
public class PropertiesUtil {

   private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private Environment environment;

	/**
	 * 根据业务参数的key获取value
	 * @param properties
	 * @return
	 */
	public  String getValue(EnProperties properties){
		return this.getValueFromEnv(properties);
	}


	private  String getValueFromEnv(EnProperties properties){
		String value = "";
		try {
			value = environment.getRequiredProperty(properties.getKey());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException("获取业务参数失败:" + e.getMessage());
		}
		logger.info("Get properties key is [{}] and value is [{}].",properties.getKey(),value);
		return value;
	}

	
}
