package utils;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.io.*;
import java.util.Properties;

import static utils.configReader.prop;

public class CredentialManager {
    private static final String CREDENTIAL_FILE = System.getProperty("user.dir")
            + "/src/test/resources/credential.properties";

    private static final Properties credProps = new Properties();

    private static final Object lock = new Object();
    private static Properties loadProps() throws IOException {
        Properties p = new Properties();
        File file = new File(CREDENTIAL_FILE);
        if(file.exists()){
            try (FileInputStream fis = new FileInputStream(file)) {
                p.load(fis);
            }
        }
        return p;

    }

    public static String getUserName() throws IOException {
        synchronized (lock){
            return loadProps().getProperty("username", "");
        }
    }
    public static String getPassword() throws IOException {
        synchronized (lock) {
            return loadProps().getProperty("password", "");
        }
    }
    public static void saveCredentials(String username, String password) throws IOException {
        synchronized (lock) {
            Properties p = loadProps();
            p.setProperty("username", username);
            p.setProperty("password", password);
            try (FileOutputStream fos = new FileOutputStream(CREDENTIAL_FILE)) {
                p.store(fos, null);
            }
        }
    }
}
