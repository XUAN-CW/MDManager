package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.utils.GetFile;
import com.example.demo.utils.SaveAndRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Value("${conf.rootPath}")
    String rootPath;

    @Autowired
    GetFile getFile;

    public List<Article> getArticles(){

        List<Article> articleList = new ArrayList<>();
        List<File> fileList = getFile.getFileBySuffix(rootPath,".md");
        for (File file:fileList) {
            String content = SaveAndRead.read(file.getAbsolutePath());
            String reg = "(?<=^(---))[\\s\\S]*?(?=\\1)";//定义正则表达式

            Pattern patten = Pattern.compile(reg);//编译正则表达式
            Matcher matcher = patten.matcher(content);// 指定要匹配的字符串

            if (matcher.find()){
                Article article = new Article();
                try {
                    Yaml yaml = new Yaml();
                    String description = matcher.group();
                    article = yaml.loadAs(description, Article.class);

                }catch (Exception e){
                    e.printStackTrace();
                }
                article.setPath(file.getAbsolutePath());
                articleList.add(article);
            }
        }
        return articleList;
    }

    public void openFile(String path) throws IOException {
        String command = "rundll32 url.dll,FileProtocolHandler " + path;
        Runtime.getRuntime().exec(command);
    }
}
