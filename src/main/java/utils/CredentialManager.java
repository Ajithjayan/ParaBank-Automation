package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static utils.configReader.prop;

public class CredentialManager {
    static FileInputStream fis;

    static {
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/credential.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getUserName() throws IOException {
        prop.load(fis);
        return prop.getProperty("username");
    }
    public static String getPassword() throws IOException {
        prop.load(fis);
        return prop.getProperty("password");
    }
    public static void saveCredentials(String username, String password) throws IOException {
        prop.setProperty("username", username);
        prop.setProperty("password",password);
        FileOutputStream fos= new FileOutputStream(System.getProperty("user.dir") + "/src/test/resources/credential.properties");
        prop.store(fos,null);
    }
}
