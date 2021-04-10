package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/getArticle")
    public List<Article> getArticle(){

        return articleService.getArticles();
    }
}
