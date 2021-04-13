package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.utils.GetMarkdown;
import com.example.demo.utils.SaveAndRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XUAN
 * @date 2021/4/10 - 15:12
 * @references
 * @purpose
 * @errors
 */
@Service
public class ArticleService {


    @Autowired
    GetMarkdown getFile;

    public List<Article> getArticles(String path){

        List<Article> articleList = new ArrayList<>();
        List<File> fileList = getFile.getFileBySuffix(path);
        for (File file:fileList) {
            String content = SaveAndRead.read(file.getAbsolutePath());
            String reg = "(?<=^(---))[\\s\\S]*?(?=\\1)";//定义正则表达式

            Pattern patten = Pattern.compile(reg);//编译正则表达式
            Matcher matcher = patten.matcher(content);// 指定要匹配的字符串

            Article article = new Article();
            if (matcher.find()){
                try {
                    Yaml yaml = new Yaml();
                    String description = matcher.group();
                    Map map = yaml.loadAs(description, Map.class);
                    String title=(String) map.get("title");
                    Date date = (Date) map.get("date");
                    List categories = (List<String>) map.get("categories");
                    List tags = (List<String>) map.get("tags");
                    article.setPath(file.getAbsolutePath());
                    article.setTitle(title);
                    article.setDate(date);
                    article.setCategories(categories);
                    article.setTags(tags);
                }catch (Exception e){
                    e.printStackTrace();
                    article.setTitle("文件 YAML 格式有误");
                    article.setPath(file.getAbsolutePath());
                }
            }else {
                article.setTitle("文件无 YAML");
                article.setPath(file.getAbsolutePath());
            }
            articleList.add(article);
        }
        return articleList;
    }

    public void openFile(String path) throws IOException {
        String command = "rundll32 url.dll,FileProtocolHandler " + path;
        Runtime.getRuntime().exec(command);
    }
}
