package io.cucumber.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtilities {

    public static String getConfigProperty(String str) throws IOException {
        Properties prop = new Properties();
        InputStream in = new FileInputStream("src/test/resources/config.properties");
        prop.load(in);
        return prop.getProperty(str);
    }
}
