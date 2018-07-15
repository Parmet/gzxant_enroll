package com.gzxant.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片上传
 * @author: Fatal
 * @date: 2018/7/13 0013 7:54
 */
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
