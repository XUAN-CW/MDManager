package edu.guet.markdowm_manager.service;

import edu.guet.markdowm_manager.dao.ArticleDao;
import edu.guet.markdowm_manager.dao.utils.SaveAndRead;
import edu.guet.markdowm_manager.domain.Article;
import edu.guet.markdowm_manager.utils.GetMarkdown;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ArticleDao articleDao;

    public List<Article> getArticles(){
        List<Article> articleList = new ArrayList<>();
        articleDao.retrieveArticleScanPath().forEach(scanDir -> {
            List<File> markdownFileList = getFile.getFileBySuffix(scanDir);
            List<Article> articleListOnThisPath = getArticlesFromMarkdownFileList(markdownFileList);
            articleList.addAll(articleListOnThisPath);
        });
        return articleList;
    }

    private List<Article> getArticlesFromMarkdownFileList(List<File> markdownFileList){
        List<Article> articleList = new ArrayList<>();
        for (File file:markdownFileList) {
            System.out.println(file.getAbsolutePath());
            String content = SaveAndRead.read(file.getAbsolutePath());
            String reg = "(?<=^(---))[\\s\\S]*?(?=\\1)";//定义正则表达式

            Pattern patten = Pattern.compile(reg);//编译正则表达式
            Matcher matcher = patten.matcher(content);// 指定要匹配的字符串

            Article article = new Article();
            article.setPath(file.getAbsolutePath());
            if (matcher.find()){
                try {
                    Yaml yaml = new Yaml();
                    String description = matcher.group();
                    Map map = yaml.loadAs(description, Map.class);
                    article.setTitle((String) map.get("title"));
                    article.setDate((Date) map.get("date"));
                    article.setCategories((List<String>) map.get("categories"));
                    article.setTags((List<String>) map.get("tags"));
                }catch (Exception e){
                    e.printStackTrace();
                    article.setTitle("文件 YAML 格式有误");
                }
            }else {
                article.setTitle("文件无 YAML");
                article.setPath(file.getAbsolutePath());
            }
            articleList.add(article);
        }
        return articleList;
    }

    public void openFile(File file) throws IOException {
        java.awt.Desktop.getDesktop().open(file);
    }
}
