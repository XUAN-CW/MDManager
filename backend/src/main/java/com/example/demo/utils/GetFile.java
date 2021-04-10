package com.example.demo.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/10 - 15:13
 * @references
 * @purpose 获取所有 .md 文件
 * @errors
 */
public class GetFile {

    static List<File> files = new ArrayList<>();

    public static List<File> getFileBySuffix(File dir,String suffix){
        recursiveTraversalFolder(dir,suffix);
        return files;
    }

    public static List<File> getFileBySuffix(String path,String suffix){
        return getFileBySuffix(new File(path),suffix);
    }

    private static void recursiveTraversalFolder(File currentDir, String suffix) {
        File[] fileArr = currentDir.listFiles();
        // 空文件夹，直接跳过
        if (null != fileArr && fileArr.length != 0) {
            for (File file : fileArr) {
                // 是文件夹，继续往下递归
                if (file.isDirectory()) {
                    recursiveTraversalFolder(file, suffix);
                } else { // 是文件,提取文件
                    if (file.getName().endsWith(suffix)) {
                        files.add(file);
                    }
                }
            }
        }
    }
}
