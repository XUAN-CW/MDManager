package edu.guet.markdowm_manager;

import edu.guet.markdowm_manager.dao.ArticleDao;
import edu.guet.markdowm_manager.service.ArticleService;
import edu.guet.markdowm_manager.utils.GetMarkdown;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MarkdowmManagerApplicationTests {

    @Autowired
    GetMarkdown getMarkdown;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    ArticleService articleService;

    @Test
    void contextLoads() throws IOException {
        getMarkdown.getFileBySuffix("D:\\core\\Desktop\\MD").forEach(k-> System.out.println(k.getAbsolutePath()));
    }

    @Test
    void updateArticleScanPathTest() throws IOException {
        List<File> fileList = new ArrayList<>();

        fileList.add(new File("D:\\core\\Desktop\\MD"));
//        fileList.add(new File("D:\\core\\java\\framework\\SpingBootLearning"));
        fileList.add(new File("D:\\core\\java\\framework"));
        articleDao.updateArticleScanPath(fileList);
    }

    @Test
    void retrieveArticleScanPathTest() {
        articleDao.retrieveArticleScanPath().forEach(k-> System.out.println(k.getAbsolutePath()));
    }

    @Test
    void retrieveArticleScanPathTest2() {
        articleService.getArticles().forEach(article -> System.out.println(article));
    }
}
