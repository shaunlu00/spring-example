package com.shaunlu.springexample.microservice.generator.model;

public class ApplicationParam {
    private String applicationName;
    private String rootPackageName;
    private String port;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRootPackageName() {
        return rootPackageName;
    }

    public void setRootPackageName(String rootPackageName) {
        this.rootPackageName = rootPackageName;
    }
}
