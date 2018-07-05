package com.shaunlu.springexample.microservice.generator.generator;

import com.shaunlu.springexample.microservice.generator.model.AppConfiguration;
import com.shaunlu.springexample.microservice.generator.util.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
public class SourceGenerator {

    private Configuration freemarkerConfig;

    private AppConfiguration appConfig;

    private File appFolder;

    public void start(AppConfiguration appConfig) throws Exception {
        this.appConfig = appConfig;

        // create app root folder
        appFolder = new File(appConfig.getAppName());
        appFolder.mkdir();

        this.freemarkerConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setDirectoryForTemplateLoading(appFolder);

        // create src, test, resource folders
        File srcFolder = FileUtils.createSubFolder(appFolder, appConfig.getSrcPath());
        File srcResourceFolder = FileUtils.createSubFolder(appFolder, appConfig.getResourcePath());
        File testFolder = FileUtils.createSubFolder(appFolder, appConfig.getTestPath());
        File testResourceFolder = FileUtils.createSubFolder(appFolder, appConfig.getTestResourcePath());

        // src code
        String templateSrcPath = appConfig.getRootPath() + File.separator + "templates" + File.separator + "webapp" + File.separator + "code" + File.separator + "func";
        FileUtils.copyFile(templateSrcPath, srcFolder.getAbsolutePath());
        compileTemplateFile(srcFolder);

        // src resource
        String templateSrcResourcePath = appConfig.getRootPath() + File.separator + "templates" + File.separator + "webapp" + File.separator + "resource" + File.separator + "func";
        FileUtils.copyFile(templateSrcResourcePath, srcResourceFolder.getAbsolutePath());
        compileTemplateFile(srcResourceFolder);

        // test code
        String templateTestPath = appConfig.getRootPath() + File.separator + "templates" + File.separator + "webapp" + File.separator + "code" + File.separator + "test";
        FileUtils.copyFile(templateTestPath, testFolder.getAbsolutePath());
        compileTemplateFile(testFolder);

        // test resource
        String templateTestResourcePath = appConfig.getRootPath() + File.separator + "templates" + File.separator + "webapp" + File.separator + "resource" + File.separator + "func";
        FileUtils.copyFile(templateTestResourcePath, testResourceFolder.getAbsolutePath());
        compileTemplateFile(testResourceFolder);

        // build tool
        String buildResPath = appConfig.getRootPath() + File.separator + "templates" + File.separator + "webapp" + File.separator + "build";
        FileUtils.copyFile(buildResPath, appFolder.getAbsolutePath());
        compileTemplateFile(new File(appFolder, "pom.xml.ftl"));

        // other file
        String otherResPath = appConfig.getRootPath() + File.separator + "templates" + File.separator + "webapp" + File.separator + "other";
        FileUtils.copyFile(otherResPath, appFolder.getAbsolutePath());
    }


    private void compileTemplateFile(File target) throws Exception {
        if (target.isDirectory()) {
            for (String targetSubFiles : target.list()) {
                compileTemplateFile(new File(target, targetSubFiles));
            }
        } else {
            if (target.getName().endsWith("ftl")) {
                String templatePath = target.getAbsolutePath().substring(this.appFolder.getAbsolutePath().length());
                Template template = freemarkerConfig.getTemplate(templatePath);
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("package", this.appConfig.getPackageName());
                dataMap.put("package_name", this.appConfig.getRootPackage());
                dataMap.put("application_name", this.appConfig.getAppName());
                dataMap.put("application_port", this.appConfig.getPort());
                String ext = target.getName().split("\\.")[1];
                Writer output = new FileWriter(new File(target.getParentFile(), FileUtils.getFileNameWithoutExt(target) + "." + ext));
                template.process(dataMap, output);
                output.flush();
                target.delete();
            }
        }
    }


    private void generateSrcResource() {

    }

    private void generateTestCode() {

    }

    private void generateTestResource() {

    }


    private void generateBuildTool() {

    }

    private void generateOtherFile() {

    }


    public void templateTest() throws IOException, TemplateException {
        Template template = freemarkerConfig.getTemplate("/webapp/example.ftl");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("package", "com.shaunlu");
        Writer file = new FileWriter(new File("example.java"));
        template.process(dataMap, file);
        file.flush();
    }

}
