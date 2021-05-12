package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/13 - 9:11
 * @references
 * @purpose
 * @errors
 */
@Component
@Data
@ConfigurationProperties("settings")
public class Settings {
    List<String> scanPath;
    List<String> excludeDir;
    List<String> excludeDirBySubfile;

    public String excludeDirToOneRegex(){
        StringBuffer regex = new StringBuffer("");
        for (int i = 0; i < excludeDir.size(); i++) {
            if (0!=i){
                regex.append("|");
            }
            regex.append("(");
            regex.append(excludeDir.get(i));
            regex.append(")");
        }
        return regex.toString();
    }
}
