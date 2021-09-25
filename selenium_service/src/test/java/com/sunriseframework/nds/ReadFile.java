package com.sunriseframework.nds;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Created by chenhao on 2021/9/19.
 */
public class ReadFile {

    @Test
    public void read() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("assets/js/stealth.min.js");
        File file = new File(resource.getFile());
        System.out.println(file.exists());
    }
}
