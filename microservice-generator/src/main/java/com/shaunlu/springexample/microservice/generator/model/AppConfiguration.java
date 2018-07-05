package com.shaunlu.springexample.microservice.generator.model;

import java.io.File;

public class AppConfiguration {

    private String appName;

    private String port;

    private String packageName;

    private String rootPath;

    private String srcPath;

    private String testPath;

    private String resourcePath;

    private String testResourcePath;

    private String rootPackage;

    public AppConfiguration(){}

    public AppConfiguration(ApplicationParam param) {
        this.rootPath = this.getClass().getResource("/").getPath();
        this.appName = param.getApplicationName();
        this.port = param.getPort();
        this.rootPackage = param.getRootPackageName();
        this.packageName = param.getRootPackageName()+"."+param.getApplicationName();
        this.srcPath = "src" + File.separator + "main" + File.separator + "java";
        this.testPath = "src" + File.separator + "test" + File.separator + "java";
        for(String folderName : packageName.split("\\.")){
            this.srcPath = this.srcPath + File.separator + folderName;
            this.testPath = this.testPath + File.separator + folderName;
        }
        this.testPath += File.separator + "test";
        this.resourcePath = "src" + File.separator + "main" + File.separator + "resources";
        this.testResourcePath = "src" + File.separator + "test" + File.separator + "resources";
    }

    public String getPackageName() {
        return packageName;
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public String getTestPath() {
        return testPath;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public String getTestResourcePath() {
        return testResourcePath;
    }

    public String getAppName() {
        return appName;
    }

    public String getPort() {
        return port;
    }

    public String getRootPackage() {
        return rootPackage;
    }
}
