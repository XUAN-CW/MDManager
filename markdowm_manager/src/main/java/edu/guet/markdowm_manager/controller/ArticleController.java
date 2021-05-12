package edu.guet.markdowm_manager.controller;

import edu.guet.markdowm_manager.common.R;
import edu.guet.markdowm_manager.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

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

    // http://localhost:8545//getArticles
    @GetMapping("/getArticles")
    public R getArticles(){
        return R.ok().data("articles",articleService.getArticles());
    }

    @GetMapping("/openFile")
    public R openFile(String path) throws IOException {
        System.out.println(path);
        articleService.openFile(new File(path));
        return R.ok();
    }


}
