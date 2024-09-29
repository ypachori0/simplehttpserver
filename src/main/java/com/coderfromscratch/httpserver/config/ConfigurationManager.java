package com.coderfromscratch.httpserver.config;

import java.io.IOException;

public class ConfigurationManager {

    //singleton bc only 1 config manager will be shared across the whole project
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        //if there is no configuration manager, create a new one
        if (myConfigurationManager == null) {
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }

    //used to load a config file by the path specified
    public void loadConfigurationFile(String filePath) throws IOException {

    }

    //returns the current loaded configuration
    public void getCurrentConfiguration() {

    }
}
