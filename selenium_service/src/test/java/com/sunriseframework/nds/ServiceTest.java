package com.sunriseframework.nds;

import com.sunriseframework.nds.business.pub.interfaces.AutomaticOrderFromJDTask;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chenhao on 2021/9/22.
 */
public class ServiceTest {

    @Autowired
    private AutomaticOrderFromJDTask automaticOrderFromJDTask;

    @Test
    public void test() throws Exception {
        automaticOrderFromJDTask.execute();
    }
}
