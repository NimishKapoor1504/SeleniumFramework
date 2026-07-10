package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static void main(String[] args) throws IOException {

        final Properties prop = new Properties();

        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("Env.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find Env.properties");
                return;
            }

            // Load the properties file
            try {
                prop.load(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Get the property values using their keys
            String Url = prop.getProperty("db.westpacUrl");

            System.out.println("Database URL: " + Url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}