package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/10 - 15:03
 * @references
 * @purpose
 * @errors
 */
@CrossOrigin
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/getArticle")
    public List<Article> getArticle(){

        return articleService.getArticles();
    }


    @PostMapping("/openFile")
    public void openFile(@RequestBody String path) throws IOException {
        System.out.println(path);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(path);
        articleService.openFile(jsonObject.getString("path"));
    }

}
