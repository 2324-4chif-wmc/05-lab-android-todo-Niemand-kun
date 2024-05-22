package at.htl.todo.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties properties;

    public static void load(Context context) {
        properties = new Properties();

        try (InputStream inputStream = context.getAssets().open("application.properties")) {
            Log.d("Config", "Loading properties file.");
            properties.load(inputStream);
            Log.i("Config", "Properties loaded successfully.");

        } catch (IOException e) {
            Log.e("Config", "Failed to load properties file.", e);
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            Log.e("Config", "Properties not loaded.");
            throw new IllegalStateException("Config properties have not been loaded.");
        }
        String value = properties.getProperty(key);
        if (value == null) {
            Log.w("Config", "Property for key '" + key + "' not found.");
        }
        return value;
    }
}