package com.example.demo.utils;

import com.example.demo.config.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
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
public class GetMarkdown {

    @Autowired
    Settings settings;

    @Autowired
    FileFilter fileFilter;

    public List<File> getFileBySuffix(File dir){
        List<File> fileList = new LinkedList<>();
        recursiveTraversalFolder(dir,fileList);
        return fileList;
    }

    public List<File> getFileBySuffix(String path){
        return getFileBySuffix(new File(path));
    }

    private void recursiveTraversalFolder(File currentDir,List<File> fileList) {
        File[] fileArr = currentDir.listFiles(fileFilter);
        if (null != fileArr && fileArr.length != 0) {
            for (File file : fileArr) {
                // 是文件夹，继续往下递归
                if (file.isDirectory()) {
                    recursiveTraversalFolder(file,fileList);
                }else {
                    fileList.add(file);
                }
            }
        }
    }
}
