package com.example.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/10 - 15:13
 * @references
 * @purpose 获取所有 .md 文件
 * @errors
 */
@Component
public class GetFile {

    public List<File> getFileBySuffix(File dir,String suffix){
        List<File> fileList = new LinkedList<>();
        recursiveTraversalFolder(dir,suffix,fileList);
        return fileList;
    }

    public List<File> getFileBySuffix(String path,String suffix){
        return getFileBySuffix(new File(path),suffix);
    }

    private void recursiveTraversalFolder(File currentDir,String suffix,List<File> fileList) {
        File[] fileArr = currentDir.listFiles();
        // 空文件夹，直接跳过
        if (null != fileArr && fileArr.length != 0) {
            for (File file : fileArr) {
                // 是文件夹，继续往下递归
                if (file.isDirectory()) {
                    recursiveTraversalFolder(file, suffix,fileList);
                } else { // 是文件,提取文件
                    if (file.getName().endsWith(suffix)) {
                        fileList.add(file);
                    }
                }
            }
        }
    }
}
