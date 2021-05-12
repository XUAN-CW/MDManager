package com.example.demo.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/10 - 15:08
 * @references
 * @purpose
 * @errors
 */
@Data
public class Article {
    String path;
    String title;
    List<String> tags;
    List<String> categories;
    Date date;
}
