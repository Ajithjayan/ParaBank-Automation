package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class configReader {
    static Properties prop = new Properties();
    //FileInputStream fis;

    static{
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBrowser() throws IOException {

        String browserName = prop.getProperty("browser");
        return browserName;
    }
    public  static String getBaseUrl(){
        return prop.getProperty("baseUrl");
    }
}
