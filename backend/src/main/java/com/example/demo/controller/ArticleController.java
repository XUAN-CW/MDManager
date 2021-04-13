package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.R;
import com.example.demo.config.Settings;
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

    @Autowired
    Settings settings;

    @GetMapping("/getScanPath")
    public R getScanPath(){
        return R.ok().data("scanPath",settings.getScanPath());
    }

    @GetMapping("/getArticle")
    public R getArticle(String dir){
//        System.out.println(dir);
//        return R.ok();
        return R.ok().data("articles",articleService.getArticles(dir)).data("dir",dir);
    }

    @GetMapping("/openFile")
    public R openFile(String path) throws IOException {
        System.out.println(path);
        articleService.openFile(path);
        return R.ok();
    }


}
