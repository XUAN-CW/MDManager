package com.example.demo.config.configuration;

import com.example.demo.config.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileFilter;

/**
 * @author XUAN
 * @date 2021/4/13 - 13:08
 * @references
 * @purpose
 * @errors
 */
@Configuration
public class FileFilterConfiguration {
    @Autowired
    Settings settings;

    @Bean
    FileFilter MDFileFilter(){
        return file -> {
            if (!file.isDirectory() && !file.getName().endsWith(".md")){
                return false;
            }

            if (file.isHidden() || file.getName().startsWith(".")){
                return false;
            }

            for (String s: settings.getExcludeDirBySubfile()) {
                if (new File(file.getAbsolutePath()+File.separator+s).exists()){
                    return false;
                }
            }

            if (file.getAbsolutePath().replaceAll(settings.excludeDirToOneRegex(),"").equals("")){
                return false;
            }

            for (String s: settings.getExcludeDirBySubfile()) {
                if (new File(file.getAbsolutePath()+File.separator+s).exists()){
                    return false;
                }
            }

            return true;
        };
    }


}
