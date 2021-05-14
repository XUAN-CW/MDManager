package edu.guet.markdowm_manager.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.guet.markdowm_manager.dao.utils.SaveAndReadByUTF8;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/5/12 - 20:38
 * @references
 * @purpose
 * @errors
 */
@Repository
public class ArticleDao {

    @Value("${article.scanDirConfiguration}")
    String scanDirConfiguration;

    public List<File> retrieveArticleScanPath(){
        String paths = SaveAndReadByUTF8.read(scanDirConfiguration);
        List<File> fileList = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(paths);
        if (null != jsonArray){
            for (int i = 0; i < jsonArray.size(); i++) {
                File scanFile = new File(jsonArray.getString(i));
                if (scanFile.exists()){
                    fileList.add(scanFile);
                }
            }
        }
        return fileList;
    }

    public void updateArticleScanPath(List<File> fileList){
        SaveAndReadByUTF8.save(scanDirConfiguration,JSONObject.toJSONString(fileList));
    }
}
