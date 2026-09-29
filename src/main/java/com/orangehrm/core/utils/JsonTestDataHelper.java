package com.orangehrm.core.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads JSON test-data files into typed objects for data providers.
 */
public final class JsonTestDataHelper {

    private static final Logger LOGGER = LogManager.getLogger(JsonTestDataHelper.class);
    private static JsonTestDataHelper instance;

    private JsonTestDataHelper() {
    }

    /**
     * Returns the shared helper instance.
     *
     * @return the singleton helper
     */
    public static JsonTestDataHelper getInstance() {
        if (instance == null) {
            synchronized (JsonTestDataHelper.class) {
                if (instance == null) {
                    instance = new JsonTestDataHelper();
                    LOGGER.info("JsonTestDataHelper created");
                }
            }
        }
        return instance;
    }

    /**
     * Reads a JSON array file into objects of the requested type.
     *
     * @param filePath path of the JSON file holding an array of rows
     * @param clazz target model class whose fields match the JSON keys
     * @param <T> model type, one instance per JSON row
     * @return the rows as an array ready for a TestNG data provider
     * @throws FileNotFoundException when the data file does not exist
     */
    public <T> Object[] getTestData(String filePath, Class<T> clazz) throws FileNotFoundException {
        LOGGER.info("Reading test data from {}", filePath);
        JsonReader reader = new JsonReader(new FileReader(filePath));
        List<T> rows = new Gson()
                .fromJson(reader, TypeToken.getParameterized(ArrayList.class, clazz).getType());
        LOGGER.info("Rows read: {}", rows.size());
        return rows.toArray();
    }
}
