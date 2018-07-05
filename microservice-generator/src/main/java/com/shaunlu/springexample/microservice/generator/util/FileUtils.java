package com.shaunlu.springexample.microservice.generator.util;

import java.io.*;
import java.util.regex.Pattern;

public class FileUtils {

    public static void copyFile(String srcPath, String destPath) throws IOException {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        copy(srcFile, destFile);
    }

    public static File createSubFolder(File folder, String subFolderPath){
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        for (String subFolder : subFolderPath.split(pattern)){
            folder = new File(folder, subFolder);
            if(!folder.exists()){
                folder.mkdir();
            }
        }
        return folder;
    }

    public static String getFileNameWithoutExt(File file){
        return file.getName().split("\\.")[0];
    }

    public static String getFileExt(File file){
        String[] strs = file.getName().split("\\.");
        return strs[strs.length-1];
    }

    private static void copy(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
            }
            //list all the directory contents
            String files[] = src.list();
            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copy(srcFile, destFile);
            }
        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }
}
