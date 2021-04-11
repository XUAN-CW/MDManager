package com.example.demo;

import com.example.demo.domain.Article;
import com.example.demo.service.ArticleService;
import com.example.demo.utils.GetFile;
import com.example.demo.utils.SaveAndRead;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class DemoApplicationTests {

    @Value("${conf.rootPath}")
    String rootPath;

    @Autowired
    ArticleService articleService;

    @Autowired
    GetFile getFile;

    @Test
    public void getFileBySuffix() {
        getFile.getFileBySuffix(rootPath,".md").forEach((file)->{
            System.out.println(file.getAbsolutePath());
        });
    }

    @Test
    public void getDescription(){
        getFile.getFileBySuffix(rootPath,".md").forEach((file)->{
            System.out.println(file.getAbsolutePath());
            String content = SaveAndRead.read(file.getAbsolutePath());
            String reg = "(?<=^(---))[\\s\\S]*?(?=\\1)";//定义正则表达式

            Pattern patten = Pattern.compile(reg);//编译正则表达式
            Matcher matcher = patten.matcher(content);// 指定要匹配的字符串

            if (matcher.find()){
                System.out.println(matcher.group());
            }
        });
    }

    @Test
    public void testYml() {

        getFile.getFileBySuffix(rootPath,".md").forEach((file)->{
            System.out.println(file.getAbsolutePath());
            String content = SaveAndRead.read(file.getAbsolutePath());
            String reg = "(?<=^(---))[\\s\\S]*?(?=\\1)";//定义正则表达式

            Pattern patten = Pattern.compile(reg);//编译正则表达式
            Matcher matcher = patten.matcher(content);// 指定要匹配的字符串

            if (matcher.find()){
                org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
//                System.out.println(matcher.group());
                String yml = matcher.group();
                System.out.println(yml);
                //如果读入 Map,这里可以是 Mapj 接口,默认实现为 sLinkedHashMap
                Article testEntity = yaml.loadAs(yml, Article.class);System.out.println(testEntity);
            }
        });
    }

    @Test
    public void testYmlMap() {

        getFile.getFileBySuffix(rootPath,".md").forEach((file)->{
            System.out.println(file.getAbsolutePath());
            String content = SaveAndRead.read(file.getAbsolutePath());
            String reg = "(?<=^(---))[\\s\\S]*?(?=\\1)";//定义正则表达式

            Pattern patten = Pattern.compile(reg);//编译正则表达式
            Matcher matcher = patten.matcher(content);// 指定要匹配的字符串

            if (matcher.find()){
                org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
//                System.out.println(matcher.group());
                String yml = matcher.group();
                System.out.println(yml);
                //如果读入 Map,这里可以是 Mapj 接口,默认实现为 sLinkedHashMap
//                Article testEntity = yaml.loadAs(yml, Article.class);System.out.println(testEntity);
                Map map = yaml.loadAs(yml, Map.class);
                /**
                 * eg:获取server中的port
                 * server:
                 port: 8090
                 context-path: /myService
                 */

                map.forEach((k,v)->{
                    System.out.println(k+"-"+v);
                });
//                Map mapServer = (Map)map.get("server");
//                String port = mapServer.get("port").toString();
//                System.out.println(port);//输出8090

            }
        });
    }

    @Test
    public void articleServiceTest(){
        articleService.getArticles().forEach(article -> {
            System.out.println(article.toString());
        });
    }

    // [java实现双击打开文件功能](https://zhidao.baidu.com/question/541273746.html)
    @Test
    public void openFile() throws IOException {
        //通过doc命令可以通过默认方式打开文件
        String command = "rundll32 url.dll,FileProtocolHandler " + "D:\\core\\java\\MyProject\\MDManager\\backend\\src\\test\\TsetData\\windows下jdk安装.md";
        Runtime.getRuntime().exec(command);
    }
}
